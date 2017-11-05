package com.example.csh_demo1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csh_demo1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹少航 on 2017/10/25.
 */

public class Fragment1 extends Fragment {
    private List<Fragment> fragments=new ArrayList<>();
    private TabLayout tab;
    private ViewPager vp1;
     private static final String[] str = {"社会", "娱乐", "体育", "军事","科技","财经"};
    private List<Fragment> listF=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null,false);
        tab=view.findViewById(R.id.tab);
        vp1=view.findViewById(R.id.vp);
        //tabLayout和Fragment+viewPager
        getTabLayout();

        return view;
    }
    //tab设置方法
    private void getTabLayout() {
        listF.add( new Fragment_1());
        listF.add( new Fragment_2());
        listF.add( new Fragment_3());
        listF.add( new Fragment_4());
        listF.add( new Fragment_5());
        listF.add( new Fragment_6());
        //遍历数组
        for (String s : str){
            tab.addTab(tab.newTab().setText(s));
        }

        //设置tab滚动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);

        //设置tab和Viewpage联动
        tab.setupWithViewPager(vp1);


        vp1.setAdapter(new MyFragmentAdapter1(getActivity().getSupportFragmentManager()));

    }

    class MyFragmentAdapter1 extends  FragmentPagerAdapter{

        public MyFragmentAdapter1(FragmentManager fm1) {
            super(fm1);

        }

        @Override
        public Fragment getItem(int position) {
            return listF.get(position);
        }

        @Override
        public int getCount() {
            return listF.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }




}
