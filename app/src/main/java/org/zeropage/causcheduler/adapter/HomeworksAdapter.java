package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.data.Homework;

/**
 * Created by Donghwan on 2016-01-27.
 */
public class HomeworksAdapter extends RealmBaseAdapter<Homework> implements ListAdapter{
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	public HomeworksAdapter(Context context, RealmResults<Homework> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}
}
