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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.uclab.mm.sl.uiux.analytics.TrackHelper;
import org.uclab.mm.sl.uiux.analytics.Tracker;

public class NewUserActivity extends AppCompatActivity {

    EditText edtname, edtdateofbirth, edtheightfeet, edtheightinch, edtweight, edtemail, edtconfemail, edtpassword, edtconfpassword;
    RadioButton rbtmale, rbtfemale;
    String name, dateofbirth, heightfeet, heightinch, weight, email, conemail, password, conpassword, gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtname = (EditText) findViewById(R.id.edtnameid);
        edtdateofbirth = (EditText) findViewById(R.id.edtdateid);
        edtheightfeet = (EditText) findViewById(R.id.edtheightid);
        edtheightinch = (EditText) findViewById(R.id.edtinchid);
        edtweight = (EditText) findViewById(R.id.edtweightid);
        edtemail = (EditText) findViewById(R.id.edtemailid);
        edtconfemail = (EditText) findViewById(R.id.edtconfirmemailid);
        edtpassword = (EditText) findViewById(R.id.edtpassid);
        edtconfpassword = (EditText) findViewById(R.id.edtconpassid);

        rbtmale = (RadioButton) findViewById(R.id.rbtmaleid);
        rbtfemale = (RadioButton) findViewById(R.id.rbtfemaleid);



    }

    public void newUserForm(View view){

        name = edtname.getText().toString();
        dateofbirth = edtdateofbirth.getText().toString();
        heightfeet=  edtheightfeet.getText().toString();
        heightinch = edtheightinch.getText().toString();
        weight = edtweight.getText().toString();
        email = edtemail.getText().toString();
        conemail = edtconfemail.getText().toString();
        password = edtpassword.getText().toString();
        conpassword = edtconfpassword.getText().toString();

        if(rbtmale.isChecked()){
            gender = "male";
        }
        else if(rbtfemale.isChecked()){
            gender = "female";
        }

        if(password.equals(conpassword)){

            Intent i = new Intent(getApplicationContext(), PhysiologicalInfoActivity.class);

            i.putExtra("name", name);
            i.putExtra("dateofbirth", dateofbirth);
            i.putExtra("heightfeet", heightfeet);
            i.putExtra("heightinch", heightinch);
            i.putExtra("weight", weight);
            i.putExtra("email", email);
            i.putExtra("conemail", conemail);
            i.putExtra("password", password);
            i.putExtra("conpassword", conpassword);
            i.putExtra("gender", gender);

            startActivity(i);

        }
        else{
            Toast.makeText(getApplicationContext(),"Password does not match", Toast.LENGTH_SHORT).show();
        }


    }
}
