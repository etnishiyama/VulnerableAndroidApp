package br.com.moboweb.vulnerableandroidapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.moboweb.vulnerableandroidapp.LoginActivity;
import br.com.moboweb.vulnerableandroidapp.MainActivity;
import br.com.moboweb.vulnerableandroidapp.R;
import br.com.moboweb.vulnerableandroidapp.database.DatabaseHelperApi;
import br.com.moboweb.vulnerableandroidapp.model.MySharedPreferences;

/**
 * @author Everton Takashi Nishiyama <everton.nishiyama@venturus.org.br>
 * @version 1.0
 * @since 3/31/17
 */
public class ClockInFragment extends Fragment {
    public static final String TAG = "ClockInFragment";
    private Button mClockinButton;
    private DatabaseHelperApi mDb;
    private Context mContext;

    public ClockInFragment(){}

    public void setContext(Context context) {
        mContext = context;
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
                    startActivity(new Intent(mContext, LoginActivity.class));
                    Toast.makeText(getContext(), "Could not save clockin to database!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private boolean saveClockToDb(String clockin) {
        MySharedPreferences sharedPreferences = new MySharedPreferences(mContext);
        String userId = sharedPreferences.getUserId();
        if(userId != null) {
            mDb = new DatabaseHelperApi(mContext);
            mDb.saveClockin(userId, clockin);
            return true;
        } else {
            return false;
        }
    }
}
