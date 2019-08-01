package com.yuecheng.im.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.yuecheng.im.MainTab;
import com.yuecheng.im.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends UI {

    private List<Fragment> fragments;// used for ViewPager adapter
    private VpAdapter adapter;
    ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        viewPager =  findViewById(R.id.viewPager);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragments = new ArrayList<>(2);
        SessionListFragment sessionListFragment= new SessionListFragment();
        sessionListFragment.attachTabData(MainTab.RECENT_CONTACTS);
        ContactListFragment contactListFragment= new ContactListFragment();
        contactListFragment.attachTabData(MainTab.CONTACT);
        fragments.add(sessionListFragment);
        fragments.add(contactListFragment);
        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);//默认预加载几个页面

        login();
    }

    protected void login(){
        NimUIKit.login(new LoginInfo("shimiso", MD5.getStringMD5("8361718")), new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                LogUtil.i("main", "login success");
                // 初始化消息提醒配置
//                initNotificationConfig();

            }

            @Override
            public void onFailed(int code) {
                if (code == 302 || code == 404) {
                    Toast.makeText(MainActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                Toast.makeText(MainActivity.this, "exception", Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }
}
