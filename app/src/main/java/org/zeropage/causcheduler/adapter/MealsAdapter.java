package org.zeropage.causcheduler.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.data.Meal;

/**
 * Created by Donghwan on 2016-01-27.
 */
public class MealsAdapter extends RealmBaseAdapter<Meal> implements ListAdapter {

	private static class ViewHolder{
		TextView name;
		TextView distributeTime;
		TextView price;
		TextView totalCalorie;
		TextView menu;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.list_item_meal, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView)convertView.findViewById(R.id.meal_item_name);
			viewHolder.distributeTime = (TextView)convertView.findViewById(R.id.meal_item_distribute_time);
			viewHolder.price = (TextView)convertView.findViewById(R.id.meal_item_price);
			viewHolder.totalCalorie = (TextView)convertView.findViewById(R.id.meal_item_calorie);
			viewHolder.menu = (TextView)convertView.findViewById(R.id.meal_item_menu);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}

		// TODO 데이터 소스에서 해당 위치의 결과값 받아오기
		Meal item = realmResults.get(position);
		viewHolder.name.setText(item.getName());
		viewHolder.distributeTime.setText(item.getDistributeTime());
		viewHolder.price.setText(Integer.toString(item.getPrice()));
		viewHolder.totalCalorie.setText(Float.toString(item.getTotalCalorie()));
		viewHolder.menu.setText(item.getMenu());
		return convertView;
	}

	public MealsAdapter(Context context, RealmResults<Meal> realmResults, boolean automaticUpdate) {
		super(context, realmResults, automaticUpdate);
	}

	public RealmResults<Meal> getRealmResults(){ return realmResults; }
}
