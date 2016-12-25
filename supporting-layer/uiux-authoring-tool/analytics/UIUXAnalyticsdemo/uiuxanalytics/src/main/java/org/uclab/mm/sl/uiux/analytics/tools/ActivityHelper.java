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
 *  @Author: Jamil Hussain
 *   This is the extension of PIWIK android SDK @link https://github.com/piwik/piwik-android-sdk for sending data to custom Analytics engine developed in Django
*/

package org.uclab.mm.sl.uiux.analytics.tools;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Activity helper.
 */
public class ActivityHelper {


    /**
     * Gets breadcrumbs.
     *
     * @param activity the activity
     * @return the breadcrumbs
     */
    public static String getBreadcrumbs(final Activity activity) {
        Activity currentActivity = activity;
        ArrayList<String> breadcrumbs = new ArrayList<>();

        while (currentActivity != null) {
            breadcrumbs.add(currentActivity.getTitle().toString());
            currentActivity = currentActivity.getParent();
        }
        return joinSlash(breadcrumbs);
    }

    /**
     * Join slash string.
     *
     * @param sequence the sequence
     * @return the string
     */
    public static String joinSlash(List<String> sequence) {
        if (sequence != null && sequence.size() > 0) {
            return TextUtils.join("/", sequence);
        }
        return "";
    }

    /**
     * Breadcrumbs to path string.
     *
     * @param breadcrumbs the breadcrumbs
     * @return the string
     */
    public static String breadcrumbsToPath(String breadcrumbs) {
        return breadcrumbs.replaceAll("\\s", "");
    }
}
