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
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.uclab.mm.sl.uiux.analytics.tools.ActivityHelper;

import timber.log.Timber;

/**
 * The type Track helper.
 */
public class TrackHelper {
    private final TrackMe mBaseTrackMe;

    private TrackHelper() {
        this(null);
    }

    private TrackHelper(@Nullable TrackMe baseTrackMe) {
        if (baseTrackMe == null) baseTrackMe = new TrackMe();
        mBaseTrackMe = baseTrackMe;
    }

    /**
     * Track track helper.
     *
     * @return the track helper
     */
    public static TrackHelper track() {
        return new TrackHelper();
    }

    /**
     * Track track helper.
     *
     * @param base the base
     * @return the track helper
     */
    public static TrackHelper track(@NonNull TrackMe base) {
        return new TrackHelper(base);
    }

    /**
     * The type Base event.
     */
    static abstract class BaseEvent {

        private final TrackHelper mBaseBuilder;

        /**
         * Instantiates a new Base event.
         *
         * @param baseBuilder the base builder
         */
        BaseEvent(TrackHelper baseBuilder) {
            mBaseBuilder = baseBuilder;
        }

        /**
         * Gets base track me.
         *
         * @return the base track me
         */
        TrackMe getBaseTrackMe() {
            return mBaseBuilder.mBaseTrackMe;
        }

        /**
         * Build track me.
         *
         * @return the track me
         */
        @Nullable
        public abstract TrackMe build();


        /**
         * With.
         *
         * @param tracker the tracker
         */
        public void with(@NonNull Tracker tracker) {
            TrackMe trackMe = build();
            if (trackMe != null) tracker.track(trackMe);
        }
    }

    /**
     * To track a screenview.
     *
     * @param path Example: "/user/settings/billing"
     * @return an object that allows addition of further details.
     */
    public Screen screen(String path) {
        return new Screen(this, path);
    }

    /**
     * Calls {@link #screen(String)} for an activity.
     * Uses the activity-stack as path and activity title as names.
     *
     * @param activity the activity to track
     * @return the screen
     */
    public Screen screen(Activity activity) {
        String breadcrumbs = ActivityHelper.getBreadcrumbs(activity);
        return new Screen(this, ActivityHelper.breadcrumbsToPath(breadcrumbs)).title(breadcrumbs);
    }

    /**
     * The type Screen.
     */
    public static class Screen extends BaseEvent {
        private final String mPath;
        private final CustomVariables mCustomVariables = new CustomVariables();
        private String mTitle;

        /**
         * Instantiates a new Screen.
         *
         * @param baseBuilder the base builder
         * @param path        the path
         */
        Screen(TrackHelper baseBuilder, String path) {
            super(baseBuilder);
            mPath = path;
        }

        /**
         * The title of the action being tracked. It is possible to use slashes / to set one or several categories for this action.
         *
         * @param title Example: Help / Feedback will create the Action Feedback in the category Help.
         * @return this object to allow chaining calls
         */
        public Screen title(String title) {
            mTitle = title;
            return this;
        }

        /**
         * Just like {@link Tracker#setVisitCustomVariable(int, String, String)} but only valid per screen.
         * Only takes effect when setting prior to tracking the screen view.
         *
         * @param index the index
         * @param name  the name
         * @param value the value
         * @return the screen
         */
        public Screen variable(int index, String name, String value) {
            mCustomVariables.put(index, name, value);
            return this;
        }


        @Nullable
        @Override
        public TrackMe build() {
            if (mPath == null) return null;
            return new TrackMe(getBaseTrackMe())
                    .set(QueryParams.SCREEN_SCOPE_CUSTOM_VARIABLES, mCustomVariables.toString())
                    .set(QueryParams.ACTION_NAME, mTitle);
        }
    }


