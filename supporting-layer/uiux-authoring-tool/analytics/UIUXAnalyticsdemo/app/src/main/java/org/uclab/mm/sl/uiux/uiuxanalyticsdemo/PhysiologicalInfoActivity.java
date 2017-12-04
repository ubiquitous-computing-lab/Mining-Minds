/*
Copyright [2016] [name of copyright owner <-Put your name]

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

*/
package org.uclab.mm.sl.uiux.uiuxanalyticsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONObject;
import org.uclab.mm.sl.uiux.analytics.TrackHelper;
import org.uclab.mm.sl.uiux.analytics.Tracker;
import org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper.AppConfig;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhysiologicalInfoActivity extends AppCompatActivity {

    RadioButton vsnormal, vsmedium, vslow, hsnormal, hsmedium, hslow, tsnormal, tsmedium, tslow;

    String name, dateofbirth, heightfeet, heightinch, weight, email, conemail, password, conpassword, gender, visionstate=null, hearingstate=null, touchstate="ts";
    int heightfe, heightin, weigh;


    public static final int DEFAULT_CONNECTION_TIMEOUT = 5 * 1000;  // 5s
    private volatile int mTimeOut = DEFAULT_CONNECTION_TIMEOUT;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physiological_info);



        Intent i = this.getIntent();
        if (i != null) {
            name = i.getExtras().getString("name");
            dateofbirth = i.getExtras().getString("dateofbirth");
            heightfeet = i.getExtras().getString("heightfeet");
            heightfe = Integer.parseInt(heightfeet);
            heightinch = i.getExtras().getString("heightinch");
            heightin = Integer.parseInt(heightinch);
            weight = i.getExtras().getString("weight");
            weigh = Integer.parseInt(weight);
            email = i.getExtras().getString("email");
            conemail = i.getExtras().getString("conemail");
            password = i.getExtras().getString("password");
            conpassword = i.getExtras().getString("conpassword");
            gender = i.getExtras().getString("gender");
        }

        Toast.makeText(getApplicationContext(), "" + name + " " + dateofbirth + "  " + heightfeet + " " + heightinch + " " + weight + " " + " " + email +" "+conemail+ " " + password + " " + conpassword + " " + gender,
                Toast.LENGTH_SHORT).show();
        vsnormal = (RadioButton) findViewById(R.id.rbvsnormalid);
        vsmedium = (RadioButton) findViewById(R.id.rbvsmediumid);
        vslow = (RadioButton) findViewById(R.id.rbvslowid);


        hsnormal = (RadioButton) findViewById(R.id.rbhsnormalid);
        hsmedium = (RadioButton) findViewById(R.id.rbhsmediumid);
        hslow = (RadioButton) findViewById(R.id.rbhslowid);


        tsnormal = (RadioButton) findViewById(R.id.rbtsnormalid);
        tsmedium = (RadioButton) findViewById(R.id.rbtsmediumid);
        tslow = (RadioButton) findViewById(R.id.rbtslowid);


    }

    public void register(View view) {
        // Toast.makeText(getApplicationContext(), "Register Button is Clicked", Toast.LENGTH_SHORT).show();
        if(vsnormal.isChecked()){
            visionstate = "normal";
        }
        else if(vsmedium.isChecked()){
            visionstate = "medium";
        }
        else if (vslow.isChecked()){
            visionstate = "low";
        }

        if(hsnormal.isChecked()){
            hearingstate = "normal";
        }
        else if(hsmedium.isChecked()){
            hearingstate = "medium";
        }
        else if (hslow.isChecked()){
            hearingstate = "low";
        }

        if(tsnormal.isChecked()){
            touchstate = "normal";
        }
        else if(tsmedium.isChecked()){
            touchstate = "medium";
        }
        else if (tslow.isChecked()){
            touchstate = "low";
        }

        sendJson(name, email, conemail, password, gender, heightfe, weigh, visionstate, hearingstate, touchstate);

    }

    protected void sendJson(final String nm, final String em, final String conem, final String pass, final String gend, final int height, final int wgh, final String vs, final String hs, final String ts){
        Thread t = new Thread(){
            public void run(){
                Looper.prepare(); //For Preparing Message Pool for the child Thread

                JSONObject json = new JSONObject();

                Toast.makeText(getApplicationContext(),""+vs+hs+ts, Toast.LENGTH_SHORT).show();

                try {
                    String apiURlstring= AppConfig.URL_REGISTER;
                    URL apiurl= new URL(apiURlstring);
                    json.put("username", nm);
                    json.put("email",em );
                    json.put("email2", conem);
                    json.put("password",pass );
                  //  json.put("date_of_birth", dob);
                    json.put("gender", gend);
                    json.put("height", height);
                    json.put("weight", wgh);
                    json.put("user_sight", vs);
                    json.put("user_hearing", hs);
                    json.put("user_touch", ts);

                    HttpURLConnection urlConnection = (HttpURLConnection) apiurl.openConnection();
                    urlConnection.setConnectTimeout(mTimeOut);
                    urlConnection.setReadTimeout(mTimeOut);
                    urlConnection.setRequestMethod("POST");
                        // POST
                        urlConnection.setDoOutput(true); // Forces post
                        urlConnection.setRequestProperty("Content-Type", "application/json");
                        urlConnection.setRequestProperty("charset", "utf-8");

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                        writer.write(json.toString());
                        writer.flush();
                        writer.close();

                    int statusCode = urlConnection.getResponseCode();

                    Log.d("Json ", json.toString());
                    if (statusCode == 200 ||statusCode == 201 ) {

                        Intent intent = new Intent(PhysiologicalInfoActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    Log.d("Status","Status code"+ statusCode );

                } catch (Exception e) {
                    // Broad but an analytics app shouldn't impact it's host app.
                    e.printStackTrace();
                }


                Looper.loop();
            }
        };
        t.start();
    }
}
