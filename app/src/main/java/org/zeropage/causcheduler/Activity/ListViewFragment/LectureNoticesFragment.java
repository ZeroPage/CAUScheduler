package org.zeropage.causcheduler.activity.ListViewFragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.ListView;
import org.zeropage.causcheduler.R;

public class LectureNoticesFragment extends Fragment {
	private final String LOG_TAG = LectureNoticesFragment.class.getSimpleName();

	public LectureNoticesFragment() {
		// Required empty public constructor
	}

	// TODO: Rename and change types and number of parameters
	public static LectureNoticesFragment newInstance() {
		LectureNoticesFragment fragment = new LectureNoticesFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		getActivity().setTitle(R.string.label_lectureNotice);
		View rootView = inflater.inflate(R.layout.fragment_lecture_notices, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_lectureNotice);
		// TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_lecturenotices, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}
}
