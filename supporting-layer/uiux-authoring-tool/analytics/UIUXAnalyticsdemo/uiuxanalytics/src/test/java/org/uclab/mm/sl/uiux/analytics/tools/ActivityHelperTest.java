package org.uclab.mm.sl.uiux.analytics.tools;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by jamil on 12/23/2016.
 */
public class ActivityHelperTest  extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getTestTitle());
    }
    public static String getTestTitle(){
        return "Test Activity";
    }



}