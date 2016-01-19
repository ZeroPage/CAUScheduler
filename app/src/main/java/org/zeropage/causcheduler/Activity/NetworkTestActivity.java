package org.zeropage.causcheduler.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.zeropage.causcheduler.network.PortalNetworkQueue;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.util.PortalXmlParser;

/**
 * Created by Lumin on 2016-01-09.
 */
public class NetworkTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test);

        Button b = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.textView);

        tv.setText(Html.fromHtml("<p>  <span style=\"color: rgb(0, 0, 0); font-family: Dotum; font-size: 12px; line-height: 16.8px; widows: 1;\">수업 시간에 배운 2D Graphics와 Touch screen event 처리기를 사용하여,</span></p> <p style=\"widows: 1; \">  <font color=\"#000000\" face=\"Dotum\"><span style=\"line-height: 16.799999237060547px;\">간단한 라켓볼 게임을 만들어 봅시다.</span></font></p> <p style=\"widows: 1; \">  <font color=\"#000000\" face=\"Dotum\"><span style=\"line-height: 16.799999237060547px;\">실행화면이 첨부한 수업자료에 나와 있으니 참조 바랍니다.</span></font></p>"));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PortalNetworkQueue.sendLectureListRequest(getApplicationContext(), "20146824", 2015, Semester.Spring, new Response.Listener() {
//                    @Override
//                    public void onResponse(Object response) {
//                        try {
//                            PortalXmlParser portalXmlParser = new PortalXmlParser();
//                            portalXmlParser.parseLectureList(response.toString());
//                            Toast.makeText(NetworkTestActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//
//                        }
//                    }
//                });

//                PortalNetworkQueue.sendMealInfoRequest(getApplicationContext(), new GregorianCalendar(2016, 1, 9), Restaurant.Dormitory, "20146824", new Response.Listener() {
//                    @Override
//                    public void onResponse(Object response) {
//                        try {
//                            PortalXmlParser portalXmlParser = new PortalXmlParser();
//                            portalXmlParser.parseMealInfo(response.toString());
//                            Toast.makeText(NetworkTestActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//
//                        }
//                    }
//                });

//                PortalNetworkQueue.sendHomeworkListRequest(getApplicationContext(), "20146824", 99382, new Response.Listener() {
//                    @Override
//                    public void onResponse(Object response) {
//                        try {
//                            PortalXmlParser portalXmlParser = new PortalXmlParser();
//                            portalXmlParser.parseHomeworkList(response.toString());
//                            Toast.makeText(NetworkTestActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//
//                        }
//                    }
//                });

//                PortalNetworkQueue.sendHomeworkViewRequest(getApplicationContext(), "20146824", 99382, 5, new Response.Listener() {
//                    @Override
//                    public void onResponse(Object response) {
//                        try {
//                            PortalXmlParser portalXmlParser = new PortalXmlParser();
//                            portalXmlParser.parseHomeworkView(response.toString());
//                            Toast.makeText(NetworkTestActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                        } catch (Exception e) {
//
//                        }
//                    }
//                });

                PortalNetworkQueue.sendNoticeRequest(getApplicationContext(), "20146824", 99382, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            PortalXmlParser portalXmlParser = new PortalXmlParser();
                            portalXmlParser.parseNotice(response.toString());
                            Toast.makeText(NetworkTestActivity.this, "성공", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {

                        }
                    }
                });
            }
        });
    }
}
