package com.sample.walmartpickup.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sample.walmartpickup.R;

public class DealsFragment extends android.support.v4.app.Fragment {

    int background;

    public DealsFragment() {
    }

    public DealsFragment(int background) {
        this.background = background;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(background);
        return imageView;
    }
}