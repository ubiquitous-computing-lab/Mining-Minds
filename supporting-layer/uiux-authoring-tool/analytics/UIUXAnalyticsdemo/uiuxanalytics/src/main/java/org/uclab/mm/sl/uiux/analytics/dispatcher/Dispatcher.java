/*
Copyright [2016] [name of copyright owner <-Put your name]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 * Android SDK for UI/UX Authorig Tool Analytics
 * @Author: Jamil Hussain
 * This is the extension of PIWIK android SDK @link https://github.com/piwik/piwik-android-sdk for sending data to custom Analytics engine developed in Django
*/
package org.uclab.mm.sl.uiux.analytics.dispatcher;

import android.os.Process;
import android.util.Log;

import org.json.JSONObject;
import org.uclab.mm.sl.uiux.analytics.UIUXAnalytics;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

/**
 * The type Dispatcher.
 */
public class Dispatcher {
    private static final String LOGGER_TAG = UIUXAnalytics.LOGGER_PREFIX + "Dispatcher";
    private final BlockingQueue<String> mDispatchQueue = new LinkedBlockingQueue<>();
    private final Object mThreadControl = new Object();
    private final Semaphore mSleepToken = new Semaphore(0);
    private final UIUXAnalytics mUIUXAnalytics;
    private final URL mApiUrl;

    private static long startTime = System.currentTimeMillis();

    private static final String TAG = "Dispatcher";


    private final String mAuthToken;

    /**
     * The constant DEFAULT_CONNECTION_TIMEOUT.
     */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 20 * 1000;  // 5s
    private volatile int mTimeOut = DEFAULT_CONNECTION_TIMEOUT;
    private volatile boolean mRunning = false;

    /**
     * The constant DEFAULT_DISPATCH_INTERVAL.
     */
    public static final long DEFAULT_DISPATCH_INTERVAL = 10 * 1000; // 120s
    private volatile long mDispatchInterval = DEFAULT_DISPATCH_INTERVAL;

    /**
     * Instantiates a new Dispatcher.
     *
     * @param UIUXAnalytics the uiux analytics
     * @param apiUrl        the api url
     * @param authToken     the auth token
     */
    public Dispatcher(UIUXAnalytics UIUXAnalytics, URL apiUrl, String authToken) {
        mUIUXAnalytics = UIUXAnalytics;
        mApiUrl = apiUrl;
        mAuthToken = authToken;
    }

    /**
     * Connection timeout in milliseconds
     *
     * @return timeout in milliseconds
     */
    public int getConnectionTimeOut() {
        return mTimeOut;
    }

    /**
     * Timeout when trying to establish connection and when trying to read a response.
     * Values take effect on next dispatch.
     *
     * @param timeOut timeout in milliseconds
     */
    public void setConnectionTimeOut(int timeOut) {
        mTimeOut = timeOut;
    }

    /**
     * Packets are collected and dispatched in batches, this intervals sets the pause between batches.
     *
     * @param dispatchInterval in milliseconds
     */
    public void setDispatchInterval(long dispatchInterval) {
        mDispatchInterval = dispatchInterval;
        if (mDispatchInterval != -1)
            launch();
    }

    /**
     * Gets dispatch interval.
     *
     * @return the dispatch interval
     */
    public long getDispatchInterval() {
        return mDispatchInterval;
    }

    private boolean launch() {
        synchronized (mThreadControl) {
            if (!mRunning) {
                mRunning = true;
                new Thread(mLoop).start();
                return true;
            }
        }
        return false;
    }

    /**
     * Starts the dispatcher for one cycle if it is currently not working.
     * If the dispatcher is working it will skip the dispatch interval once.
     *
     * @return the boolean
     */
    public boolean forceDispatch() {
        if (!launch()) {
            mSleepToken.release();
            return false;
        }
        return true;
    }

    /**
     * Submit.
     *
     * @param query the query
     */
    public void submit(String query) {
        mDispatchQueue.add(query);
        if (mDispatchInterval != -1)
            launch();
    }

    private Runnable mLoop = new Runnable() {
        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            while (mRunning) {
                try {
                    // Either we wait the interval or forceDispatch() granted us one free pass
                    mSleepToken.tryAcquire(mDispatchInterval, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int count = 0;
                List<String> availableEvents = new ArrayList<>();
                List<Map<String, String>> availableEvts = new ArrayList<Map<String, String>>();

                mDispatchQueue.drainTo(availableEvents);
                Timber.tag(LOGGER_TAG).d("Drained %s events.", availableEvents.size());
                TrackerBulkURLWrapper wrapper = new TrackerBulkURLWrapper(mApiUrl, availableEvents, mAuthToken);


                Log.d(TAG,"data  "+ availableEvents);

                Iterator<TrackerBulkURLWrapper.Page> pageIterator = wrapper.iterator();
                while (pageIterator.hasNext()) {
                    TrackerBulkURLWrapper.Page page = pageIterator.next();

                    // use doGET when only event on current page

                    if (page.elementsCount() > 1) {
                        JSONObject eventData = wrapper.getEvents(page);

                        Log.d(TAG,"Packet 1  "+ eventData.toString());

                        Log.d(TAG,"API URL :  "+ wrapper.getApiUrl());

                        try {
                            HttpURLConnection urlConnection = (HttpURLConnection) wrapper.getApiUrl().openConnection();
                            urlConnection.setConnectTimeout(mTimeOut);
                            urlConnection.setReadTimeout(mTimeOut);
                            urlConnection.setDoOutput(true); // Forces post
                            urlConnection.setRequestProperty("Content-Type", "application/json");
                            urlConnection.setRequestProperty("charset", "utf-8");
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                            writer.write(eventData.toString());
                            writer.flush();
                            writer.close();
                            int statusCode = urlConnection.getResponseCode();
                            Log.d(TAG,"status code"+ statusCode);

                        } catch (Exception e) {
                            // Broad but an analytics app shouldn't impact it's host app.
                            Log.d(TAG,"Cannot send request"+ e);
                        }

                    }
                }




                Timber.tag(LOGGER_TAG).d("Dispatched %s events.", count);
                synchronized (mThreadControl) {
                    // We may be done or this was a forced dispatch
                    if (mDispatchQueue.isEmpty() || mDispatchInterval < 0) {
                        mRunning = false;
                        break;
                    }
                }
            }
        }




    };

    /**
     * http://stackoverflow.com/q/4737841
     *
     * @param param raw data
     * @return encoded string
     */
    public static String urlEncodeUTF8(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            Timber.tag(LOGGER_TAG).e(e, "Cannot encode %s", param);
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }


    /**
     * For bulk tracking purposes
     *
     * @param map query map
     * @return String "?idsite=1&url=http://example.org&action_name=Test bulk log view&rec=1"
     */
    public static String urlEncodeUTF8(Map<String, String> map) {


        StringBuilder sb = new StringBuilder(100);

        JSONObject obj = new JSONObject(map);

        return obj.toString();
    }



}
