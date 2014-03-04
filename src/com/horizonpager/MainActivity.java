package com.horizonpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Random;

/**
 * Created by ericrichardson on 3/4/14.
 */
public class MainActivity extends FragmentActivity {
    VerticalViewPager pager;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (VerticalViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ExampleAdapter(getSupportFragmentManager()));
        pager.setPageTransformer(true, new HorizonTransform());
    }

    private class ExampleAdapter extends FragmentPagerAdapter {

        public ExampleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ColorFragment.newInstance(ColorFragment.colors[new Random().nextInt(ColorFragment.colors.length -1)]);
        }


        @Override
        public int getCount() {
            return 10;
        }
    }
}