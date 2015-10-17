package com.enterprise.charky.mareco.ui;

import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.enterprise.charky.mareco.MainActivity;
import com.enterprise.charky.mareco.R;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;

/**
 * Created by charky on 11.10.15.
 */
public class AdvancedKeyFragment extends ExtendedFragment implements View.OnClickListener {

    private IRCodes irCodes;
    private IRTransmitter irTransmitter;
    private GridLayout advancedGrid;

    public AdvancedKeyFragment(){
        this.setEnterTransition(new Slide(Gravity.RIGHT));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_advanced_key, container, false);
        advancedGrid = (GridLayout) rootView.findViewById(R.id.advanced_grid);
        initAdvancedButtons();
        return rootView;
    }

    public void setUp(IRCodes irCodes, IRTransmitter irTransmitter) {
        this.irCodes = irCodes;
        this.irTransmitter = irTransmitter;
    }

    private void initAdvancedButtons() {

        advancedGrid.removeAllViews();

        for(String customButtonName: irCodes.getCustomCommandNames()){
            Button bt = new Button(getActivity());
            bt.setText(customButtonName);
            bt.setOnClickListener(this);
            advancedGrid.addView(bt,dpToPixels(192),dpToPixels(64));
        }

    }

    @Override
    public void onClick(View v) {
        String btText = ((Button)v).getText().toString();
        try {
            irTransmitter.sendIR(irCodes.getCustomCommand(btText));
        } catch (IRTransmitter.NoIREmitterException e) {
            e.printStackTrace();
        }
    }

    private int dpToPixels(int dps){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }


    public void onButtonClick(View v,MainActivity mainActivity) {

    }

}
