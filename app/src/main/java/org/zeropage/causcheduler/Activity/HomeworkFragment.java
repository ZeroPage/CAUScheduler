package org.zeropage.causcheduler.activity;

import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.original.Homework;

public class HomeworkFragment extends Fragment{

    private ArrayAdapter<Homework> mAdapter;

    public static HomeworkFragment newInstance() {
        HomeworkFragment fragment = new HomeworkFragment();
        return fragment;
    }

    public HomeworkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_homework, container, false);
        getActivity().setTitle(R.string.label_homework);
        ListView listView = (ListView)rootView.findViewById(R.id.listView_assignment);
        // TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_homework, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
