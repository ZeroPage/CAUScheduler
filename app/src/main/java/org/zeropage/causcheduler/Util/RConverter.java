package org.zeropage.causcheduler.Util;

import android.content.Context;

/**
 * R.java에 들어있는 리소스들을 실제 데이터로 변환시켜주는 기능을 제공하는 정적 클래스입니다.
 * Created by Lumin on 2016-01-08.
 */
public class RConverter {
    /**
     * R.string에 들어있는 문자열을 가져옵니다.
     * @param context 문자열을 가져올 때 사용할 Context를 가리킵니다.
     * @param stringId 문자열이 들어있는 주소를 가리킵니다. ex) R.string.[....]
     * @return 가져온 문자열을 반환합니다.
     */
    public static String getStringFromR(final Context context, final int stringId) {
        return context.getResources().getString(stringId);
    }

    /**
     * RConverter 인스턴스를 초기화합니다.
     */
    private RConverter() {

    }
}
