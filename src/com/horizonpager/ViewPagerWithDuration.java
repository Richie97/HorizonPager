package com.horizonpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;

/**
 * Created by ericrichardson on 3/17/14.
 */
public class ViewPagerWithDuration extends VerticalViewPager {
    public ViewPagerWithDuration(Context context) {
        super(context);
        postInitViewPager();
    }

    public ViewPagerWithDuration(Context context, AttributeSet attrs) {
        super(context, attrs);
        postInitViewPager();
    }

    private ScrollerWithDuration mScroller = null;

    /**
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void postInitViewPager() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        mTouchSlop = (viewConfiguration.getScaledTouchSlop());
        try {
            Field scroller = VerticalViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = VerticalViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            mScroller = new ScrollerWithDuration(getContext(),
                    (Interpolator) interpolator.get(null));
            scroller.set(this, mScroller);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

    private float mCurrX = 0.0f;
    private float mStartX, mStartY;
    private int mTouchSlop;
    private boolean autoScrolling = false;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            mCurrX = event.getX();
            mStartX = event.getX();
            mStartY = event.getY();
            startUserInteraction();
        } else if (Math.abs(event.getY() - mStartY) > Math.abs(event.getX() - mStartX)) {
            // User scrolled vertically
            endUserInteraction();
        } else if (action == MotionEvent.ACTION_MOVE) {
            // Shouldn't need to do anything
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            endUserInteraction();
        } else {
            startUserInteraction();
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE) {
            if (getCurrentItem() == 0 && ((event.getX() - mCurrX) > mTouchSlop)) {
                // User moved finger to the right and is on the leftmost ViewGroup
                endUserInteraction();
            } else if (getCurrentItem() == (getAdapter().getCount() - 1) && ((mCurrX - event.getX()) > mTouchSlop)) {
                // User moved finger to the left and is on the rightmost ViewGroup
                endUserInteraction();
            }
        } else {
            endUserInteraction();
        }
        return super.onTouchEvent(event);
    }

    private void startUserInteraction() {
        setScrollDurationFactor(1);
    }

    private void endUserInteraction() {
        setScrollDurationFactor(10);
    }
}