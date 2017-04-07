package br.com.moboweb.vulnerableandroidapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Everton Takashi Nishiyama <everton.nishiyama@venturus.org.br>
 * @version 1.0
 * @since 4/7/17
 */

public class SystemDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ClockSystem.db";

    public SystemDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SystemContract.SQL_CREATE_USER_ENTRIES);
        db.execSQL(SystemContract.SQL_CREATE_CLOCK_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SystemContract.SQL_DELETE_USER_ENTRIES);
        db.execSQL(SystemContract.SQL_DELETE_CLOCK_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}