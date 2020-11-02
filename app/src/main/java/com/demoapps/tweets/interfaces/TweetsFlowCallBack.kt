package com.demoapps.tweets.interfaces

import com.demoapps.tweets.model.TweetsResponse

interface TweetsFlowCallBack {
    fun onApiCalled()
    fun onApiSuccess(tweetsResponse: TweetsResponse)
    fun onApiFailed(error: Throwable)
}