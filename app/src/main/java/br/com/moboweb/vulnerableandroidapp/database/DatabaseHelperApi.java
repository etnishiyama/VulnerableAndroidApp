package br.com.moboweb.vulnerableandroidapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.moboweb.vulnerableandroidapp.model.UserModel;

/**
 * Created by hulk on 08/04/17.
 */

public class DatabaseHelperApi {
    private SystemDatabaseHelper mDbHelper;
    private SQLiteDatabase mWritableDatabase;
    private Context mContext;

    public DatabaseHelperApi(Context context) {
        mContext = context;
        mDbHelper = new SystemDatabaseHelper(mContext);
        mWritableDatabase = mDbHelper.getWritableDatabase();
    }

    public boolean saveUser(UserModel user) {
        ContentValues values = new ContentValues();
        values.put(SystemContract.UserEntry.COLUMN_NAME_NAME, user.name);
        values.put(SystemContract.UserEntry.COLUMN_NAME_EMAIL, user.email);
        values.put(SystemContract.UserEntry.COLUMN_NAME_USERNAME, user.username);
        values.put(SystemContract.UserEntry.COLUMN_NAME_PASSWORD, user.password);

        long newRowId = mWritableDatabase.insert(SystemContract.UserEntry.TABLE_NAME, null, values);

        return (newRowId > 0)? true: false;
    }

    public boolean saveClockin(String user_id, String clockin) {
        ContentValues values = new ContentValues();
        values.put(SystemContract.ClockEntry.COLUMN_NAME_CLOCKIN, clockin);
        values.put(SystemContract.ClockEntry.COLUMN_NAME_USER_ID, user_id);

        long newRowId = mWritableDatabase.insert(SystemContract.ClockEntry.TABLE_NAME, null, values);

        return (newRowId > 0)? true: false;
    }
}
