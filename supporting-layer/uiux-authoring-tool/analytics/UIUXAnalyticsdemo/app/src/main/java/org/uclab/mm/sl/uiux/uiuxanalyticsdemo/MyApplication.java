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

*/


package org.uclab.mm.sl.uiux.uiuxanalyticsdemo;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.uclab.mm.sl.uiux.analytics.UIUXAnalytics;
import org.uclab.mm.sl.uiux.analytics.Tracker;
import org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper.AppConfig;


import java.net.MalformedURLException;


public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();
    private Tracker UIUXTracker;

    private static MyApplication mInstance;

    private String URL_TRACKER = AppConfig.URL_TRACKER;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public synchronized Tracker getTracker() {
        if (UIUXTracker != null) {
            return UIUXTracker;
        }

        try {
            UIUXTracker = UIUXAnalytics.getInstance(this).newTracker(URL_TRACKER, 1);
        } catch (MalformedURLException e) {
           // Log.w(Tracker.LOGGER_TAG, "url is malformed", e);
            return null;
        }

        return UIUXTracker;
    }


}