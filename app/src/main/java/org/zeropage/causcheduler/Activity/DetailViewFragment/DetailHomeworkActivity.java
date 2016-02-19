package org.zeropage.causcheduler.activity.DetailViewFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.activity.ListViewFragment.HomeworksFragment;
import org.zeropage.causcheduler.activity.ListViewFragment.LectureNoticesFragment;
import org.zeropage.causcheduler.activity.ListViewFragment.MealsFragment;
import org.zeropage.causcheduler.activity.LoginActivity;
import org.zeropage.causcheduler.activity.PrefsFragment;
import org.zeropage.causcheduler.data.Homework;
import org.zeropage.causcheduler.util.RConverter;
import org.zeropage.causcheduler.util.SharedConstant;

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
		TextView curProgStat = (TextView)findViewById(R.id.homework_item_cur_prog_stat);
		TextView content = (TextView)findViewById(R.id.homework_item_content);
		name.append(homework.getName());
		startTime.append(homework.getStartTime());
		endTime.append(homework.getEndTime());
		extendEndTime.append(homework.getExtendEndTime());
		curProgStat.append(homework.getCurrentProgressStatus());
		content.append(homework.getContent());
		realm.close();
	}


}
