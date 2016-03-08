package org.zeropage.causcheduler.activity.detailView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.Homework;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 과제 목록에서 선택한 과제에 대한 자세한 내용을 표시합니다.
 */
public class DetailHomeworkActivity extends AppCompatActivity {

	private final String LOG_TAG = DetailHomeworkActivity.class.getSimpleName();

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_homework);

		// Toolbar 초기화
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Intent intent = getIntent();
		String homeworkName = intent.getStringExtra(SharedConstant.NAME);
		int lectureNum = intent.getIntExtra(SharedConstant.LEC_NUM, 0);
		if(lectureNum == 0) return;
		Realm realm = Realm.getDefaultInstance();
		RealmResults<Homework> item = realm.where(Homework.class)
				.equalTo("name", homeworkName)
				.equalTo("lecture.lectureNum", lectureNum).findAll();
		Homework homework = item.first();
		LinearLayout container = (LinearLayout)findViewById(R.id.container);
		TextView name = (TextView)findViewById(R.id.homework_item_name);
		TextView startTime = (TextView)findViewById(R.id.homework_item_start_time);
		TextView endTime = (TextView)findViewById(R.id.homework_item_end_time);
		TextView extendEndTime = (TextView)findViewById(R.id.homework_item_extend_end_time);
		TextView isOpen = (TextView)findViewById(R.id.homework_item_is_open);
		TextView submitStudentNum = (TextView)findViewById(R.id.homework_item_submit_student_num);
		TextView curProgStat = (TextView)findViewById(R.id.homework_item_cur_prog_stat);
		TextView content = (TextView)findViewById(R.id.homework_item_content);
		name.append(homework.getName());
		startTime.append(homework.getStartTime());
		endTime.append(homework.getEndTime());
		extendEndTime.append(homework.getExtendEndTime());
		isOpen.append(homework.isOpenTask() ? "공개" : "비공개");
		submitStudentNum.append(
				homework.isOpenTask() ? (homework.getSubmitStudentNum() + "/" + homework.getTotalStudentNum()) : "비공개");
		curProgStat.append(homework.getCurrentProgressStatus());
		content.append(homework.getContent());
		realm.close();
	}
}
