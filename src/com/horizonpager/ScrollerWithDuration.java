package com.horizonpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by ericrichardson on 3/17/14.
 */
public class ScrollerWithDuration extends Scroller {
    private double mScrollFactor = 1;

    public ScrollerWithDuration(Context context) {
        super(context);
    }

    public ScrollerWithDuration(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ScrollerWithDuration(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
    }
}
