package org.zeropage.causcheduler.network;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.volley.Response;
import io.realm.Realm;
import org.zeropage.causcheduler.data.*;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Donghwan on 2016-02-10.
 * 백그라운드에서 처리할 일을 처리함.
 * 주로 중앙대 포탈 서버에서 컨텐츠를 가져오는 명령을 받아서 처리합니다.
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
				//Log.e("workerThread", studentId);
				// TODO 학번 검사

				final PortalXmlParser portalXmlParser = new PortalXmlParser();
				// 예전에 저장된 정보는 지움
				clearRealm();

				// TODO 에러 메세지를 표현해야 함.(지금은 기본 Toast)

				//TODO 테스트용 날짜값을 GregorianCalendar.getInstance()로 고치기
				final Calendar calendar = GregorianCalendar.getInstance();
				// 강의 목록 정보 불러오기
				PortalNetworkQueue.sendLectureListRequest(context, studentId, calendar.get(Calendar.YEAR), Semester.toSemester(calendar), new Response.Listener() {
					@Override
					public void onResponse(Object response) {
						//Log.e("workerThread", String.format("<map><userId value=\"%s\"/><groupCode value=\"cau\"/><recordCountPerPage value=\"20\"/><pageIndex value=\"1\"/><kisuYear value=\"%d\"/><kisuNo value=\"%d%d\"/></map>", studentId, calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR), Semester.toSemester(calendar).semesterCode));
						Realm realm = Realm.getDefaultInstance();
						List<Lecture> lectureList = portalXmlParser.parseLectureList(response.toString());
						realm.beginTransaction();
						lectureList = realm.copyToRealm(lectureList);
						realm.commitTransaction();
						realm.close();
						// 강의 공지사항 목록 정보 불러오기
						for(final Lecture lecture : lectureList) {
							PortalNetworkQueue.sendNoticeRequest(context, studentId, lecture.getLectureNum(), new Response.Listener() {
								@Override
								public void onResponse(Object response) {
									Realm realm = Realm.getDefaultInstance();
									List<LectureNotice> lectureNoticeList = portalXmlParser.parseNotice(response.toString(), lecture);
									realm.beginTransaction();
									realm.copyToRealm(lectureNoticeList);
									realm.commitTransaction();
									realm.close();
								}
							});

							// 과제 목록 정보 불러오기
							PortalNetworkQueue.sendHomeworkListRequest(context, studentId, lecture.getLectureNum(), new Response.Listener() {
								@Override
								public void onResponse(Object response) {
									Realm realm = Realm.getDefaultInstance();
									List<Homework> homeworkList = portalXmlParser.parseHomeworkList(response.toString(), lecture);
									realm.beginTransaction();
									homeworkList = realm.copyToRealm(homeworkList);
									realm.commitTransaction();
									realm.close();
									// 각 과제에 과제 내용 넣기
									for (final Homework homework : homeworkList) {
										PortalNetworkQueue.sendHomeworkContentRequest(context, studentId, lecture.getLectureNum(), homework.getIndex(), new Response.Listener() {
											@Override
											public void onResponse(Object response) {
												Realm realm = Realm.getDefaultInstance();
												realm.beginTransaction();
												homework.setContent(portalXmlParser.parseHomeworkContent(response.toString()));
												realm.commitTransaction();
												realm.close();
											}
										});
									}
								}
							});
						}
					}
				});

				// 식단 정보 불러오기
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
				break; // case SYNC end
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
