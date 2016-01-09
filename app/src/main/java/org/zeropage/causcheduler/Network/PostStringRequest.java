package org.zeropage.causcheduler.Network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * POST 작업을 수행하기 위해 StringRequest를 확장시킨 클래스입니다.
 * Created by Lumin on 2015-12-24.
 */
public class PostStringRequest extends StringRequest {
    private final String postBodyContent;
    private static final String LOG_TAG = "Request";

    /**
     * PostStringRequest 인스턴스를 초기화합니다.
     * @param url Request를 보내고자 하는 Url 주소입니다.
     * @param postBodyContent Body에 담을 내용입니다.
     * @param responseListener 네트워크 작업 후 수행할 작업을 명시한 Listener입니다.
     * @param errorListener 네트워크 작업 시 오류가 발생했을 때 작업을 명시한 Listener입니다.
     */
    public PostStringRequest(String url, String postBodyContent, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        super(Method.POST, url, responseListener, errorListener);

        this.postBodyContent = postBodyContent;
    }

    /**
     * Request의 Header를 재정의합니다.
     * @return Request의 Header입니다.
     */
    @Override
    public String getBodyContentType() {
        return "application/xml; charset=" + getParamsEncoding();
    }

    /**
     * Request의 Entity Body를 가져옵니다.
     * @return UTF-8로 인코딩된 Entity body를 가져옵니다.
     * @throws AuthFailureError
     */
    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return postBodyContent == null ? null : postBodyContent.getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            Log.e(LOG_TAG, "지원되지 않는 Encoding을 사용하려 하고 있습니다.");
            return null;
        }
    }
}
