/*
Copyright [2016] [name of copyright owner @Jamil]

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
  *  This is the extension of PIWIK android SDK @link https://github.com/piwik/piwik-android-sdk for sending data to custom Analytics engine developed in Django
 */

package org.uclab.mm.sl.uiux.analytics;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import org.uclab.mm.sl.uiux.analytics.dispatcher.Dispatcher;
import org.uclab.mm.sl.uiux.analytics.tools.DeviceHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Main tracking class
 * This class is threadsafe.
 */
public class Tracker {
    /**
     * The constant LOGGER_TAG.
     */
    protected static final String LOGGER_TAG = UIUXAnalytics.LOGGER_PREFIX + "Tracker";

    // UIUXAnalytics default parameter values
    private static final String DEFAULT_UNKNOWN_VALUE = "unknown";
    private static final String DEFAULT_TRUE_VALUE = "1";
    private static final String DEFAULT_RECORD_VALUE = DEFAULT_TRUE_VALUE;
    private static final String DEFAULT_API_VERSION_VALUE = "1";

    /**
     * The constant PREF_KEY_TRACKER_USERID.
     */
// Sharedpreference keys for persisted values
    protected static final String PREF_KEY_TRACKER_USERID = "tracker.userid";
    /**
     * The constant PREF_KEY_TRACKER_FIRSTVISIT.
     */
    protected static final String PREF_KEY_TRACKER_FIRSTVISIT = "tracker.firstvisit";
    /**
     * The constant PREF_KEY_TRACKER_VISITCOUNT.
     */
    protected static final String PREF_KEY_TRACKER_VISITCOUNT = "tracker.visitcount";
    /**
     * The constant PREF_KEY_TRACKER_PREVIOUSVISIT.
     */
    protected static final String PREF_KEY_TRACKER_PREVIOUSVISIT = "tracker.previousvisit";

    private final UIUXAnalytics mUIUXAnalytics;

    /**
     * Tracking HTTP API endpoint, for example, http://your-piwik-domain.tld/piwik.php
     */
    private final URL mApiUrl;

    /**
     * The ID of the website we're tracking a visit/action for.
     */
    private final int mSiteId;
    private final String mAuthToken;
    private final Object mSessionLock = new Object();
    private final CustomVariables mVisitCustomVariable = new CustomVariables();
    private final Dispatcher mDispatcher;
    private final Random mRandomAntiCachingValue = new Random(new Date().getTime());
    private final TrackMe mDefaultTrackMe = new TrackMe();

    private String mLastEvent;
    private long mSessionTimeout = 30 * 60 * 1000;
    private long mSessionStartTime;

    /**
     * Use UIUXAnalytics.newTracker() method to create new trackers
     *
     * @param url           (required) Tracking HTTP API endpoint, for example, http://your-piwik-domain.tld/piwik.php
     * @param siteId        (required) id of site
     * @param authToken     (optional) could be null
     * @param UIUXAnalytics uxe object used to gain access to application params such as name, resolution or lang
     * @throws MalformedURLException the malformed url exception
     */
    protected Tracker(@NonNull final String url, int siteId, String authToken, @NonNull UIUXAnalytics UIUXAnalytics) throws MalformedURLException {

        String checkUrl = url;
        mApiUrl = new URL(checkUrl);

        mUIUXAnalytics = UIUXAnalytics;
        mSiteId = siteId;
        mAuthToken = authToken;

        mDispatcher = new Dispatcher(mUIUXAnalytics, mApiUrl, authToken);

        String userId = getSharedPreferences().getString(PREF_KEY_TRACKER_USERID, null);
       // String userId = "1";
        if (userId == null) {
            userId = "1";
            getSharedPreferences().edit().putString(PREF_KEY_TRACKER_USERID, userId).apply();
        }
        mDefaultTrackMe.set(QueryParams.USER_ID, userId);
        mDefaultTrackMe.set(QueryParams.SESSION_START, DEFAULT_TRUE_VALUE);

        String resolution = DEFAULT_UNKNOWN_VALUE;
        int[] res = DeviceHelper.getResolution(mUIUXAnalytics.getContext());
        if (res != null)
            resolution = String.format("%sx%s", res[0], res[1]);
        mDefaultTrackMe.set(QueryParams.SCREEN_RESOLUTION, resolution);

        mDefaultTrackMe.set(QueryParams.USER_AGENT, DeviceHelper.getUserAgent());
        mDefaultTrackMe.set(QueryParams.LANGUAGE, DeviceHelper.getUserLanguage());
        mDefaultTrackMe.set(QueryParams.COUNTRY, DeviceHelper.getUserCountry());

    }

