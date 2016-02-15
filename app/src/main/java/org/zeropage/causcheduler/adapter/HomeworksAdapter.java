package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.Homework;

/**
 * Created by Donghwan on 2016-01-27.
 *
 * 과제의 내용을 리스트뷰에 출력하고 갱신해주는 어뎁터
 */
public class HomeworksAdapter extends RealmBaseAdapter<Homework> implements ListAdapter{

	private static class ViewHolder{
		TextView name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_item_homework, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView)convertView.findViewById(R.id.homework_item_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Homework item = realmResults.get(position);
		viewHolder.name.setText((item.isSubmitted() ? "[제출됨] " : "[제출안됨] ") + item.getName());
		return convertView;
	}

	public HomeworksAdapter(Context context, RealmResults<Homework> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}

	public RealmResults<Homework> getRealmResults() { return realmResults; }
}
