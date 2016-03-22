package org.zeropage.causcheduler;

import android.test.AndroidTestCase;
import io.realm.Realm;
import org.zeropage.causcheduler.data.Meal;
import org.zeropage.causcheduler.data.Restaurant;
import org.zeropage.causcheduler.util.PortalXmlParser;

import java.util.List;

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
    public void testXmlParser() throws Exception {
        String testXml = "<map id='' ><today value='20160204' /><vector id='result'><map id='0' ><tm value='07:00~08:40' /><amt value='2800 원' /><menunm1 value='709Kcal&lt;br&gt;비지찌개&lt;br&gt;계란찜&lt;br&gt;미역초고추장무침&lt;br&gt;허브생채&lt;br&gt;쌀밥/깍두기' /><picpath value='http://cautis.cau.ac.kr/TIS/images/menu/hansik.png' /><menuscd value='02' /></map></vector><msg value='no error' /><msgCode value='success' /></map>";
        PortalXmlParser portalXmlParser = new PortalXmlParser();
        List<Meal> mealList = portalXmlParser.parseMealInfo(testXml, Restaurant.Dormitory);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
