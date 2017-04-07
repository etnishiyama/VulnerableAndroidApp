package br.com.moboweb.vulnerableandroidapp.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.moboweb.vulnerableandroidapp.R;
import br.com.moboweb.vulnerableandroidapp.database.SystemContract;
import br.com.moboweb.vulnerableandroidapp.database.SystemDatabaseHelper;

/**
 * @author Everton Takashi Nishiyama <everton.nishiyama@venturus.org.br>
 * @version 1.0
 * @since 3/31/17
 */
public class ClockInFragment extends Fragment {
    public static final String TAG = "ClockInFragment";
    private Button mClockinButton;
    private SystemDatabaseHelper mDbHelper;

    public ClockInFragment() {
        mDbHelper = new SystemDatabaseHelper(getContext());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_clock_in, container, false);

        mClockinButton = (Button) rootView.findViewById(R.id.clockin_button);
        mClockinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveClockToDb(String.valueOf(System.currentTimeMillis()))) {
                    Toast.makeText(getContext(), "Clock in saved to database: " + System.currentTimeMillis(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Could not save clockin to database!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private boolean saveClockToDb(String clockin) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SystemContract.ClockEntry.COLUMN_NAME_CLOCKIN, clockin);
        values.put(SystemContract.ClockEntry.COLUMN_NAME_USER_ID, "001");

        long newRowId = db.insert(SystemContract.ClockEntry.TABLE_NAME, null, values);

        if (newRowId > 0) {
            return true;
        } else {
            return false;
        }
    }
}
