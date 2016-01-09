package org.zeropage.causcheduler.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.zeropage.causcheduler.Util.Semester;

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
     * 포탈에 아이디/패스워드를 통해 로그인 Request를 요청합니다.
     * @param context 네트워크 작업에 사용할 Context입니다.
     * @param userId 로그인에 사용할 ID를 가라킵니다.
     * @param userPassword 로그인에 사용할 Password를 가리킵니다.
     * @param listener 네트워크 작업이 끝난 후 수행할 작업을 가리키는 Listener입니다.
     * @param errorListener 네트워크 작업 중 오류가 발생했을 때 수행되는 작업을 가리키는 Listener입니다.
     */
    public static void sendLoginRequest(final Context context, final String userId, final String userPassword, final Response.Listener listener, final Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://cautis.cau.ac.kr/SMT/getList.jsp?id=" + userId + "&pw=" + userPassword, listener, errorListener);
        requestQueue.add(stringRequest);

        Log.e(LOG_TAG, "로그인 작업에 전달된 ID : " + userId);
        Log.e(LOG_TAG, "로그인 작업에 전달된 Password : " + userPassword);
    }

    /**
     * 포탈에 아이디/패스워드를 통해 로그인 Request를 요청합니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
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
     * 포탈에 특정 기간에 수강하거나 수강했던 강의의 목록을 불러오도록 Request를 요청합니다.
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
     * 포탈에 특정 기간에 수강하거나 수강했던 강의의 목록을 불러오도록 Request를 요청합니다. 오류가 발생했을 때는 단순히 로그를 남기고, 간단한 Toast를 띄우는 작업만을 수행합니다.
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
}