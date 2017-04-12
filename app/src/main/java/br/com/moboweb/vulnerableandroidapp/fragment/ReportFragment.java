package br.com.moboweb.vulnerableandroidapp.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.moboweb.vulnerableandroidapp.R;
import br.com.moboweb.vulnerableandroidapp.database.SystemContract;
import br.com.moboweb.vulnerableandroidapp.database.SystemDatabaseHelper;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class ReportFragment extends Fragment {
    public static final String TAG = "ReportFragment";
    private ListView mReportsListView;
    private ArrayAdapter mReportsArrayAdapter;
    private ArrayList<String> mReportsArrayList;
    private SystemDatabaseHelper mDbHelper;
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public ReportFragment() {
        mReportsArrayList = new ArrayList<>();
    }

    public void setContext(Context context) {
        mContext = context;
        mReportsArrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mReportsArrayList);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        mReportsListView = (ListView) rootView.findViewById(R.id.reports_listView);
        mReportsListView.setAdapter(mReportsArrayAdapter);
        mDbHelper = new SystemDatabaseHelper(mContext);
        mDatabase = mDbHelper.getReadableDatabase();


        String[] projection = {
                SystemContract.ClockEntry._ID,
                SystemContract.ClockEntry.COLUMN_NAME_USER_ID,
                SystemContract.ClockEntry.COLUMN_NAME_CLOCKIN
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                SystemContract.ClockEntry.COLUMN_NAME_CLOCKIN + " DESC";

        Cursor cursor = mDatabase.query(
                SystemContract.ClockEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        List items = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item = cursor.getString(
                    cursor.getColumnIndexOrThrow(SystemContract.ClockEntry.COLUMN_NAME_CLOCKIN));
            items.add(item);
        }
        cursor.close();

        for(int i=0; i<items.size(); i++) {
            mReportsArrayList.add(convertToDate(Long.valueOf(items.get(i).toString())));
        }

        mReportsArrayAdapter.notifyDataSetChanged();


        return rootView;
    }

    private String convertToDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy HH:mm:ss", cal).toString();
        return date;
    }
}
