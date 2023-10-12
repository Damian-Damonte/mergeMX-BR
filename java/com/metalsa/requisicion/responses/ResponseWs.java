package com.metalsa.requisicion.responses;

import java.util.List;

/**
 *
 * @author APOMS7376
 */
public abstract class ResponseWs {
    
    private String messageOut;
    private int valueOut;

    public String getMessageOut() {
        return messageOut;
    }

    public void setMessageOut(String messageOut) {
        this.messageOut = messageOut;
    }

    public int getValueOut() {
        return valueOut;
    }

    public void setValueOut(int valueOut) {
        this.valueOut = valueOut;
    }
}
