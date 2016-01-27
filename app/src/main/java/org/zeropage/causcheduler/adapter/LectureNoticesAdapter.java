package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.data.LectureNotice;

/**
 * Created by Donghwan on 2016-01-27.
 */
public class LectureNoticesAdapter extends RealmBaseAdapter<LectureNotice> implements ListAdapter{
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	public LectureNoticesAdapter(Context context, RealmResults<LectureNotice> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}
}
