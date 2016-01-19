package org.zeropage.causcheduler.Activity;

import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.ListView;
import org.zeropage.causcheduler.R;

public class AssignmentFragment extends Fragment {

    public static AssignmentFragment newInstance() {
        AssignmentFragment fragment = new AssignmentFragment();
        return fragment;
    }

    public AssignmentFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_assignment, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listView_assignment);
        // TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_assignment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
