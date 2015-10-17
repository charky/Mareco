package com.enterprise.charky.mareco.ui;

import android.support.v4.app.Fragment;
import android.view.View;

import com.enterprise.charky.mareco.MainActivity;

/**
 * Created by charky on 16.10.15.
 */
public abstract class ExtendedFragment extends Fragment{
    public abstract void onButtonClick(View v, MainActivity mainActivity);
}
