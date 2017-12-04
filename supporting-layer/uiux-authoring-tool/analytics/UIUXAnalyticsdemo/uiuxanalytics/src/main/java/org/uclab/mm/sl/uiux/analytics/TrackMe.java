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

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This objects represents one query to UIUXAnalytics.
 * For each event send to UIUXAnalytics a TrackMe gets created, either explicitly by you or implicitly by the Tracker.
 */
public class TrackMe {
    private static final int DEFAULT_QUERY_CAPACITY = 14;
    private final HashMap<String, String> mQueryParams = new HashMap<>(DEFAULT_QUERY_CAPACITY);

    /**
     * Instantiates a new Track me.
     */
    public TrackMe() {
    }

    /**
     * Instantiates a new Track me.
     *
     * @param trackMe the track me
     */
    public TrackMe(TrackMe trackMe) {
        mQueryParams.putAll(trackMe.mQueryParams);
    }

    /**
     * Set track me.
     *
     * @param key   the key
     * @param value the value
     * @return the track me
     */
    protected synchronized TrackMe set(@NonNull String key, String value) {
        if (value == null)
            mQueryParams.remove(key);
        else if (value.length() > 0)
            mQueryParams.put(key, value);
        return this;
    }

    /**
     * You can set any additional Tracking API Parameters within the SDK.
     * This includes for example the local time (parameters h, m and s).
     * <pre>
     * set(QueryParams.HOURS, "10");
     * set(QueryParams.MINUTES, "45");
     * set(QueryParams.SECONDS, "30");
     * </pre>
     *
     * @param key   query params name
     * @param value value
     * @return tracker instance
     */
    public synchronized TrackMe set(@NonNull QueryParams key, String value) {
        set(key.toString(), value);
        return this;
    }

    /**
     * Set track me.
     *
     * @param key   the key
     * @param value the value
     * @return the track me
     */
    public synchronized TrackMe set(@NonNull QueryParams key, int value) {
        set(key, Integer.toString(value));
        return this;
    }

    /**
     * Set track me.
     *
     * @param key   the key
     * @param value the value
     * @return the track me
     */
    public synchronized TrackMe set(@NonNull QueryParams key, float value) {
        set(key, Float.toString(value));
        return this;
    }

    /**
     * Set track me.
     *
     * @param key   the key
     * @param value the value
     * @return the track me
     */
    public synchronized TrackMe set(@NonNull QueryParams key, long value) {
        set(key, Long.toString(value));
        return this;
    }

    /**
     * Has boolean.
     *
     * @param queryParams the query params
     * @return the boolean
     */
    public synchronized boolean has(@NonNull QueryParams queryParams) {
        return mQueryParams.containsKey(queryParams.toString());
    }

    /**
     * Only sets the value if it doesn't exist.
     *
     * @param key   type
     * @param value value
     * @return this (for chaining)
     */
    public synchronized TrackMe trySet(@NonNull QueryParams key, int value) {
        return trySet(key, String.valueOf(value));
    }

    /**
     * Only sets the value if it doesn't exist.
     *
     * @param key   type
     * @param value value
     * @return this (for chaining)
     */
    public synchronized TrackMe trySet(@NonNull QueryParams key, float value) {
        return trySet(key, String.valueOf(value));
    }

    /**
     * Try set track me.
     *
     * @param key   the key
     * @param value the value
     * @return the track me
     */
    public synchronized TrackMe trySet(@NonNull QueryParams key, long value) {
        return trySet(key, String.valueOf(value));
    }

    /**
     * Only sets the value if it doesn't exist.
     *
     * @param key   type
     * @param value value
     * @return this (for chaining)
     */
    public synchronized TrackMe trySet(@NonNull QueryParams key, String value) {
        if (!has(key))
            set(key, value);
        return this;
    }

    /**
     * The tracker calls this to build the final query to be sent via HTTP
     *
     * @return the parameter map, but without the base URL
     */
    public synchronized Map<String, String> toMap() {
        return new HashMap<>(mQueryParams);
    }

    /**
     * Get string.
     *
     * @param queryParams the query params
     * @return the string
     */
    public synchronized String get(@NonNull QueryParams queryParams) {
        return mQueryParams.get(queryParams.toString());
    }

}
