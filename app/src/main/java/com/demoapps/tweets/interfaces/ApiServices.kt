package com.demoapps.tweets.interfaces

import com.demoapps.tweets.model.TweetsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("tweets")
    fun getCurrentTweets(): Observable<TweetsResponse>
}