package org.zeropage.causcheduler.activity.ListViewFragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.original.Homework;

/**
 * 과제 목록을 출력하는 화면입니다.
 */
public class HomeworksFragment extends Fragment{
    private final String LOG_TAG = HomeworksFragment.class.getSimpleName();

    private ArrayAdapter<Homework> mAdapter;

    public static HomeworksFragment newInstance() {
        HomeworksFragment fragment = new HomeworksFragment();
        return fragment;
    }

    public HomeworksFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_homeworks, container, false);
        getActivity().setTitle(R.string.label_homework);
        ListView listView = (ListView)rootView.findViewById(R.id.listView_assignment);
        // TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_homeworks, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
