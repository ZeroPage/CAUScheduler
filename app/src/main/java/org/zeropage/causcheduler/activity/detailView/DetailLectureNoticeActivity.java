package org.zeropage.causcheduler.activity.detailView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.LectureNotice;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 강의 공지사항 목록에서 선택한 공지사항에 대한 자세한 내용을 표시합니다.
 */
public class DetailLectureNoticeActivity extends AppCompatActivity {
	private final String LOG_TAG = DetailLectureNoticeActivity.class.getSimpleName();
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_lecture_notice);

		// Toolbar 초기화
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// TODO back button과 동일하게 작동해야함
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		String noticeTitle = intent.getStringExtra(SharedConstant.TITLE);
		int lectureNum = intent.getIntExtra(SharedConstant.LEC_NUM, 0);
		if(lectureNum == 0) return;
		Realm realm = Realm.getDefaultInstance();
		RealmResults<LectureNotice> item = realm.where(LectureNotice.class)
				.equalTo("title", noticeTitle)
				.equalTo("lecture.num", lectureNum).findAll();
		LectureNotice lectureNotice = item.first();
		LinearLayout container = (LinearLayout)findViewById(R.id.container);
		TextView title = (TextView)findViewById(R.id.lecture_notice_item_title);
		TextView writtenDate = (TextView)findViewById(R.id.lecture_notice_item_written_date);
		TextView isImportant = (TextView)findViewById(R.id.lecture_notice_item_is_important);
		TextView hitCount = (TextView)findViewById(R.id.lecture_notice_item_hit_count);
		TextView content = (TextView)findViewById(R.id.lecture_notice_item_content);
		title.append(lectureNotice.getTitle());
		writtenDate.append(lectureNotice.getWrittenDate());
		isImportant.append(lectureNotice.isImportant() ? "중요" : "중요하지 않음");
		hitCount.append(Integer.toString(lectureNotice.getHitCount()));
		content.append(Html.fromHtml(lectureNotice.getContent()));
		realm.close();
	}
}
