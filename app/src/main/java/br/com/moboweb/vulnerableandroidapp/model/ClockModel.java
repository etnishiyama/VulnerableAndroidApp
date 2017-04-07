package br.com.moboweb.vulnerableandroidapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class ClockModel {
    public String user_id;
    public Date timestamp;

    public ClockModel(String user_id, Date timestamp, String clock_id) {
        this.user_id = user_id;
        this.timestamp = timestamp;
        this.clock_id = clock_id;
    }

    @SerializedName("clock_id")
    public String clock_id;

    @Override
    public String toString() {
        return(timestamp.toString());
    }
}