    /**
     * Gets piwik.
     *
     * @return the piwik
     */
    public UIUXAnalytics getPiwik() {
        return mUIUXAnalytics;
    }

    /**
     * Gets api url.
     *
     * @return the api url
     */
    public URL getAPIUrl() {
        return mApiUrl;
    }

    /**
     * Gets auth token.
     *
     * @return the auth token
     */
    public String getAuthToken() {
        return mAuthToken;
    }

    /**
     * Gets site id.
     *
     * @return the site id
     */
    protected int getSiteId() {
        return mSiteId;
    }

    /**
     * UIUXAnalytics will use the content of this object to fill in missing values before any transmission.
     * While you can modify it's values, you can also just set them in your {@link TrackMe} object as already set values will not be overwritten.
     *
     * @return the default TrackMe object
     */
    public TrackMe getDefaultTrackMe() {
        return mDefaultTrackMe;
    }

    /**
     * Start new session.
     */
    public void startNewSession() {
        synchronized (mSessionLock) {
            mSessionStartTime = 0;
        }
    }

    /**
     * Sets session timeout.
     *
     * @param milliseconds the milliseconds
     */
    public void setSessionTimeout(int milliseconds) {
        synchronized (mSessionLock) {
            mSessionTimeout = milliseconds;
        }
    }

    /**
     * Try new session boolean.
     *
     * @return the boolean
     */
    protected boolean tryNewSession() {
        synchronized (mSessionLock) {
            boolean expired = System.currentTimeMillis() - mSessionStartTime > mSessionTimeout;
            // Update the session timer
            mSessionStartTime = System.currentTimeMillis();
            return expired;
        }
    }

    /**
     * Default is 30min (30*60*1000).
     *
     * @return session timeout value in miliseconds
     */
    public long getSessionTimeout() {
        return mSessionTimeout;
    }

    /**
     * {@link Dispatcher#getConnectionTimeOut()}
     *
     * @return the dispatch timeout
     */
    public int getDispatchTimeout() {
        return mDispatcher.getConnectionTimeOut();
    }

    /**
     * {@link Dispatcher#setConnectionTimeOut(int)}
     *
     * @param timeout the timeout
     */
    public void setDispatchTimeout(int timeout) {
        mDispatcher.setConnectionTimeOut(timeout);
    }

    /**
     * Processes all queued events in background thread
     *
     * @return true if there are any queued events and opt out is inactive
     */
    public boolean dispatch() {
        if (!mUIUXAnalytics.isOptOut()) {
            mDispatcher.forceDispatch();
            return true;
        }
        return false;
    }

    /**
     * Set the interval to 0 to dispatch events as soon as they are queued.
     * If a negative value is used the dispatch timer will never run, a manual dispatch must be used.
     *
     * @param dispatchInterval in milliseconds
     * @return the dispatch interval
     */
    public Tracker setDispatchInterval(long dispatchInterval) {
        mDispatcher.setDispatchInterval(dispatchInterval);
        return this;
    }

    /**
     * Gets dispatch interval.
     *
     * @return in milliseconds
     */
    public long getDispatchInterval() {
        return mDispatcher.getDispatchInterval();
    }

    /**
     * Defines the User ID for this request.
     * User ID is any non empty unique string identifying the user (such as an email address or a username).
     * To access this value, users must be logged-in in your system so you can
     * fetch this user ID from your system, and pass it to UIUXAnalytics.
     * <p/>
     * When specified, the User ID will be "enforced".
     * This means that if there is no recent visit with this User ID, a new one will be created.
     * If a visit is found in the last 30 minutes with your specified User ID,
     * then the new action will be recorded to this existing visit.
     *
     * @param userId passing null will delete the current user-id.
     * @return the user id
     */
    public Tracker setUserId(String userId) {
        mDefaultTrackMe.set(QueryParams.USER_ID, userId);
        getSharedPreferences().edit().putString(PREF_KEY_TRACKER_USERID, userId).apply();
        return this;
    }

    /**
     * Gets user id.
     *
     * @return a user-id string, either the one you set or the one UIUXAnalytics generated for you.
     */
    public String getUserId() {
        return mDefaultTrackMe.get(QueryParams.USER_ID);
    }

