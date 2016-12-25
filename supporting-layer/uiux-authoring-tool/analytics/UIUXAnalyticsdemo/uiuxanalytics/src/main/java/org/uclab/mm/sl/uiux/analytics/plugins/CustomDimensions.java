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
 * This is the extension of PIWIK android SDK @link https://github.com/piwik/piwik-android-sdk for sending data to custom Analytics engine developed in Django
*/
package org.uclab.mm.sl.uiux.analytics.plugins;

import org.uclab.mm.sl.uiux.analytics.TrackMe;
import org.uclab.mm.sl.uiux.analytics.UIUXAnalytics;

import timber.log.Timber;

/**
 * This plugins allows you to track any Custom Dimensions.
 * In order to use this functionality install and configure
 */
public class CustomDimensions extends TrackMe {
    /**
     * The constant LOGGER_TAG.
     */
    protected static final String LOGGER_TAG = UIUXAnalytics.LOGGER_PREFIX + "CustomDimensions";

    /**
     * This method sets a tracking API parameter dimension%dimensionId%=%dimensionValue%.
     * Eg dimension1=foo or dimension2=bar.
     * So the tracking API parameter starts with dimension followed by the set dimensionId.
     *
     * @param dimensionId    accepts values greater than 0
     * @param dimensionValue is limited to 255 characters
     * @return instance of CustomDimensions plugin
     */
    public synchronized CustomDimensions set(int dimensionId, String dimensionValue) {
        if (dimensionId < 1){
            Timber.tag(LOGGER_TAG).w("dimensionId should be great than 0");
            return this;
        }
        if (dimensionValue != null && dimensionValue.length() > 255){
            Timber.tag(LOGGER_TAG).w("dimensionValue will be truncated to 255 chars");
            dimensionValue = dimensionValue.substring(0, 255);
        }
        set("dimension" + dimensionId, dimensionValue);
        return this;
    }

}
