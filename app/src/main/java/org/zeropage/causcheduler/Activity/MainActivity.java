package org.zeropage.causcheduler.Activity;

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
                if(item.getItemId() == R.id.drawer_setting){
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, PrefsFragment.newInstance())
                            .commit();
                }
                return false;
            }
        });

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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .add(R.id.container, PrefsFragment.newInstance())
                    .commit();
        }
    }



    /* Called whenever we call invalidateOptionsMenu() */
    // 이 메소드는 드로어가 열릴 경우, 그에 따라 자연스럽게 화면에서 특정 컴포넌트를 없애는 데 사용합니다.
    // ex) 드로어가 열리면 툴바의 검색 버튼이 자동으로 사라져야 함.
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }

    // onPostCreate와 onConfigurationChanged는 툴바의 토글 상태를 동기화 하기 위해 필요함
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
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
