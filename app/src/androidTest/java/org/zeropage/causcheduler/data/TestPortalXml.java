package org.zeropage.causcheduler.data;

import android.test.AndroidTestCase;
import io.realm.Realm;

/**
 * PortalXmlParser 테스트
 * Created by Donghwan on 2016-01-26.
 */
public class TestPortalXml extends AndroidTestCase{
	public static final String LOG_TAG = TestPortalXml.class.getSimpleName();
    public Realm realm;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
    }

    // TODO 비정상적인 XML 값에 따라서(필요한 노드가 없다든지 순서가 바뀌었다든지...) 테스트를 해야 함
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
