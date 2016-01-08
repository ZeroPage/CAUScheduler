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
 * Created by Lumin on 2015-12-24.
 */
public class PostStringRequest extends StringRequest {
    private final String postBodyContent;
    private static final String LOG_TAG = "Request";

    public PostStringRequest(String url, String postBodyContent, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        super(Method.POST, url, responseListener, errorListener);

        this.postBodyContent = postBodyContent;
    }

    @Override
    protected String getParamsEncoding() {
        return "ISO_8859_1";
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

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
