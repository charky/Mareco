package com.enterprise.charky.mareco.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enterprise.charky.mareco.MainActivity;
import com.enterprise.charky.mareco.R;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;

/**
 * Created by charky on 11.10.15.
 */
public class BasicKeyFragment extends ExtendedFragment{

    private IRCodes irCodes;

    public BasicKeyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_key, container, false);
    }

    public void setIrCodes(IRCodes irCodes) {
        this.irCodes = irCodes;
    }

    public void onButtonClick(View v,MainActivity mainActivity) {
        try {
            switch (v.getId()) {
                case R.id.bt_1:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_1());
                    break;
                case R.id.bt_2:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_2());
                    break;
                case R.id.bt_3:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_3());
                    break;
                case R.id.bt_4:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_4());
                    break;
                case R.id.bt_5:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_5());
                    break;
                case R.id.bt_6:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_6());
                    break;
                case R.id.bt_7:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_7());
                    break;
                case R.id.bt_8:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_8());
                    break;
                case R.id.bt_9:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_9());
                    break;
                case R.id.bt_0:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_0());
                    break;
                //Volume and Channel Buttons
                case R.id.bt_VolUp:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_VOLUME_UP());
                    break;
                case R.id.bt_Mute:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_MUTE());
                    break;
                case R.id.bt_VolDown:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_VOLUME_DOWN());
                    break;
                case R.id.bt_ChUp:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_CHANNEL_UP());
                    break;
                case R.id.bt_Info:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_INFORMATION());
                    break;
                case R.id.bt_ChDown:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_CHANNEL_DOWN());
                    break;
                //Menu Navigation Buttons
                case R.id.bt_Up:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_ARROW_UP());
                    break;
                case R.id.bt_Left:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_ARROW_LEFT());
                    break;
                case R.id.bt_Enter:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_ENTER());
                    break;
                case R.id.bt_Right:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_ARROW_RIGHT());
                    break;
                case R.id.bt_Down:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_ARROW_DOWN());
                    break;
                case R.id.bt_Exit:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_EXIT());
                    break;
                //Elementary Buttons
                case R.id.bt_Power:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_POWER());
                    break;
                case R.id.bt_Menu:
                    mainActivity.irTransmitter.sendIR(irCodes.getIRC_MENU());
                    break;
            }
        } catch (IRTransmitter.NoIREmitterException e) {
            e.printStackTrace();
        }
    }

}
