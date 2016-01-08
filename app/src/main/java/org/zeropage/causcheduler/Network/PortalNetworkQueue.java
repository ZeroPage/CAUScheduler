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
     * 포탈에 아이디/패스워드를 통해 로그인 Request를 요청합니다. 오류가 발생했을 때는 단순히 로그를 남기는 작업만을 수행합니다.
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

    public static void sendHomeworkRequest(Context context, String studentId, String lectureId, Response.Listener listener, Response.ErrorListener errorListener) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/std/lec/sLmsLec070/selectTaskList.do",
                "<map><lectureNo value=\"" + lectureId + "\"/><userId value=\"" + studentId +"\"/></map>", listener, errorListener);
        requestQueue.add(stringRequest);
    }
}
