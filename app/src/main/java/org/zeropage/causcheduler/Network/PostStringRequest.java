package org.zeropage.causcheduler.Network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lumin on 2015-12-24.
 */
public class PostStringRequest extends StringRequest {
    private final String postBodyContent;

    public PostStringRequest(String url, String postBodyContent, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        super(Method.POST, url, responseListener, errorListener);

        this.postBodyContent = postBodyContent;
    }
}
