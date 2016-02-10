package org.zeropage.causcheduler.data;

import android.test.AndroidTestCase;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Response;
import io.realm.Realm;
import org.zeropage.causcheduler.activity.NetworkTestActivity;
import org.zeropage.causcheduler.data.original.Lecture;
import org.zeropage.causcheduler.network.PortalNetworkQueue;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Donghwan on 2016-01-26.
 */
public class TestPortalXml extends AndroidTestCase{
	public static final String LOG_TAG = TestPortalXml.class.getSimpleName();
    public Realm realm;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
    }


	/**
	 * 서버에서 받은 데이터를 DB에 저장할 수 있는지 테스트
	 * @throws Throwable
	 */
	public void testDownloadPortalInfo() throws Throwable{
                        PortalNetworkQueue.sendMealInfoRequest(getContext(), GregorianCalendar.getInstance(), Restaurant.Dormitory, "20146824", new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            PortalXmlParser portalXmlParser = new PortalXmlParser();
                            portalXmlParser.parseMealInfo(response.toString(), Restaurant.Dormitory);
                            Log.d(LOG_TAG, "success");
                        } catch (Exception e) {

                        }
                    }
                });

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
