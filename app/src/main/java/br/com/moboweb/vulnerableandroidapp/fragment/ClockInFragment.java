package br.com.moboweb.vulnerableandroidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.moboweb.vulnerableandroidapp.R;

/**
 * @author Everton Takashi Nishiyama <everton.nishiyama@venturus.org.br>
 * @version 1.0
 * @since 3/31/17
 */
public class ClockInFragment extends Fragment {
    public static final String TAG = "ClockInFragment";
    private Button mClockinButton;

    public ClockInFragment() {
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
                Toast.makeText(getContext(), "Clock in on: "+System.currentTimeMillis(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
