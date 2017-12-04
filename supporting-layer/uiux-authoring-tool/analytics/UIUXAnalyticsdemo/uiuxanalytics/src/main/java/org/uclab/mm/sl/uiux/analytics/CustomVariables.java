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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import timber.log.Timber;

/**
 * You can track up to 5 custom variables for each user to your app,
 * and up to 5 custom variables for each screen view.
 * <p/>
 * Desired json output:
 * {
 * "1":["OS","iphone 5.0"],
 * "2":["UIUXAnalytics Mobile Version","1.6.2"],
 * "3":["Locale","en::en"],
 * "4":["Num Accounts","2"],
 * "5":["Level","over9k"]
 * }
 */
public class CustomVariables {
    private final Map<String, JSONArray> mVars = new ConcurrentHashMap<>();

    private static final String LOGGER_TAG = UIUXAnalytics.LOGGER_PREFIX + "CustomVariables";
    /**
     * The constant MAX_LENGTH.
     */
    protected static final int MAX_LENGTH = 200;

    /**
     * Instantiates a new Custom variables.
     */
    public CustomVariables() {

    }

    /**
     * Instantiates a new Custom variables.
     *
     * @param variables the variables
     */
    public CustomVariables(CustomVariables variables) {
        mVars.putAll(variables.mVars);
    }

    /**
     * Custom variable names and values are limited to 200 characters in length each.
     *
     * @param index this Integer accepts values from 1 to 5.              A given custom variable name must always be stored in the same "index" per session.              For example, if you choose to store the variable name = "Gender" in index = 1              and you record another custom variable in index = 1, then the "Gender" variable              will be deleted and replaced with the new custom variable stored in index 1.              You may configure UIUXAnalytics to track more custom variables than 5.
     * @param name  of a specific Custom Variable such as "User type".
     * @param value of a specific Custom Variable such as "Customer".
     * @return super.put result if index in right range and name/value pair aren't null
     */
    public JSONArray put(int index, String name, String value) {
        if (index > 0 && name != null & value != null) {

            if (name.length() > MAX_LENGTH) {
                Timber.tag(LOGGER_TAG).w("Name is too long %s", name);
                name = name.substring(0, MAX_LENGTH);
            }

            if (value.length() > MAX_LENGTH) {
                Timber.tag(LOGGER_TAG).w("Value is too long %s", value);
                value = value.substring(0, MAX_LENGTH);
            }

            return put(Integer.toString(index), new JSONArray(Arrays.asList(name, value)));
        }
        Timber.tag(LOGGER_TAG).w("Index is out of range or name/value is null");
        return null;
    }

    /**
     * Put json array.
     *
     * @param index  index accepts values from 1 to 5.
     * @param values packed key/value pair
     * @return super.put result or null if key is null or value length is not equals 2
     */
    public JSONArray put(String index, JSONArray values) {
        if (values.length() != 2 || index == null) {
            Timber.tag(LOGGER_TAG).w("values.length() should be equal 2");
            return null;
        }
        return mVars.put(index, values);
    }

    public String toString() {
        JSONObject json = new JSONObject(mVars);
        return json.length() > 0 ? json.toString() : null;
    }

}