    /**
     * Events are a useful way to collect data about a user's interaction with interactive components of your app,
     * like button presses or the use of a particular item in a game.
     *
     * @param category (required) – this String defines the event category.                 You might define event categories based on the class of user actions,                 like clicks or gestures or voice commands, or you might define them based upon the                 features available in your application (play, pause, fast forward, etc.).
     * @param action   (required) this String defines the specific event action within the category specified.                 In the example, we are basically saying that the category of the event is user clicks,                 and the action is a button click.
     * @return an object that allows addition of further details.
     */
    public EventBuilder event(@NonNull String category, @NonNull String action) {
        return new EventBuilder(this, category, action);
    }

    /**
     * The type Event builder.
     */
    public static class EventBuilder extends BaseEvent {
        @NonNull private final String mCategory;
        @NonNull private final String mAction;
        private String mPath;
        private String mName;
        private Float mValue;

        /**
         * Instantiates a new Event builder.
         *
         * @param builder  the builder
         * @param category the category
         * @param action   the action
         */
        EventBuilder(TrackHelper builder, @NonNull String category, @NonNull String action) {
            super(builder);
            mCategory = category;
            mAction = action;
        }

        /**
         * The path under which this event occurred.
         * Example: "/user/settings/billing", if you pass NULL, the last path set by #trackScreenView will be used.
         *
         * @param path the path
         * @return the event builder
         */
        public EventBuilder path(String path) {
            mPath = path;
            return this;
        }

        /**
         * Defines a label associated with the event.
         * For example, if you have multiple Button controls on a screen, you might use the label to specify the specific View control identifier that was clicked.
         *
         * @param name the name
         * @return the event builder
         */
        public EventBuilder name(String name) {
            mName = name;
            return this;
        }

        /**
         * Defines a numeric value associated with the event.
         * For example, if you were tracking "Buy" button clicks, you might log the number of items being purchased, or their total cost.
         *
         * @param value the value
         * @return the event builder
         */
        public EventBuilder value(Float value) {
            mValue = value;
            return this;
        }

        @Nullable
        @Override
        public TrackMe build() {
            TrackMe trackMe = new TrackMe(getBaseTrackMe())
                    .set(QueryParams.EVENT_CATEGORY, mCategory)
                    .set(QueryParams.EVENT_ACTION, mAction)
                    .set(QueryParams.EVENT_NAME, mName);
            if (mValue != null) trackMe.set(QueryParams.EVENT_VALUE, mValue);
            return trackMe;
        }
    }


    /**
     * Caught exceptions are errors in your app for which you've defined exception handling code,
     * such as the occasional timeout of a network connection during a request for data.
     * <p/>
     * This is just a different way to define an event.
     * Keep in mind UIUXAnalytics is not a crash tracker, use this sparingly.
     * <p/>
     * For this to be useful you should ensure that proguard does not remove all classnames and line numbers.
     * Also note that if this is used across different app versions and obfuscation is used, the same exception might be mapped to different obfuscated names by proguard.
     * This would mean the same exception (event) is tracked as different events by UIUXAnalytics.
     *
     * @param throwable exception instance
     * @return the exception
     */
    public Exception exception(Throwable throwable) {
        return new Exception(this, throwable);
    }

    /**
     * The type Exception.
     */
    public static class Exception extends BaseEvent {
        private final Throwable mThrowable;
        private String mDescription;
        private boolean mIsFatal;

        /**
         * Instantiates a new Exception.
         *
         * @param baseBuilder the base builder
         * @param throwable   the throwable
         */
        Exception(TrackHelper baseBuilder, Throwable throwable) {
            super(baseBuilder);
            mThrowable = throwable;
        }

        /**
         * Description exception.
         *
         * @param description exception message
         * @return the exception
         */
        public Exception description(String description) {
            mDescription = description;
            return this;
        }

        /**
         * Fatal exception.
         *
         * @param isFatal true if it's fatal exception
         * @return the exception
         */
        public Exception fatal(boolean isFatal) {
            mIsFatal = isFatal;
            return this;
        }

