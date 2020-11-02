package com.demoapps.tweets.model;

/*
This class is used for response
*/

public class TweetsData {
    private String name;
    private String handle;
    private String text;
    private String profileImageUrl;
    private String retweetCount;
    private String favoriteCount;

    public String getName() {
        return name;
    }

    public String getHandle() {
        return handle;
    }

    public String getText() {
        return text;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getRetweetCount() {
        return retweetCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }
}
