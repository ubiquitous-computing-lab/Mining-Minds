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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.uclab.mm.sl.uiux.analytics.TrackHelper;
import org.uclab.mm.sl.uiux.analytics.Tracker;
import org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper.AppConfig;
import org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper.HttpHandler;
import org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper.SessionManager;


import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager session;
    private static String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = AppConfig.URL_FEEDS;

    ArrayList<HashMap<String, String>> recommendationList;

    private Tracker getTracker() {

        ((MyApplication) getApplication()).getTracker()
                .setUserId("5");
        return (  (MyApplication) getApplication()).getTracker();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TrackHelper.track().screen("/").title("Main screen").with(getTracker());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);

        // session manager
        session = new SessionManager(getApplicationContext());

        TextView nameUser = (TextView) header.findViewById(R.id.userName);
        nameUser.setText(session.getUserName());
        //nameUser.setText(mDailySpecial);
        TextView emailUser = (TextView) header.findViewById(R.id.userEmail);
        emailUser.setText(session.getUserEmail());
        recommendationList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);
        new GetRecommendation().execute();

        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Intent intentSetting=new Intent(this, SettingsActivity.class);
            // startActivity(intentSetting);
        }

        if (id == R.id.action_logout) {
            logoutUser();
        }

        if (id == R.id.action_graphs) {

            //Intent intentGraphs=new Intent(this, GraphsActivity.class);
            //startActivity(intentGraphs);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            TrackHelper.track().exception(new Exception("OnPurposeException")).description("Crash button").fatal(false).with(getTracker());

        } else if (id == R.id.nav_favorites) {
            TrackHelper.track().event("Side Menu", "action").name("Favorites Button Click").value(10f).with(getTracker());

        }else if (id == R.id.nav_feedback) {

            userFeedback();

            TrackHelper.track().event("Side Menu", "action").name("Feedback Button Click").value(10f).with(getTracker());


        }
        else if (id == R.id.nav_profile) {

            TrackHelper.track().event("Side Menu", "action").name("Profile Button Click").value(10f).with(getTracker());
        }
        else {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {

        session.setLogin(false);
        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void userFeedback()
    {
        AlertDialog userfeedback = new AlertDialog.Builder(this).create();

        // Setting Dialog Title
        userfeedback.setTitle("Feedback");

        // Setting Icon to Dialog
        userfeedback.setIcon(R.drawable.ic_feedback_black_18dp);


        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        userfeedback.setView(inflater.inflate(R.layout.feedback, null));

        // Setting OK Button
        userfeedback.setButton("Send",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // Write your code here to execute after dialog
                        // closed
                        Toast.makeText(getApplicationContext(),
                                "Your Feedback sent Successfully", Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        // Showing Alert Message
        userfeedback.show();
    }

    private class GetRecommendation extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("results");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);



                        String title = c.getString("title");
                        String recommedationt_text  = c.getString("recommedationt_text");


                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value

                        contact.put("title", title);
                        contact.put("recommedationt_text", recommedationt_text);

                        Log.d("contacts are the",contact.toString());

                        // adding contact to contact list
                        recommendationList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());

                    TrackHelper.track().exception(new Exception("JSONException")).description("Json parsing error").fatal(false).with(getTracker());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, recommendationList,
                    R.layout.list_item, new String[]{"title", "recommedationt_text"}, new int[]{
                    R.id.titleid, R.id.recommendationid});

            lv.setAdapter(adapter);
        }

    }



}
