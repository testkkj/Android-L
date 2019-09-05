package com.example.challenge;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_fragment1, container, false);

        class MyPagerAdapter extends FragmentStatePagerAdapter {
            ArrayList<Fragment> items = new ArrayList<Fragment>();
            public MyPagerAdapter(FragmentManager fragmentManager){
                super(fragmentManager);
            }

            public void addItem(Fragment item) {
                items.add(item);
            }

            @Override
            public Fragment getItem(int position) {
                return items.get(position);
            }

            @Override
            public int getCount() {
                return items.size();
            }
        }

        ViewPager viewPager = viewGroup.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager());

        PagerFragment1 pagerFragment1 = new PagerFragment1();
        myPagerAdapter.addItem(pagerFragment1);

        PagerFragment2 pagerFragment2 = new PagerFragment2();
        myPagerAdapter.addItem(pagerFragment2);

        PagerFragment3 pagerFragment3 = new PagerFragment3();
        myPagerAdapter.addItem(pagerFragment3);

        viewPager.setAdapter(myPagerAdapter);

        return viewGroup;
    }
}
