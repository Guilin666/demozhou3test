package bwie.example.com.demozhou3test.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.activity.MainActivity;
import bwie.example.com.demozhou3test.fragment.HomeFragment;
import bwie.example.com.demozhou3test.fragment.MyFragmnet;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;

public class MainActivityPresenter extends DegateImpl {
    private Context context;
    private String mTitles[]={"购物车","我的"};
    private List<Fragment> mFragments=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        mFragments.add(new HomeFragment());
        mFragments.add(new MyFragmnet());

        ViewPager viewpager_main=(ViewPager)get(R.id.viewpager_main);
        TabLayout tab_layout=(TabLayout)get(R.id.tab_layout);
        viewpager_main.setAdapter(new FragmentPagerAdapter(((MainActivity)context).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
        tab_layout.setupWithViewPager(viewpager_main);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
    }


    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;
    }


}
