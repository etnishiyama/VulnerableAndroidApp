package br.com.moboweb.vulnerableandroidapp.data;

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

    private static final String KEY_TOKEN = "shared_key_token";

    public MySharedPreferences(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveApplicationToken(String token) {
        SharedPreferences.Editor editor =  mSharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

}
