package org.zeropage.causcheduler.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import org.zeropage.causcheduler.R;

public class LectureNoticeFragment extends Fragment {

	public LectureNoticeFragment() {
		// Required empty public constructor
	}

	// TODO: Rename and change types and number of parameters
	public static LectureNoticeFragment newInstance() {
		LectureNoticeFragment fragment = new LectureNoticeFragment();
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
		View rootView = inflater.inflate(R.layout.fragment_lecture_notice, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_lectureNotice);
		// TODO ListView에 어댑터 달고, 과제 정보 받아 와야함.

		return rootView;
	}
}
