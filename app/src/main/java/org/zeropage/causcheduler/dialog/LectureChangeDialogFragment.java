package org.zeropage.causcheduler.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.adapter.LecturesAdapter;
import org.zeropage.causcheduler.data.Lecture;

/**
 * 강의 선택 다이얼로그입니다.
 *
 * Created by Donghwan on 3/22/2016.
 */
public class LectureChangeDialogFragment extends DialogFragment {
	private final String LOG_TAG = LectureChangeDialogFragment.class.getSimpleName();
	private Realm realm;
	private LecturesAdapter lecturesAdapter;
	private LectureChangeListener listener;

	public interface LectureChangeListener{
		/**
		 * 이 다이얼로그를 생성하는 R.id.container 프래그먼트는
		 * 반드시 이 함수를 통해 강의를 선택했을 때, 해야 하는 액션을 정의해야 합니다.
		 * @param name 선택한 강의 이름
		 */
		public void onDialogLectureNameClick(String name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		realm = Realm.getDefaultInstance();
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final RealmResults<Lecture> lectures = realm.where(Lecture.class).findAll();
		lecturesAdapter = new LecturesAdapter(getActivity().getApplicationContext(), lectures, true);
		builder.setTitle(R.string.action_change_lecture)
				.setAdapter(lecturesAdapter, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO 해당 강의 선택을 반영
						// 호출한 프래그먼트에게 이벤트를 넘김
						listener.onDialogLectureNameClick(lectures.get(which).getName());
					}
				});
		return builder.create();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			// 호출한 프래그먼트를 리스너로 추가
			listener = (LectureChangeListener)activity.getFragmentManager().findFragmentById(R.id.container);
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		realm.close();
	}
}
