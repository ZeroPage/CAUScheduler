package org.zeropage.causcheduler.activity.DetailViewFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zeropage.causcheduler.R;
public class DetailLectureNoticeFragment extends Fragment {
	private final String LOG_TAG = DetailLectureNoticeFragment.class.getSimpleName();

	public static DetailLectureNoticeFragment newInstance() {
		DetailLectureNoticeFragment fragment = new DetailLectureNoticeFragment();
		return fragment;
	}

	public DetailLectureNoticeFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_detail_lecture_notice, container, false);
	}
}
