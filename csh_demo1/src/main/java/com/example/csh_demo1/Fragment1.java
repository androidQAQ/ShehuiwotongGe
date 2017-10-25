package com.example.csh_demo1;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹少航 on 2017/10/25.
 */

public class Fragment1 extends Fragment {
    private List<Fragment> fragments=new ArrayList<>();
    private TabLayout tab;
    private ViewPager vp;
    String[] str = {"社会", "娱乐", "体育", "军事","科技","财经"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,null,false);
        tab=view.findViewById(R.id.tab);
        vp=view.findViewById(R.id.vp);

        getTabLayout();
        //给ViewPager添加适配


        return view;
    }



    //tab设置方法
    private void getTabLayout() {
        //设置tab滚动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //设置tab和Viewpage联动
        tab.setupWithViewPager(vp);
        //将字符数组中的标签放入Tabz中
        for (int i = 0; i < str.length; i++) {
            tab.addTab(tab.newTab().setText(str[i]));
        }
    }

}
