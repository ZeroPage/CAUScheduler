package org.zeropage.causcheduler.data;

import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Response;
import io.realm.Realm;
import org.zeropage.causcheduler.activity.NetworkTestActivity;
import org.zeropage.causcheduler.data.original.*;
import org.zeropage.causcheduler.data.original.Lecture;
import org.zeropage.causcheduler.network.PortalNetworkQueue;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.List;

/**
 * Created by Donghwan on 2016-01-26.
 */
public class TestRealm extends AndroidTestCase{
	public static final String LOG_TAG = TestRealm.class.getSimpleName();
    public Realm realm;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
        Realm realm = Realm.getInstance(getContext());

    }


	/**
	 * 서버에서 받은 데이터를 DB에 저장할 수 있는지 테스트
	 * @throws Throwable
	 */
	public void testDownloadPortalInfo() throws Throwable{
		PortalNetworkQueue.sendLectureListRequest(getContext(), "20146824", 2015, Semester.Spring, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            PortalXmlParser portalXmlParser = new PortalXmlParser();
                            List<Lecture> lectureList = portalXmlParser.parseLectureList(response.toString());
                            Log.d(LOG_TAG, "success");

                        } catch (Exception e) {

                        }
                    }
                });
	}

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        realm.close();
    }
}
