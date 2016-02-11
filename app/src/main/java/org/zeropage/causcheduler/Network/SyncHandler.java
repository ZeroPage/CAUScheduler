package org.zeropage.causcheduler.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import com.android.volley.Response;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmResults;
import org.zeropage.causcheduler.data.Meal;
import org.zeropage.causcheduler.data.Restaurant;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Donghwan on 2016-02-10.
 */
public class SyncHandler extends Handler {
	private Context context;

	public static final int SYNC = 1;

	public static final String ACTION = "action";
	public static final String STUDENT_ID = "studentid";

	public SyncHandler(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		final Bundle bundle = msg.getData();
		final int action = bundle.getInt(ACTION);
		final String studentId = bundle.getString(STUDENT_ID);
		try{
			Integer.parseInt(studentId);
		}catch(NumberFormatException e){
			//학번이 아닌 값이 들어올 때,
			return;
		}

		final PortalXmlParser portalXmlParser = new PortalXmlParser();

		switch(action){
			case SYNC:
				// TODO 네트워크에서 불러오기
				PortalNetworkQueue.sendMealInfoRequest(context, GregorianCalendar.getInstance(), Restaurant.Dormitory, studentId, new Response.Listener() {
					@Override
					public void onResponse(Object response) {
						Realm realm = Realm.getDefaultInstance();
						RealmResults<Meal> oldMeals = realm.where(Meal.class).equalTo("restaurantCode", Restaurant.Dormitory.code).findAll();
						List<Meal> mealList = portalXmlParser.parseMealInfo(response.toString(), Restaurant.Dormitory);
						realm.beginTransaction();
						oldMeals.clear();
						mealList = realm.copyToRealm(mealList);
						realm.commitTransaction();
						realm.close();
					}
				});
		}
	}
}