    /**
     * The unique visitor ID, must be a 16 characters hexadecimal string.
     * Every unique visitor must be assigned a different ID and this ID must not change after it is assigned.
     * If this value is not set UIUXAnalytics will still track visits, but the unique visitors metric might be less accurate.
     *
     * @param visitorId the visitor id
     * @return the visitor id
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Tracker setVisitorId(String visitorId) throws IllegalArgumentException {
        if (confirmVisitorIdFormat(visitorId))
            mDefaultTrackMe.set(QueryParams.VISITOR_ID, visitorId);
        return this;
    }

    /**
     * Gets visitor id.
     *
     * @return the visitor id
     */
    public String getVisitorId() {
        return mDefaultTrackMe.get(QueryParams.VISITOR_ID);
    }

    private static final Pattern PATTERN_VISITOR_ID = Pattern.compile("^[0-9a-f]{16}$");

    private boolean confirmVisitorIdFormat(String visitorId) throws IllegalArgumentException {
        Matcher visitorIdMatcher = PATTERN_VISITOR_ID.matcher(visitorId);
        if (visitorIdMatcher.matches()) {
            return true;
        }
        throw new IllegalArgumentException("VisitorId: " + visitorId + " is not of valid format, " +
                " the format must match the regular expression: " + PATTERN_VISITOR_ID.pattern());
    }


    /**
     * There parameters are only interesting for the very first query.
     */
    private void injectInitialParams(TrackMe trackMe) {
        long firstVisitTime;
        int visitCount;
        long previousVisit;

        // Protected against Trackers on other threads trying to do the same thing.
        // This works because they would use the same preference object.
        synchronized (getSharedPreferences()) {
            visitCount = 1 + getSharedPreferences().getInt(PREF_KEY_TRACKER_VISITCOUNT, 0);
            getSharedPreferences().edit().putInt(PREF_KEY_TRACKER_VISITCOUNT, visitCount).apply();
        }

        synchronized (getSharedPreferences()) {
            firstVisitTime = getSharedPreferences().getLong(PREF_KEY_TRACKER_FIRSTVISIT, -1);
            if (firstVisitTime == -1) {
                firstVisitTime = System.currentTimeMillis() / 1000;
                getSharedPreferences().edit().putLong(PREF_KEY_TRACKER_FIRSTVISIT, firstVisitTime).apply();
            }
        }

        synchronized (getSharedPreferences()) {
            previousVisit = getSharedPreferences().getLong(PREF_KEY_TRACKER_PREVIOUSVISIT, -1);
            getSharedPreferences().edit().putLong(PREF_KEY_TRACKER_PREVIOUSVISIT, System.currentTimeMillis() / 1000).apply();
        }

        // trySet because the developer could have modded these after creating the Tracker
        mDefaultTrackMe.trySet(QueryParams.FIRST_VISIT_TIMESTAMP, firstVisitTime);
        mDefaultTrackMe.trySet(QueryParams.TOTAL_NUMBER_OF_VISITS, visitCount);
        if (previousVisit != -1)
            mDefaultTrackMe.trySet(QueryParams.PREVIOUS_VISIT_TIMESTAMP, previousVisit);

        trackMe.trySet(QueryParams.SESSION_START, mDefaultTrackMe.get(QueryParams.SESSION_START));
        trackMe.trySet(QueryParams.SCREEN_RESOLUTION, mDefaultTrackMe.get(QueryParams.SCREEN_RESOLUTION));
        trackMe.trySet(QueryParams.USER_AGENT, mDefaultTrackMe.get(QueryParams.USER_AGENT));
        trackMe.trySet(QueryParams.LANGUAGE, mDefaultTrackMe.get(QueryParams.LANGUAGE));
        trackMe.trySet(QueryParams.COUNTRY, mDefaultTrackMe.get(QueryParams.COUNTRY));
        trackMe.trySet(QueryParams.FIRST_VISIT_TIMESTAMP, mDefaultTrackMe.get(QueryParams.FIRST_VISIT_TIMESTAMP));
        trackMe.trySet(QueryParams.TOTAL_NUMBER_OF_VISITS, mDefaultTrackMe.get(QueryParams.TOTAL_NUMBER_OF_VISITS));
        trackMe.trySet(QueryParams.PREVIOUS_VISIT_TIMESTAMP, mDefaultTrackMe.get(QueryParams.PREVIOUS_VISIT_TIMESTAMP));
    }

