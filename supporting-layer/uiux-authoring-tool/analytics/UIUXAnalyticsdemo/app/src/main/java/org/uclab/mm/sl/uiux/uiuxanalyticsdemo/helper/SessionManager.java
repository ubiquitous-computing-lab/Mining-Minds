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
package org.uclab.mm.sl.uiux.uiuxanalyticsdemo.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = "AndroidLogin";
	
	private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
	private static final String USER_NAME = "username";
	private static final String USER_EMAIL = "useremail";

	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void setLogin(boolean isLoggedIn) {

		editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
		// commit changes
		editor.commit();

		Log.d(TAG, "User login session modified!");
	}

	public  void setUserdetails(String name, String email)
	{
		editor.putString(USER_NAME, name);
		editor.putString(USER_EMAIL, email);
		// commit changes
		editor.commit();
	}
	
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);
	}

	public String getUserEmail()
	{
		return pref.getString(USER_EMAIL, null);
	}
	public String getUserName()
	{
		return pref.getString(USER_NAME,null);
	}
}
