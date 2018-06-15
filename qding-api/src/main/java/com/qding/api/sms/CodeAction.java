package com.qding.api.sms;

/**
 * Created by qd on 2017/3/8.
 */
public enum CodeAction  {

    VOICECODE(2),
    SMSCODE(1);

    private int action;

    CodeAction(int action) {
        this.action = action;
    }

}
