package com.nichias.utils._pref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.nichias.utils.MyApplication;


public class SessionManager {
    private static final SessionManager ourInstance = new SessionManager();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return ourInstance;
    }

    /**
     * Insert all the shared preference values here
     * @param context
     * @param KEY
     * @param value
     */
    public void insertIntoPreference(Context context, String KEY, String value) {
        SharedPreferences pref = context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, AppCompatActivity.MODE_PRIVATE);
        pref.edit().putString(KEY, value).apply();
    }

    /**
     * Get shared preference values
     * @param KEY
     * @return
     */
    public String getFromPreference(String KEY) {
        SharedPreferences prefs = MyApplication.context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, AppCompatActivity.MODE_PRIVATE);
        return prefs.getString(KEY, "");
    }

    public void Logout() {
        SharedPreferences prefs = MyApplication.context.getSharedPreferences(SharedPrefConstants.APP_PREFERENCE_NAME, AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }


}
