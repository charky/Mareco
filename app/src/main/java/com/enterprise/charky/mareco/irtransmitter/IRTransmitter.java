package com.enterprise.charky.mareco.irtransmitter;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;

/**
 * Created by charky on 06.09.15.
 */
public class IRTransmitter {

    private ConsumerIrManager mCIR;

    public IRTransmitter(Activity activity){
        // Get a reference to the ConsumerIrManager
        mCIR = (ConsumerIrManager)activity.getSystemService(Context.CONSUMER_IR_SERVICE);
    }

    public void sendIR(IRCommand irCommand) throws NoIREmitterException, IllegalArgumentException{
        if (!mCIR.hasIrEmitter()) {
            throw new NoIREmitterException("No IR Emitter found");
        }

        if(!irCommand.isFrequencyAndCodesSet()){
            throw new IllegalArgumentException("Frequency and/or Codes are not set");
        }

        // transmit the pattern
        mCIR.transmit(irCommand.getFrequency(), irCommand.getCodes());
    }

    public class NoIREmitterException extends Exception {
        public NoIREmitterException(String message) {
            super(message);
        }
    }

}
