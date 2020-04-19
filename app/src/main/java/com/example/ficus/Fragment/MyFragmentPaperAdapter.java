package com.example.ficus.Fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ficus.MainActivity;

public class MyFragmentPaperAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    private Tourist_Fragment1 myFragment1 = null;
    private Tourist_Fragment2 myFragment2= null;
    private Tourist_Fragment3 myFragment3 = null;
    private Tourist_Fragment4 myFragment4 = null;


    public MyFragmentPaperAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Tourist_Fragment1();
        myFragment2 = new Tourist_Fragment2();
        myFragment3 = new Tourist_Fragment3();
        myFragment4 = new Tourist_Fragment4();
    }

    public MyFragmentPaperAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public int getCount(){
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    //获取给定位置的项Id，用于生成Fragment名称
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case MainActivity.PAGE_ONE:
                fragment = myFragment1;
                break;
            case MainActivity.PAGE_TWO:
                fragment = myFragment2;
                break;
            case MainActivity.PAGE_THREE:
                fragment = myFragment3;
                break;
            case MainActivity.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }
}