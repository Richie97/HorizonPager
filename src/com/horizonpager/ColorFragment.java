package com.horizonpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by ericrichardson on 3/3/14.
 */
public class ColorFragment extends Fragment {

    public static int[] colors = new int[]{R.drawable.arrow, R.drawable.arrow_blue};

    public static Fragment newInstance(int color){
        Bundle bundle = new Bundle();
        bundle.putInt("key", color);
        ColorFragment colorFragment = new ColorFragment();
        colorFragment.setArguments(bundle);
        return colorFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.box, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.color).setBackgroundResource(colors[new Random().nextInt(colors.length)]);
    }
}