package org.zeropage.causcheduler.activity.ListViewFragment;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.AdapterView;
import android.widget.ListView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.activity.DetailViewFragment.DetailLectureNoticeActivity;
import org.zeropage.causcheduler.adapter.LectureNoticesAdapter;
import org.zeropage.causcheduler.data.LectureNotice;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 과목의 공지사항 목록을 출력하는 화면입니다.
 */

public class LectureNoticesFragment extends Fragment {
	private final String LOG_TAG = LectureNoticesFragment.class.getSimpleName();
	private Realm realm;
	private LectureNoticesAdapter lectureNoticesAdapter;

	public LectureNoticesFragment() {
		// Required empty public constructor
	}

	public static LectureNoticesFragment newInstance() {
		LectureNoticesFragment fragment = new LectureNoticesFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		realm = Realm.getDefaultInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		getActivity().setTitle(R.string.label_lectureNotice);
		View rootView = inflater.inflate(R.layout.fragment_lecture_notices, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_lectureNotice);
		final RealmResults<LectureNotice> lectureNotices = realm.where(LectureNotice.class).findAll();
		lectureNoticesAdapter = new LectureNoticesAdapter(getActivity().getApplicationContext(), lectureNotices, true);
		listView.setAdapter(lectureNoticesAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LectureNotice lectureNotice = lectureNotices.get(position);
				Intent detailViewIntent = new Intent(getActivity().getApplicationContext(), DetailLectureNoticeActivity.class);
				detailViewIntent.putExtra(SharedConstant.TITLE, lectureNotice.getTitle());
				detailViewIntent.putExtra(SharedConstant.LEC_NUM, lectureNotice.getLecture().getLectureNum());
				startActivity(detailViewIntent);
			}
		});

		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		realm.close();
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
