package org.zeropage.causcheduler.activity.listView;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.*;

import android.widget.AdapterView;
import android.widget.ListView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.activity.detailView.DetailLectureNoticeActivity;
import org.zeropage.causcheduler.adapter.LectureNoticesAdapter;
import org.zeropage.causcheduler.data.Lecture;
import org.zeropage.causcheduler.data.LectureNotice;
import org.zeropage.causcheduler.dialog.LectureChangeDialogFragment;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 과목의 공지사항 목록을 출력하는 화면입니다.
 */

public class LectureNoticesFragment extends Fragment implements LectureChangeDialogFragment.LectureChangeListener{
	private final String LOG_TAG = LectureNoticesFragment.class.getSimpleName();
	private Realm realm;
	private LectureNoticesAdapter lectureNoticesAdapter;
	private RealmResults<LectureNotice> lectureNotices;

	public LectureNoticesFragment() {
		// Required empty public constructor
	}

	public static LectureNoticesFragment newInstance() {
		return new LectureNoticesFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		realm = Realm.getDefaultInstance();
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		getActivity().setTitle(R.string.label_lectureNotice);
		View rootView = inflater.inflate(R.layout.fragment_lecture_notices, container, false);
		ListView listView = (ListView)rootView.findViewById(R.id.listView_lectureNotice);
		final Lecture lecture = realm.where(Lecture.class).findFirst();
		if(lecture != null) lectureNotices = realm.where(LectureNotice.class).equalTo("lecture.name", lecture.getName()).findAll();
		lectureNoticesAdapter = new LectureNoticesAdapter(getActivity().getApplicationContext(), lectureNotices, true);
		listView.setAdapter(lectureNoticesAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				LectureNotice lectureNotice = lectureNotices.get(position);
				Intent detailViewIntent = new Intent(getActivity().getApplicationContext(), DetailLectureNoticeActivity.class);
				detailViewIntent.putExtra(SharedConstant.TITLE, lectureNotice.getTitle());
				detailViewIntent.putExtra(SharedConstant.LEC_NUM, lectureNotice.getLecture().getNum());
				startActivity(detailViewIntent);
			}
		});

		return rootView;
	}

	@Override
	public void onDialogLectureNameClick(String name) {
		lectureNotices = realm.where(LectureNotice.class).equalTo("lecture.name", name).findAll();
		lectureNoticesAdapter.updateRealmResults(lectureNotices);
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
		switch(item.getItemId()){
			case R.id.action_change_lecture:
				LectureChangeDialogFragment changeLectureDialog = new LectureChangeDialogFragment();
				changeLectureDialog.show(getActivity().getFragmentManager(), LectureChangeDialogFragment.class.getSimpleName());
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
