package com.horizonpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by ericrichardson on 3/4/14.
 */
public class HorizonTransform implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.25f;

    public void transformPage(View view, float position) {
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way 'off-screen' to the bottom.
            view.findViewById(R.id.color).setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            //Fade Out
            view.findViewById(R.id.color).setAlpha(1 + position);

            if(position > -0.7f) {
                // Animate down over the "horizon"
                view.setTranslationY(pageHeight * -(position - (position / (8 + position))));
            }else{
                view.setTranslationY(pageHeight * -(position - (position / (8 - 0.7f + (position + 0.7f)))));
            }


            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.findViewById(R.id.color).setAlpha(1 - position);

            // Counteract the default slide transition.
            view.setTranslationY(pageHeight * -(position / 2));

            // Scale the page up basd on position
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 + Math.abs(position * 2));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way 'off-screen' to the bottom.
            view.findViewById(R.id.color).setAlpha(0);
        }
    }
}