package org.zeropage.causcheduler.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.android.volley.Response;
import io.realm.Realm;
import org.zeropage.causcheduler.data.*;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Donghwan on 2016-02-10.
 */
public class WorkerHandler extends Handler {
	private Context context;
	private Realm mRealm;

	public static final int SYNC = 1;

	public static final String ACTION = "action";
	public static final String STUDENT_ID = "studentid";

	public WorkerHandler(Context context, Realm realm) {
		this.context = context;
		this.mRealm = realm;
	}

	@Override
	public void handleMessage(Message msg) {
		final Bundle bundle = msg.getData();
		final int action = bundle.getInt(ACTION);
		switch(action){
			case SYNC:
				// 학번 불러오기
				final String studentId = bundle.getString(STUDENT_ID);
				try{
					// TODO 학번 검사(parseInt는 대충 넣은 거임)
					Integer.parseInt(studentId);
				}catch(NumberFormatException e){
					// 학번이 아닌 값이 들어올 때,
					return;
				}
				final PortalXmlParser portalXmlParser = new PortalXmlParser();
				// 예전에 저장된 정보는 지움
				clearRealm();

				// TODO 포탈에서 정보 가져오기

				// 식단 정보 불러오기
				Calendar calendar = GregorianCalendar.getInstance();
				for (final Restaurant restaurant : Restaurant.values()){
					PortalNetworkQueue.sendMealInfoRequest(context, calendar, restaurant, studentId, new Response.Listener() {
						@Override
						public void onResponse(Object response) {
							Realm realm = Realm.getDefaultInstance();
							List<Meal> mealList = portalXmlParser.parseMealInfo(response.toString(), restaurant);
							realm.beginTransaction();
							realm.copyToRealm(mealList);
							realm.commitTransaction();
							realm.close();
						}
					});
				}
				// 강의 목록 정보 불러오기
				PortalNetworkQueue.sendLectureListRequest(context, studentId, calendar.get(Calendar.YEAR), Semester.toSemester(calendar), new Response.Listener() {
					@Override
					public void onResponse(Object response) {
						Realm realm = Realm.getDefaultInstance();
						List<Lecture> lectureList = portalXmlParser.parseLectureList(response.toString());
						realm.beginTransaction();
						realm.copyToRealm(lectureList);
						realm.commitTransaction();
						realm.close();
					}
				});

				// 과제 목록 정보 불러오기
				

				break;
		}
	}

	private void clearRealm(){
		mRealm.beginTransaction();
		mRealm.clear(Meal.class);
		mRealm.clear(Homework.class);
		mRealm.clear(LectureNotice.class);
		mRealm.clear(Lecture.class);
		mRealm.commitTransaction();
	}
}
