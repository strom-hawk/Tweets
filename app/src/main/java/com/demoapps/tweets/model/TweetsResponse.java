package com.demoapps.tweets.model;

/*
This class is used for response
*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TweetsResponse {
    @SerializedName("message")
    @Expose
    private String txnMessage;

    @SerializedName("cod")
    @Expose
    private String txnStatus;

    //getter txnMessage
    public String getTxnMessage() {
        return txnMessage;
    }

    //setter txnMessage
    public void setTxnMessage(String txnMessage) {
        this.txnMessage = txnMessage;
    }

    //getter txnStatus
    public String getTxnStatus() {
        return txnStatus;
    }

    //setter txnStatus
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }
}
