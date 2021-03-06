package br.com.moboweb.vulnerableandroidapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;

    private static final String KEY_TOKEN = "key_token";
    private static final String KEY_USER = "key_user";

    public MySharedPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveApplicationToken(String token) {
        SharedPreferences.Editor editor =  mSharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public void saveUser(UserModel user) {
        SharedPreferences.Editor editor =  mSharedPreferences.edit();
        editor.putString(KEY_USER, user._id);
        editor.commit();
    }

    public String getUserId() {
        return mSharedPreferences.getString(KEY_USER, null);
    }

}
