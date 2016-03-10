package org.zeropage.causcheduler.activity;

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

import org.zeropage.causcheduler.network.PortalNetworkQueue;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.util.RConverter;
import org.zeropage.causcheduler.util.SharedConstant;

/**
 * 사용자에게 중앙대 포탈 아이디와 패스워드를 입력받는 액티비티입니다.
 * Created by Lumin on 2016-01-08.
 */
public class LoginActivity extends AppCompatActivity implements Response.Listener{
    private static final String LOG_TAG = "LoginProcess";
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 로그인 작업을 위한 Dialog 설정.
        dialog = new ProgressDialog(LoginActivity.this);
        String dialogHeader = RConverter.getStringFromR(getApplicationContext(), R.string.login_introduction_header);
        String dialogMessage = RConverter.getStringFromR(getApplicationContext(), R.string.login_introduction_msg);

        setDialogWithTitleAndMessage(dialogHeader, dialogMessage);
        setDialogNotCancelableAndNotIndeterminate();
        //LoggingSavedStudent();

        // 버튼에 Listener 추가.
        final Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText idText = (EditText) findViewById(R.id.portal_id);
                final EditText pwText = (EditText) findViewById(R.id.portal_password);

                PortalNetworkQueue.sendLoginRequest(LoginActivity.this, idText.getText().toString(), pwText.getText().toString(), LoginActivity.this);
            }
        });
    }

    private void setDialogWithTitleAndMessage(final String title, final String message) {
        dialog.setTitle(title);
        dialog.setMessage(message);
    }

    private void setDialogNotCancelableAndNotIndeterminate() {
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
    }

    private void LoggingSavedStudent() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 이름 : " + prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_name_key), ""));
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 학번 : " + prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_num_key), ""));
        Log.e(LOG_TAG, "현재 저장되어 있는 학생 학과 : " + prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_dept_key), ""));
    }

    private void switchActivity(Class<?> cls) {
        Intent switchIntent = new Intent(getApplicationContext(), cls);
        finish();
        startActivity(switchIntent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // 이미 로그인이 되어있는 상태인지 검사.
        if (isAlreadyLoginStatus()) {
            switchActivity(MainActivity.class);
            Toast.makeText(getApplicationContext(), RConverter.getStringFromR(getApplicationContext(), R.string.welcome_msg), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 로그인이 되어있는 상태인지 확인합니다.
     * @return 로그인이 되어있는 상태면 True, 되어있지 않으면 False를 반환합니다.
     */
    private boolean isAlreadyLoginStatus() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String savedStudentId = prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_num_key), SharedConstant.EMPTY_STRING);
        String savedStudentName = prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_name_key), SharedConstant.EMPTY_STRING);
        String savedStudentDept = prefs.getString(RConverter.getStringFromR(getApplicationContext(), R.string.student_dept_key), SharedConstant.EMPTY_STRING);
        return !savedStudentId.equals(SharedConstant.EMPTY_STRING) && !savedStudentName.equals(SharedConstant.EMPTY_STRING) && !savedStudentDept.equals(SharedConstant.EMPTY_STRING);
    }

    @Override
    public void onResponse(Object response) {
        final String responseInfo = response.toString();
        Log.e(LOG_TAG, responseInfo);
        final String[] studentInfo  = responseInfo.split(",");

        // NG2가 Response로 나옴. 즉, 로그인이 실패함.
        if (studentInfo.length == 1) {
            Toast.makeText(getApplicationContext(), RConverter.getStringFromR(getApplicationContext(), R.string.login_fail_msg), Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            // studentInfo에서 studentInfo[0] = 학생 이름, studentInfo[1] = 학생 학번, studentInfo[2] = 소속 학과
            prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_name_key), studentInfo[0]).apply();
            prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_num_key), studentInfo[1]).apply();
            prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_dept_key), studentInfo[2]).apply();
            LoggingSavedStudent();
            switchActivity(MainActivity.class);
        }

        dialog.dismiss();
    }
}