package br.com.moboweb.vulnerableandroidapp.database;

import android.provider.BaseColumns;

/**
 * @author Everton Takashi Nishiyama <everton.nishiyama@venturus.org.br>
 * @version 1.0
 * @since 4/7/17
 */

public final class SystemContract {
    private SystemContract() {}

    /* Inner class that defines the user table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    /* Inner class that defines the clock table contents */
    public static class ClockEntry implements BaseColumns {
        public static final String TABLE_NAME = "clock";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_CLOCKIN = "clockin";
    }

    public static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UserEntry.COLUMN_NAME_NAME + " TEXT," +
                    UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    public static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;

    public static final String SQL_CREATE_CLOCK_ENTRIES =
            "CREATE TABLE " + ClockEntry.TABLE_NAME + " (" +
                    ClockEntry._ID + " INTEGER PRIMARY KEY," +
                    ClockEntry.COLUMN_NAME_USER_ID + " TEXT," +
                    ClockEntry.COLUMN_NAME_CLOCKIN + " DATETIME)";

    public static final String SQL_DELETE_CLOCK_ENTRIES =
            "DROP TABLE IF EXISTS " + ClockEntry.TABLE_NAME;
}