    /**
     * These parameters are required for all queries.
     */
    private void injectBaseParams(TrackMe trackMe) {
        trackMe.trySet(QueryParams.APP_ID, mSiteId);
        //trackMe.trySet(QueryParams.RECORD, DEFAULT_RECORD_VALUE);
       // trackMe.trySet(QueryParams.API_VERSION, DEFAULT_API_VERSION_VALUE);
        //trackMe.trySet(QueryParams.RANDOM_NUMBER, mRandomAntiCachingValue.nextInt(100000));
        trackMe.trySet(QueryParams.DATETIME_OF_REQUEST, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
       // trackMe.trySet(QueryParams.SEND_IMAGE, "0");

        trackMe.trySet(QueryParams.VISITOR_ID, mDefaultTrackMe.get(QueryParams.VISITOR_ID));
        trackMe.trySet(QueryParams.USER_ID, mDefaultTrackMe.get(QueryParams.USER_ID));

        trackMe.trySet(QueryParams.VISIT_SCOPE_CUSTOM_VARIABLES, mVisitCustomVariable.toString());


    }

    private static String fixUrl(String url, String baseUrl) {
        if (url == null) url = baseUrl + "/";

        if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("ftp://")) {
            url = baseUrl + (url.startsWith("/") ? "" : "/") + url;
        }
        return url;
    }

    private CountDownLatch mSessionStartLatch = new CountDownLatch(0);

    /**
     * Track tracker.
     *
     * @param trackMe the track me
     * @return the tracker
     */
    public Tracker track(TrackMe trackMe) {
        boolean newSession;
        synchronized (mSessionLock) {
            newSession = tryNewSession();
            if (newSession)
                mSessionStartLatch = new CountDownLatch(1);
        }
        if (newSession) {
            injectInitialParams(trackMe);
        } else {
            try {
                // Another thread is currently creating a sessions first transmission, wait until it's done.
                mSessionStartLatch.await(mDispatcher.getConnectionTimeOut(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        injectBaseParams(trackMe);
        String event = Dispatcher.urlEncodeUTF8(trackMe.toMap());


        if (mUIUXAnalytics.isOptOut()) {
            mLastEvent = event;
            Timber.tag(LOGGER_TAG).d("URL omitted due to opt out: %s", event.toString());
        } else {
            mDispatcher.submit(event);
            Timber.tag(LOGGER_TAG).d("URL added to the queue: %s", event);
        }

        // we did a first transmission, let the other through.
        if (newSession)
            mSessionStartLatch.countDown();

        return this;
    }


    /**
     * A custom variable is a custom name-value pair that you can assign to your users or screen views,
     * and then visualize the reports of how many visits, conversions, etc. for each custom variable.
     * A custom variable is defined by a name — for example,
     * "User status" — and a value – for example, "LoggedIn" or "Anonymous".
     * You can track up to 5 custom variables for each user to your app.
     *
     * @param index this Integer accepts values from 1 to 5.              A given custom variable name must always be stored in the same "index" per session.              For example, if you choose to store the variable name = "Gender" in              index = 1 and you record another custom variable in index = 1, then the              "Gender" variable will be deleted and replaced with the new custom variable stored in index 1.
     * @param name  String defines the name of a specific Custom Variable such as "User type".
     * @param value String defines the value of a specific Custom Variable such as "Customer".
     * @return the visit custom variable
     */
    public Tracker setVisitCustomVariable(int index, String name, String value) {
        mVisitCustomVariable.put(index, name, value);
        return this;
    }

    /**
     * Gets shared preferences.
     *
     * @return the shared preferences
     */
    public SharedPreferences getSharedPreferences() {
        return mUIUXAnalytics.getSharedPreferences();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracker tracker = (Tracker) o;
        return mSiteId == tracker.mSiteId && mApiUrl.equals(tracker.mApiUrl);
    }

    @Override
    public int hashCode() {
        int result = mSiteId;
        result = 31 * result + mApiUrl.hashCode();
        return result;
    }


    /**
     * For testing purposes
     *
     * @return query of the event ?r=1&sideId=1..
     */
    @VisibleForTesting
    public String getLastEvent() {
        return mLastEvent;
    }

    /**
     * Clear last event.
     */
    @VisibleForTesting
    public void clearLastEvent() {
        mLastEvent = null;
    }

    /**
     * Gets dispatcher.
     *
     * @return the dispatcher
     */
    @VisibleForTesting
    public Dispatcher getDispatcher() {
        return mDispatcher;
    }
}

