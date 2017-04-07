package br.com.moboweb.vulnerableandroidapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.moboweb.vulnerableandroidapp.R;

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

    public ReportFragment() {
        mReportsArrayList = new ArrayList<>();
        mReportsArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mReportsArrayList);
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

        return rootView;
    }
}
