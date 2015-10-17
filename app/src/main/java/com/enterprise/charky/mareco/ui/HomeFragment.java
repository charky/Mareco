package com.enterprise.charky.mareco.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enterprise.charky.mareco.MainActivity;
import com.enterprise.charky.mareco.R;

/**
 * Created by charky on 16.10.15.
 */
public class HomeFragment extends ExtendedFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onButtonClick(View v, MainActivity mainActivity) {

    }
}
