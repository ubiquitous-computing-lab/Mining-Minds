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

import android.app.Application;
import android.os.Build;

import java.net.MalformedURLException;

/**
 * The type Uiux application.
 */
public abstract class UIUXApplication extends Application {
    private Tracker mPiwikTracker;

    /**
     * Gets piwik.
     *
     * @return the piwik
     */
    public UIUXAnalytics getPiwik() {
        return UIUXAnalytics.getInstance(this);
    }

    /**
     * Gives you an all purpose thread-safe persisted Tracker object.
     *
     * @return a shared Tracker
     */
    public synchronized Tracker getTracker() {
        if (mPiwikTracker == null) {
            try {
                mPiwikTracker = getPiwik().newTracker(getTrackerUrl(), getSiteId());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Tracker URL was malformed.");
            }
        }
        return mPiwikTracker;
    }

    /**
     * The URL of your remote Piwik server.
     *
     * @return the tracker url
     */
    public abstract String getTrackerUrl();

    /**
     * The siteID you specified for this application in Piwik.
     *
     * @return the site id
     */
    public abstract Integer getSiteId();


    @Override
    public void onLowMemory() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH && mPiwikTracker != null) {
            mPiwikTracker.dispatch();
        }
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        if ((level == TRIM_MEMORY_UI_HIDDEN || level == TRIM_MEMORY_COMPLETE) && mPiwikTracker != null) {
            mPiwikTracker.dispatch();
        }
        super.onTrimMemory(level);
    }

}
