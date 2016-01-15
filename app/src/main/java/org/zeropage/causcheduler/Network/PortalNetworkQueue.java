package org.zeropage.causcheduler.Network;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.zeropage.causcheduler.Util.Restaurant;
import org.zeropage.causcheduler.Util.Semester;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Lumin on 2015-12-24.
 * 포탈과 관련된 네트워크 작업을 지원하는 정적 클래스입니다. 인스턴스 생성은 불가능합니다.
 */
public class PortalNetworkQueue {
    private static final String LOG_TAG = "Network";

    /**
     * PortalNetworkQueue 인스턴스를 초기화합니다. 정적 클래스이므로 외부에서 인스턴스 생성은 불가능합니다.
     */
    private PortalNetworkQueue() {

    }

    /**
     * 포탈에 아이디/패스워드를 통해 로그인 Request를 보냅니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param userId 로그인에 사용할 ID를 가라킵니다.
     * @param userPassword 로그인에 사용할 Password를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행되는 작업을 가리키는 Listener입니다.
     */
    public static void sendLoginRequest(final Context context, final String userId, final String userPassword, final Response.Listener listener, final Response.ErrorListener errorListener) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String encodedPW = URLEncoder.encode(userPassword, "utf-8");

            StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://cautis.cau.ac.kr/SMT/getList.jsp?id=" + userId + "&pw=" + encodedPW, listener, errorListener);
            requestQueue.add(stringRequest);
        } catch (UnsupportedEncodingException e) {

        }
    }

    /**
     * 포탈에 아이디/패스워드를 통해 로그인 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param userId 로그인에 사용할 ID를 가라킵니다.
     * @param userPassword 로그인에 사용할 Password를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendLoginRequest(final Context context, final String userId, final String userPassword, final Response.Listener listener) {
        sendLoginRequest(context, userId, userPassword, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 포탈에 특정 기간에 수강하거나 수강했던 강의의 목록을 불러오도록 Request를 보냅니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 강의 리스트를 불러올 학생의 학번을 가리킵니다.
     * @param lectureYear 강의 리스트를 불러올 년도를 가리킵니다.
     * @param semester 강의 리스트를 불러올 학기를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendLectureListRequest(final Context context, final String studentId, final int lectureYear, final Semester semester, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/prof/myp/pLmsMyp050/selectStudDataInCourseList.do",
                "<map><userId value=\"" + studentId + "\"/><groupCode value=\"cau\"/><recordCountPerPage value=\"20\"/><pageIndex value=\"1\"/>" +
                        "<kisuYear value=\"" + lectureYear + "\"/><kisuNo value=\"" + Integer.toString(lectureYear) + semester.getSemesterCode() + "\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }

    /**
     * 포탈에 특정 기간에 수강하거나 수강했던 강의의 목록을 불러오도록 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 강의 리스트를 불러올 학생의 학번을 가리킵니다.
     * @param lectureYear 강의 리스트를 불러올 년도를 가리킵니다.
     * @param semester 강의 리스트를 불러올 학기를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendLectureListRequest(final Context context, final String studentId, final int lectureYear, final Semester semester, final Response.Listener listener) {
        sendLectureListRequest(context, studentId, lectureYear, semester, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "정보 수신에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 포탈에 특정 날짜에 대한 식당의 식단 정보를 불러오도록 Request를 보냅니다. 
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param calendar 식단을 불러올 날짜 정보입니다.
     * @param restaurant 식단을 불러올 식당을 가리킵니다.
     * @param studentId 식단을 불러오기 위해 필요한 학생의 학번을 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendMealInfoRequest(final Context context, final GregorianCalendar calendar, final Restaurant restaurant, final String studentId, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String timeShortcut = Integer.toString(calendar.get(calendar.YEAR)) + String.format("%02d", calendar.get(calendar.MONTH)) + String.format("%02d", calendar.get(calendar.DATE));

        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/TIS/portlet/comm/cPortlet001/selectList.do",
                "<map><userid value=\"" + studentId + "\"/><calvalue value=\"0\"/><gb value=\"1\"/><storediv value=\"" + restaurant.getCode() + "\"/><campfg value=\"1\"/>" +
                        "<today value=\"" + timeShortcut + "\"/><store value=\"" + restaurant.getCode() + "\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }

    /**
     * 포탈에 특정 날짜에 대한 식당의 식단 정보를 불러오도록 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param calendar 식단을 불러올 날짜 정보입니다.
     * @param restaurant 식단을 불러올 식당을 가리킵니다.
     * @param studentId 식단을 불러오기 위해 필요한 학생의 학번을 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendMealInfoRequest(final Context context, final GregorianCalendar calendar, final Restaurant restaurant, final String studentId, final Response.Listener listener) {
        sendMealInfoRequest(context, calendar, restaurant, studentId, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "정보 수신에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 포탈에 특정 과목의 과제 리스트를 불러오도록 Request를 보냅니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 과제 정보를 불러오기 위한 학생의 학번입니다.
     * @param networkLectureId 과제 정보를 불러올 강의의 ID를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendHomeworkListRequest(final Context context, final String studentId, final int networkLectureId, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/std/lec/sLmsLec070/selectTaskList.do",
                "<map><lectureNo value=\"" + networkLectureId + "\"/><userId value=\"" + studentId + "\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }


    /**
     * 포탈에 특정 과목의 과제 리스트를 불러오도록 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 과제 정보를 불러오기 위한 학생의 학번입니다.
     * @param networkLectureId 과제 정보를 불러올 강의의 ID를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendHomeworkListRequest(final Context context, final String studentId, final int networkLectureId, final Response.Listener listener) {
        sendHomeworkListRequest(context, studentId, networkLectureId, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "정보 수신에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 포탈에 특정 과제의 상세 내용을 불러오도록 Request를 보냅니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 과제 정보를 불러오기 위한 학생의 학번입니다.
     * @param networkLectureId 과제 정보를 불러올 강의의 ID를 가리킵니다.
     * @param homeworkTaskId 해당 과제에 부여된 ID를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendHomeworkViewRequest(final Context context, final String studentId, final int networkLectureId, final int homeworkTaskId, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/std/lec/sLmsLec070/getTaskView.do",
                "<map><lectureNo value=\"" + networkLectureId + "\"/><userId value=\"" + studentId + "\"/><taskNo value=\"" + homeworkTaskId + "\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }

    /**
     * 포탈에 특정 과제의 상세 내용을 불러오도록 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 과제 정보를 불러오기 위한 학생의 학번입니다.
     * @param networkLectureId 과제 정보를 불러올 강의의 ID를 가리킵니다.
     * @param homeworkTaskId 해당 과제에 부여된 ID를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendHomeworkViewRequest(final Context context, final String studentId, final int networkLectureId, final int homeworkTaskId, final Response.Listener listener) {
        sendHomeworkViewRequest(context, studentId, networkLectureId, homeworkTaskId, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "정보 수신에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 포탈에 특정 과목의 공지사항을 불러오도록 Request를 보냅니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 공지사항을 불러오는데 필요한 학생의 학번입니다.
     * @param networkLectureId 공지사항을 불러올 강의의 ID를 가리킵니다.
     * @param pageIndex 공지사항을 불러올 페이지 번호를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendNoticeRequest(final Context context, final String studentId, final int networkLectureId, final int pageIndex, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/prof/lec/pLmsLec070/selectLectureBoardList.do",
                "<map><userId value=\"" + studentId + "\"/><lectureNo value=\"" + networkLectureId + "\"/><boardType value=\"NOTICE\"/><recordCountPerPage value=\"10\"/><pageIndex value=\"" + pageIndex + "\"/><searchWord value=\"\"/><searchType value=\"inqAll\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }

    /**
     * 포탈에 특정 과목의 공지사항을 불러오도록 Request를 보냅니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param studentId 공지사항을 불러오는데 필요한 학생의 학번입니다.
     * @param networkLectureId 공지사항을 불러올 강의의 ID를 가리킵니다.
     * @param pageIndex 공지사항을 불러올 페이지 번호를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     */
    public static void sendNoticeRequest(final Context context, final String studentId, final int networkLectureId, final int pageIndex, final Response.Listener listener) {
        sendNoticeRequest(context, studentId, networkLectureId, pageIndex, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
                Toast.makeText(context, "정보 수신에 실패했습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
}