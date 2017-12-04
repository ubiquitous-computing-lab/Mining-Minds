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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import org.uclab.mm.sl.uiux.analytics.TrackHelper;
import org.uclab.mm.sl.uiux.analytics.Tracker;

public class TermsAndConditionActivity extends AppCompatActivity {

    CheckBox agree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        agree = (CheckBox) findViewById(R.id.chkboxagreeid);


    }

    public void newUserForm(View view){
        if(agree.isChecked()){
            Intent i = new Intent(getApplicationContext(), NewUserActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"You are not agree to the terms and conditions", Toast.LENGTH_SHORT).show();
        }
    }
}
