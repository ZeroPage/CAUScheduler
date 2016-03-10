package org.zeropage.causcheduler;

import android.test.AndroidTestCase;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import org.zeropage.causcheduler.data.Lecture;
import org.zeropage.causcheduler.data.Semester;
import org.zeropage.causcheduler.network.PostStringRequest;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.List;

public class TestRequest extends AndroidTestCase{
	public static final String LOG_TAG = TestRequest.class.getSimpleName();

	public void testRequest() throws Exception{
		String studentId = "20141552";
		int lectureYear = 2016;
		Semester semester = Semester.Spring;
		final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
		Response.Listener listener = new Response.Listener() {
			@Override
			public void onResponse(Object response) {
				List<Lecture> lectures = new PortalXmlParser().parseLectureList(response.toString());
			}
		};
		Response.ErrorListener errorListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d(LOG_TAG, "네트워크 전송 작업에 실패했습니다. 다음 내용을 참고하세요.\n" + error.getMessage());
			}
		};
		String paraments = String.format("<map><userId value=\"%s\"/><groupCode value=\"cau\"/><recordCountPerPage value=\"20\"/><pageIndex value=\"1\"/><kisuYear value=\"%d\"/><kisuNo value=\"%d%d\"/></map>", studentId, lectureYear, lectureYear, semester.semesterCode);
		assertEquals(paraments,
				"<map><userId value=\"20141552\"/><groupCode value=\"cau\"/><recordCountPerPage value=\"20\"/><pageIndex value=\"1\"/><kisuYear value=\"2016\"/><kisuNo value=\"20161\"/></map>");
		PostStringRequest stringRequest = new PostStringRequest("http://cautis.cau.ac.kr/LMS/LMS/prof/myp/pLmsMyp050/selectStudDataInCourseList.do", paraments, listener, errorListener);
		requestQueue.add(stringRequest);
	}
}
