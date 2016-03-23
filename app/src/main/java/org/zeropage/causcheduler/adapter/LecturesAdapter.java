package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.Lecture;

/**
 * Created by Donghwan on 3/23/2016.
 */
public class LecturesAdapter extends RealmBaseAdapter<Lecture> implements ListAdapter {
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
		Lecture lecture = realmResults.get(position);
		viewHolder.item.setText(lecture.getName());

		return convertView;
	}

	public LecturesAdapter(Context context, RealmResults<Lecture> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}

	public RealmResults<Lecture> getRealmResults() { return realmResults; }
}
