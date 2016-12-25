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

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.net.MalformedURLException;


/**
 * The type Uiux analytics.
 */
public class UIUXAnalytics {
    /**
     * The constant LOGGER_PREFIX.
     */
    public static final String LOGGER_PREFIX = "PIWIK:";
    /**
     * The constant PREFERENCE_FILE_NAME.
     */
    public static final String PREFERENCE_FILE_NAME = "org.uxe.sdk";
    /**
     * The constant PREFERENCE_KEY_OPTOUT.
     */
    public static final String PREFERENCE_KEY_OPTOUT = "uxe.optout";
    private final Context mContext;
    private boolean mOptOut = false;
    private boolean mDryRun = false;

    private static UIUXAnalytics sInstance;
    private final SharedPreferences mSharedPreferences;

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static synchronized UIUXAnalytics getInstance(Context context) {
        if (sInstance == null)
            sInstance = new UIUXAnalytics(context);
        return sInstance;
    }

    private UIUXAnalytics(Context context) {
        mContext = context.getApplicationContext();
        mSharedPreferences = getContext().getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        mOptOut = getSharedPreferences().getBoolean(PREFERENCE_KEY_OPTOUT, false);
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    protected Context getContext() {
        return mContext;
    }

    /**
     * New tracker tracker.
     *
     * @param trackerUrl (required) Tracking HTTP API endpoint, for example, http://your-piwik-domain.tld/piwik.php
     * @param siteId     (required) id of site
     * @param authToken  (optional) could be null or valid auth token.
     * @return Tracker object
     * @throws MalformedURLException the malformed url exception
     * @deprecated Use {@link #newTracker(String, int)} as there are security concerns over the authToken.
     */
    @Deprecated
    public synchronized Tracker newTracker(@NonNull String trackerUrl, int siteId, String authToken) throws MalformedURLException {
        return new Tracker(trackerUrl, siteId, authToken, this);
    }

    /**
     * New tracker tracker.
     *
     * @param trackerUrl (required) Tracking HTTP API endpoint
     * @param siteId     (required) id of site
     * @return Tracker object
     * @throws MalformedURLException the malformed url exception
     */
    public synchronized Tracker newTracker(@NonNull String trackerUrl, int siteId) throws MalformedURLException {
        return new Tracker(trackerUrl, siteId, null, this);
    }

    /**
     * Use this to disable UIUXAnalytics, e.g. if the user opted out of tracking.
     * UIUXAnalytics will persist the choice and remain disable on next instance creation.</p>
     * The choice is stored in {@link #PREFERENCE_FILE_NAME} under the key {@link #PREFERENCE_KEY_OPTOUT}.
     *
     * @param optOut true to disable reporting
     */
    public void setOptOut(boolean optOut) {
        mOptOut = optOut;
        getSharedPreferences().edit().putBoolean(PREFERENCE_KEY_OPTOUT, optOut).apply();
    }

    /**
     * Is opt out boolean.
     *
     * @return true if UIUXAnalytics is currently disabled
     */
    public boolean isOptOut() {
        return mOptOut;
    }

    /**
     * Is dry run boolean.
     *
     * @return the boolean
     */
    public boolean isDryRun() {
        return mDryRun;
    }

    /**
     * The dryRun flag set to true prevents any data from being sent to UIUXAnalytics.
     * The dryRun flag should be set whenever you are testing or debugging an implementation and do not want
     * test data to appear in your UIUXAnalytics reports. To set the dry run flag, use:
     *
     * @param dryRun true if you don't want to send any data to uxe
     */
    public void setDryRun(boolean dryRun) {
        mDryRun = dryRun;
    }

    /**
     * Gets application domain.
     *
     * @return the application domain
     */
    public String getApplicationDomain() {
        return getContext().getPackageName();
    }

    /**
     * Returns the shared preferences used by UIUXAnalytics that are stored under {@link #PREFERENCE_FILE_NAME}
     *
     * @return UIUXAnalytics 's SharedPreferences instance
     */
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }
}
