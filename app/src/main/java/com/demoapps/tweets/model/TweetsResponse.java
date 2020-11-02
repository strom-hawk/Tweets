package com.demoapps.tweets.model;

/*
This class is used for response
*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TweetsResponse {
    @SerializedName("success")
    @Expose
    private String txnMessage;

    @SerializedName("data")
    @Expose
    private ArrayList<TweetsData> data;

    //getter txnMessage
    public String getTxnMessage() {
        return txnMessage;
    }

    //setter txnMessage
    public void setTxnMessage(String txnMessage) {
        this.txnMessage = txnMessage;
    }

    //getter data
    public ArrayList<TweetsData> getData() {
        return data;
    }

    //setter data
    public void setData(ArrayList<TweetsData> data) {
        this.data = data;
    }
}
