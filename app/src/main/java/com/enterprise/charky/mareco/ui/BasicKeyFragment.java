package com.enterprise.charky.mareco.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enterprise.charky.mareco.R;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;
import com.enterprise.charky.mareco.irtransmitter.IRTransmitter;

/**
 * Created by charky on 11.10.15.
 */
public class BasicKeyFragment extends Fragment{

    private IRTransmitter irTransmitter;
    private IRCodes irCodes;

    public BasicKeyFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    public void setIrTransmitter(IRTransmitter _irTransmitter){
        irTransmitter = _irTransmitter;
    }


    public void setIrCodes(IRCodes irCodes) {
        this.irCodes = irCodes;
    }

    public void RemoteButtonClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.bt_1:
                    irTransmitter.sendIR(irCodes.getIRC_1());
                    break;
                case R.id.bt_2:
                    irTransmitter.sendIR(irCodes.getIRC_2());
                    break;
                case R.id.bt_3:
                    irTransmitter.sendIR(irCodes.getIRC_3());
                    break;
                case R.id.bt_4:
                    irTransmitter.sendIR(irCodes.getIRC_4());
                    break;
                case R.id.bt_5:
                    irTransmitter.sendIR(irCodes.getIRC_5());
                    break;
                case R.id.bt_6:
                    irTransmitter.sendIR(irCodes.getIRC_6());
                    break;
                case R.id.bt_7:
                    irTransmitter.sendIR(irCodes.getIRC_7());
                    break;
                case R.id.bt_8:
                    irTransmitter.sendIR(irCodes.getIRC_8());
                    break;
                case R.id.bt_9:
                    irTransmitter.sendIR(irCodes.getIRC_9());
                    break;
                case R.id.bt_0:
                    irTransmitter.sendIR(irCodes.getIRC_0());
                    break;
                //Volume and Channel Buttons
                case R.id.bt_VolUp:
                    irTransmitter.sendIR(irCodes.getIRC_VOLUME_UP());
                    break;
                case R.id.bt_Mute:
                    irTransmitter.sendIR(irCodes.getIRC_MUTE());
                    break;
                case R.id.bt_VolDown:
                    irTransmitter.sendIR(irCodes.getIRC_VOLUME_DOWN());
                    break;
                case R.id.bt_ChUp:
                    irTransmitter.sendIR(irCodes.getIRC_CHANNEL_UP());
                    break;
                case R.id.bt_Info:
                    irTransmitter.sendIR(irCodes.getIRC_INFORMATION());
                    break;
                case R.id.bt_ChDown:
                    irTransmitter.sendIR(irCodes.getIRC_CHANNEL_DOWN());
                    break;
                //Menu Navigation Buttons
                case R.id.bt_Up:
                    irTransmitter.sendIR(irCodes.getIRC_ARROW_UP());
                    break;
                case R.id.bt_Left:
                    irTransmitter.sendIR(irCodes.getIRC_ARROW_LEFT());
                    break;
                case R.id.bt_Enter:
                    irTransmitter.sendIR(irCodes.getIRC_ENTER());
                    break;
                case R.id.bt_Right:
                    irTransmitter.sendIR(irCodes.getIRC_ARROW_RIGHT());
                    break;
                case R.id.bt_Down:
                    irTransmitter.sendIR(irCodes.getIRC_ARROW_DOWN());
                    break;
                case R.id.bt_Exit:
                    irTransmitter.sendIR(irCodes.getIRC_EXIT());
                    break;
                //Elementary Buttons
                case R.id.bt_Power:
                    irTransmitter.sendIR(irCodes.getIRC_POWER());
                    break;
                case R.id.bt_Menu:
                    irTransmitter.sendIR(irCodes.getIRC_MENU());
                    break;
            }
        } catch (IRTransmitter.NoIREmitterException e) {
            e.printStackTrace();
        }
    }

}
