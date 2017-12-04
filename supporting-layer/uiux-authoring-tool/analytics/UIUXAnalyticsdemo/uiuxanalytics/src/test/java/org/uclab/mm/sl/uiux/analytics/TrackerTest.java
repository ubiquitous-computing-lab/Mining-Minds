package org.uclab.mm.sl.uiux.analytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.uclab.mm.sl.uiux.analytics.tools.DefaultTestCase;
import org.uclab.mm.sl.uiux.analytics.tools.FullEnvTestRunner;

import java.net.MalformedURLException;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jamil on 12/23/2016.
 */

@Config(emulateSdk = 18, manifest = Config.NONE)
@RunWith(FullEnvTestRunner.class)
public class TrackerTest extends DefaultTestCase {



    @Test
    public void getSiteId() throws Exception {

        assertEquals(createTracker().getSiteId(), 1);
    }


    @Test
    public void setSessionTimeout() throws Exception {


        Tracker tracker = createTracker();
        tracker.setSessionTimeout(10000);

        TrackHelper.track().screen("test").with(tracker);
        assertFalse(tracker.tryNewSession());

        tracker.setSessionTimeout(0);
        Thread.sleep(1, 0);
        assertTrue(tracker.tryNewSession());

        tracker.setSessionTimeout(10000);
        assertFalse(tracker.tryNewSession());
        assertEquals(tracker.getSessionTimeout(), 10000);

    }


    @Test
    public void setDispatchTimeout() throws Exception {

        Tracker tracker = createTracker();
        tracker.setDispatchTimeout(1337);

        assertEquals(1337, tracker.getDispatcher().getConnectionTimeOut());
        assertEquals(1337, tracker.getDispatchTimeout());

    }

    @Test
    public void dispatch() throws Exception {

    }

    @Test
    public void setDispatchInterval() throws Exception {

        Tracker tracker = createTracker();
        tracker.setDispatchInterval(1);
        assertEquals(tracker.getDispatchInterval(), 1);

    }

    @Test
    public void getDispatchInterval() throws Exception {

    }

    @Test
    public void setUserId() throws Exception {


        Tracker tracker = createTracker();
        assertNotNull(tracker.getDefaultTrackMe().get(QueryParams.USER_ID));

        tracker.setUserId("test");
        assertEquals(tracker.getUserId(), "test");

        tracker.setUserId("");
        assertEquals(tracker.getUserId(), "test");

        tracker.setUserId(null);
        assertNull(tracker.getUserId());

        String uuid = UUID.randomUUID().toString();
        tracker.setUserId(uuid);
        assertEquals(uuid, tracker.getUserId());
        assertEquals(uuid, createTracker().getUserId());

    }


    @Test
    public void setVisitorId() throws Exception {

        Tracker tracker = createTracker();
        String visitorId = "0123456789abcdef";
        tracker.setVisitorId(visitorId);
        assertEquals(visitorId, tracker.getVisitorId());
        TrackMe trackMe = new TrackMe();
        tracker.track(trackMe);
        assertTrue(tracker.getLastEvent().contains("_id"));
    }
    @Test
    public void SetUserAgent() throws MalformedURLException {
        String defaultUserAgent = "aUserAgent";
        String customUserAgent = "Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0";
        System.setProperty("http.agent", "aUserAgent");

        // Default system user agent
        Tracker tracker = createTracker();
        TrackMe trackMe = new TrackMe();
        tracker.track(trackMe);
        assertEquals(defaultUserAgent, trackMe.get(QueryParams.USER_AGENT));

        // Custom developer specified useragent
        tracker = createTracker();
        trackMe = new TrackMe();
        trackMe.set(QueryParams.USER_AGENT, customUserAgent);
        tracker.track(trackMe);
        assertEquals(customUserAgent, trackMe.get(QueryParams.USER_AGENT));

        // Modifying default TrackMe, no USER_AGENT
        tracker = createTracker();
        trackMe = new TrackMe();
        tracker.getDefaultTrackMe().set(QueryParams.USER_AGENT, null);
        tracker.track(trackMe);
        assertEquals(null, trackMe.get(QueryParams.USER_AGENT));
    }



}