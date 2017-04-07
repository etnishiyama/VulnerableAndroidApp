package br.com.moboweb.vulnerableandroidapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class LoginModel {
    public String success;
    public String token;

    @SerializedName("login_id")
    public String login_id;

    @Override
    public String toString() {
        return(token);
    }
}
