package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.LectureNotice;

/**
 * Created by Donghwan on 2016-01-27.
 *
 * 과목의 공지사항의 내용을 출력하고 갱신해주는 어뎁터
 */
public class LectureNoticesAdapter extends RealmBaseAdapter<LectureNotice> implements ListAdapter{

	private static class ViewHolder{
		TextView item;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_item_lecture_notice, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.item = (TextView)convertView.findViewById(R.id.lecture_notice_item_text);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// TODO 데이터 소스에서 해당 위치의 결과값 받아오기
		// Object obj = realmResults.get(position);
		// viewHolder.item.setText(obj.get());
		LectureNotice lectureNotice = realmResults.get(position);
		viewHolder.item.setText((lectureNotice.isImportant() ? "[중요]": "") + lectureNotice.getTitle());

		return convertView;
	}

	public LectureNoticesAdapter(Context context, RealmResults<LectureNotice> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}

	public RealmResults<LectureNotice> getRealmResults() { return realmResults; }
}
