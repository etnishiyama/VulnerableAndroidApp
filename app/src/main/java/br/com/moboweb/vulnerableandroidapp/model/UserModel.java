package br.com.moboweb.vulnerableandroidapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class UserModel {
    public String username;
    public String password;
    public String name;
    public String email;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("_id")
    public String _id;

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", user_id='" + _id + '\'' +
                '}';
    }
}
