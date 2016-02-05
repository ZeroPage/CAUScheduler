package org.zeropage.causcheduler.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.zeropage.causcheduler.R;
import org.zeropage.causcheduler.util.RConverter;
import org.zeropage.causcheduler.util.SharedConstant;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar 초기화
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // NavigationView 초기화
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if(item.isChecked()) item.setChecked(false);
                else item.setChecked(true);

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                // TODO 드로어 아이템 클릭 시 해야되는 일 적기
                switch(item.getItemId()){
                    case R.id.drawer_lectureNotices:
                        getFragmentManager().beginTransaction().replace(R.id.container, LectureNoticeFragment.newInstance())
                                .commit(); break;
                    case R.id.drawer_assignments:
                        getFragmentManager().beginTransaction().replace(R.id.container, HomeworkFragment.newInstance())
                                .commit(); break;
                    case R.id.drawer_meals:
                        getFragmentManager().beginTransaction().replace(R.id.container, MealFragment.newInstance())
                                .commit(); break;
                    case R.id.drawer_setting:
                        getFragmentManager().beginTransaction().replace(R.id.container, PrefsFragment.newInstance())
                            .commit(); break;
                }

                if (item.getItemId() == R.id.drawer_logout) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_num_key), SharedConstant.EMPTY_STRING).apply();
                    prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_dept_key), SharedConstant.EMPTY_STRING).apply();
                    prefs.edit().putString(RConverter.getStringFromR(getApplicationContext(), R.string.student_name_key), SharedConstant.EMPTY_STRING).apply();

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(intent);
                }

                return false;
            }
        });

        // 헤더에 계정 정보를 보여주기
        View header = mNavigationView.getHeaderView(0);
        TextView studentNameTextView = (TextView)header.findViewById(R.id.header_student_name);
        TextView studentNumTextView = (TextView)header.findViewById(R.id.header_student_num);
        TextView studentDeptTextView = (TextView)header.findViewById(R.id.header_student_dept);

        studentNameTextView.setText(PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext()).getString(getString(R.string.student_name_key), getString(R.string.student_name_default)));
        studentNumTextView.setText(PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext()).getString(getString(R.string.student_num_key), getString(R.string.student_num_default)));
        studentDeptTextView.setText(PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext()).getString(getString(R.string.student_dept_key), getString(R.string.student_dept_default)));

        // 드로어 레이아웃 설정
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            // 드로어가 완전히 닫히면 호출
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // onPrepareOptionsMenu()을 호출하기 위해 만듬
            }

            // 드로어가 완전히 열리면 호출
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // onPrepareOptionsMenu()을 호출하기 위해 만듬
            }
        };

        // 드로어 토글을 리스너로 설정
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // 초기 화면을 보여줄 프래그먼트를 추가함
        // TODO 초기 화면을 보여줄 프래그먼트를 설정 값에 따라 바꿔야 함
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .add(R.id.container, LectureNoticeFragment.newInstance())
                    .commit();
        }
    } // onCreate() end

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO realm.close()
    }

    // invalidateOptionsMenu()를 호출할 때 호출됨
    // 이 메소드는 드로어가 열릴 경우, 그에 따라 자연스럽게 화면에서 특정 컴포넌트를 없애는 데 사용합니다.
    // ex) 드로어가 열리면 툴바의 검색 버튼이 자동으로 사라져야 함.
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    // onPostCreate와 onConfigurationChanged는 툴바의 토글 상태를 동기화 하기 위해 필요함
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // 홈버튼 이벤트가 툴바 토글에서 끝나도록 오버라이드한 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
