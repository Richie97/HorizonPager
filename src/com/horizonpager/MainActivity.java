package com.horizonpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ericrichardson on 3/4/14.
 */
public class MainActivity extends FragmentActivity {
    ViewPagerWithDuration pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPagerWithDuration) findViewById(R.id.pager);
        pager.setAdapter(new ExampleAdapter(getSupportFragmentManager()));
        pager.setPageTransformer(true, new HorizonTransform());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pager.setScrollDurationFactor(10);
                        pager.setCurrentItem(pager.getCurrentItem() + 1);
                        if(pager.getCurrentItem() == pager.getAdapter().getCount() - 1){
                            timer.cancel();
                            timer.purge();
                        }
                    }
                });

            }
        }, 2000, 2000);
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