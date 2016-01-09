package org.zeropage.causcheduler.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.zeropage.causcheduler.Network.PortalNetworkQueue;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.Util.Semester;

import java.nio.charset.StandardCharsets;

/**
 * Created by Lumin on 2016-01-09.
 */
public class NetworkTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);

        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PortalNetworkQueue.sendLectureListRequest(getApplicationContext(), "20146824", 2015, Semester.Spring, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            Log.e("AAAA", response.toString());
                            Toast.makeText(NetworkTestActivity.this, new String(response.toString().getBytes("ISO_8859_1")), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
    }
}