        @Nullable
        @Override
        public TrackMe build() {
            String className;
            try {
                StackTraceElement trace = mThrowable.getStackTrace()[0];
                className = trace.getClassName() + "/" + trace.getMethodName() + ":" + trace.getLineNumber();
            } catch (java.lang.Exception e) {
                Timber.tag(Tracker.LOGGER_TAG).w(e, "Couldn't get stack info");
                className = mThrowable.getClass().getName();
            }
            String actionName = "exception/" + (mIsFatal ? "fatal/" : "") + (className + "/") + mDescription;
            return new TrackMe(getBaseTrackMe())
                    .set(QueryParams.ACTION_NAME, actionName)
                    .set(QueryParams.EVENT_CATEGORY, "Exception")
                    .set(QueryParams.EVENT_ACTION, className)
                    .set(QueryParams.EVENT_NAME, mDescription)
                    .set(QueryParams.EVENT_VALUE, mIsFatal ? 1 : 0);
        }
    }

    /**
     * This will create an exception handler that wraps any existing exception handler.
     * Exceptions will be caught, tracked, dispatched and then rethrown to the previous exception handler.
     * <p/>
     * Be wary of relying on this for complete crash tracking..
     * Think about how to deal with older app versions still throwing already fixed exceptions.
     * <p/>
     *
     * @return the uncaught exceptions
     */
    public UncaughtExceptions uncaughtExceptions() {
        return new UncaughtExceptions(this);
    }

    /**
     * The type Uncaught exceptions.
     */
    public static class UncaughtExceptions {
        private final TrackHelper mBaseBuilder;

        /**
         * Instantiates a new Uncaught exceptions.
         *
         * @param baseBuilder the base builder
         */
        UncaughtExceptions(TrackHelper baseBuilder) {
            mBaseBuilder = baseBuilder;
        }

        /**
         * With thread . uncaught exception handler.
         *
         * @param tracker the tracker that should receive the exception events.
         * @return returns the new (but already active) exception handler.
         */
        public Thread.UncaughtExceptionHandler with(Tracker tracker) {
            if (Thread.getDefaultUncaughtExceptionHandler() instanceof UIUXExceptionHandler) {
                throw new RuntimeException("Trying to wrap an existing UIUXExceptionHandler.");
            }
            Thread.UncaughtExceptionHandler handler = new UIUXExceptionHandler(tracker, mBaseBuilder.mBaseTrackMe);
            Thread.setDefaultUncaughtExceptionHandler(handler);
            return handler;
        }
    }

    /**
     * This method will bind a tracker to your application,
     * causing it to automatically track Activities with {@link #screen(Activity)} within your app.
     *
     * @param app your app
     * @return the registered callback, you need this if you wanted to unregister the callback again
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public AppTracking screens(Application app) {
        return new AppTracking(this, app);
    }

    /**
     * The type App tracking.
     */
    public static class AppTracking {
        private final Application mApplication;
        private final TrackHelper mBaseBuilder;

        /**
         * Instantiates a new App tracking.
         *
         * @param baseBuilder the base builder
         * @param application the application
         */
        public AppTracking(TrackHelper baseBuilder, Application application) {
            mBaseBuilder = baseBuilder;
            mApplication = application;
        }

        /**
         * With application . activity lifecycle callbacks.
         *
         * @param tracker the tracker to use
         * @return the registered callback, you need this if you wanted to unregister the callback again
         */
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public Application.ActivityLifecycleCallbacks with(final Tracker tracker) {
            final Application.ActivityLifecycleCallbacks callback = new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                    mBaseBuilder.screen(activity).with(tracker);
                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {
                    if (activity != null && activity.isTaskRoot()) {
                        tracker.dispatch();
                    }
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            };
            mApplication.registerActivityLifecycleCallbacks(callback);
            return callback;
        }
    }
}
