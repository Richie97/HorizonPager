package com.richapps.myapplication2.app;

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

    int[] colors = new int[]{0xffff0000, 0xff00ff00, 0xff0000ff};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.box, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.color).setBackgroundColor(colors[new Random().nextInt(colors.length -1)]);
    }
}
