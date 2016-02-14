package org.zeropage.causcheduler.activity.DetailViewFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zeropage.causcheduler.R;

/**
 * 과제의 상세한 내용을 출력하는 화면입니다.
 */

public class DetailHomeworkFragment extends Fragment {
	private final String LOG_TAG = DetailHomeworkFragment.class.getSimpleName();

	public static DetailHomeworkFragment newInstance() {
		DetailHomeworkFragment fragment = new DetailHomeworkFragment();
		return fragment;
	}

	public DetailHomeworkFragment() {
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
		return inflater.inflate(R.layout.fragment_detail_homework, container, false);
	}
}
