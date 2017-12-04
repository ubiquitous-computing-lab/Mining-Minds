package org.uclab.mm.sl.uiux.analytics.tools;

import org.junit.Before;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.uclab.mm.sl.uiux.analytics.Tracker;
import org.uclab.mm.sl.uiux.analytics.UIUXAnalytics;

import java.net.MalformedURLException;

@Config(emulateSdk = 18, manifest = Config.NONE)
public abstract class DefaultTestCase {
    public Tracker createTracker() throws MalformedURLException {
        UIUXTestApplication app = (UIUXTestApplication) Robolectric.application;
        return UIUXAnalytics.getInstance(Robolectric.application).newTracker(app.getTrackerUrl(), app.getSiteId());
    }

    public UIUXAnalytics getPiwik() {
        return UIUXAnalytics.getInstance(Robolectric.application);
    }

    @Before
    public void setup() {
        UIUXAnalytics.getInstance(Robolectric.application).setDryRun(true);
        UIUXAnalytics.getInstance(Robolectric.application).setOptOut(true);
        UIUXAnalytics.getInstance(Robolectric.application).getSharedPreferences().edit().clear().apply();
    }
}
