package org.zeropage.causcheduler.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.zeropage.causcheduler.Network.PortalNetworkQueue;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.Util.RConverter;
import org.zeropage.causcheduler.Util.SharedConstant;

/**
 * Created by Lumin on 2016-01-08.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LoginProcess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.login_button);
        final EditText idText = (EditText) findViewById(R.id.portal_id);
        final EditText pwText = (EditText) findViewById(R.id.portal_password);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // For Logging.
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 이름 : " + prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_name_key), ""));
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 학번 : " + prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_num_key), ""));
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 학과 : " + prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_dept_key), ""));

        if (!checkLoginStatus()) {

        } else {
            switchActivity(MainActivity.class);
            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
        }



        // 테스트 용.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 작업을 위한 Dialog 생성.
                String dialogHeader = RConverter.getString(getApplicationContext(), R.string.login_introcution_header);
                String dialogMessage = RConverter.getString(getApplicationContext(), R.string.login_introduction_msg);
                final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, dialogHeader, dialogMessage, false, false);

                PortalNetworkQueue.sendLoginRequest(LoginActivity.this, idText.getText().toString(), pwText.getText().toString(), new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        String responseInfo = response.toString();
                        String[] studentInfo  = responseInfo.split(",");

                        // NG2가 Response로 나옴. 즉, 로그인이 실패함.
                        if (studentInfo.length == 1) {
                            Toast.makeText(getApplicationContext(), "잘못된 아이디.", Toast.LENGTH_LONG).show();
                        } else {
                            prefs.edit().putString(RConverter.getString(getApplicationContext(), R.string.student_name_key), studentInfo[0]).apply();
                            prefs.edit().putString(RConverter.getString(getApplicationContext(), R.string.student_num_key), studentInfo[1]).apply();
                            prefs.edit().putString(RConverter.getString(getApplicationContext(), R.string.student_dept_key), studentInfo[2]).apply();

                            // For Logging.
                            Log.e(LOG_TAG, "받아온 학생 이름 : " + studentInfo[0]);
                            Log.e(LOG_TAG, "받아온 학생 학번 : " + studentInfo[1]);
                            Log.e(LOG_TAG, "받아온 학생 학과 : " + studentInfo[2]);

                            switchActivity(MainActivity.class);
                        }

                        // Dialog 해제.
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 로그인이 되어있는 상태인지 확인합니다.
     * @return 로그인이 되어있는 상태면 True, 되어있지 않으면 False를 반환합니다.
     */
    private boolean checkLoginStatus() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedStudentId = prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_num_key), SharedConstant.EMPTY_STRING);
        String savedStudentName = prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_name_key), SharedConstant.EMPTY_STRING);
        String savedStudentDept = prefs.getString(RConverter.getString(getApplicationContext(), R.string.student_dept_key), SharedConstant.EMPTY_STRING);
        return !savedStudentId.equals(SharedConstant.EMPTY_STRING) && !savedStudentName.equals(SharedConstant.EMPTY_STRING) && !savedStudentDept.equals(SharedConstant.EMPTY_STRING);
    }

    /**
     * 해당 Activity에서 인자로 주어진 Activity로 이동합니다. Extra는 사용하지 않습니다.
     * @param cls 이동한 Activity를 가리킵니다.
     */
    private void switchActivity(Class<?> cls) {
        Intent switchIntent = new Intent(getApplicationContext(), cls);
        this.finish();
        startActivity(switchIntent);
    }
}
