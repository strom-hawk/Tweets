package com.demoapps.tweets.viewmodel

import com.demoapps.tweets.interfaces.TweetsFlowCallBack
import com.demoapps.tweets.model.TweetsResponse
import com.demoapps.tweets.utils.ApplicationConstants
import com.demoapps.tweets.utils.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
*This class is used as view model of home screen
*/

class HomeScreenViewModel(
    private var tweetsFlowCallBack: TweetsFlowCallBack
) : BaseViewModel() {

    fun fireFetchTweetsApi() {
        val apiServices = RetrofitClient.getApiClient(ApplicationConstants.BASE_URL)
        if (apiServices != null) {
            compositeDisposable?.add(
                apiServices.getCurrentTweets()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())
                    .subscribe({ response ->
                        handleResponse(response)
                    }, { error ->
                        handleError(error)
                    })
            )
        }
    }

    private fun handleResponse(tweetsResponse: TweetsResponse) {
        tweetsFlowCallBack.onApiSuccess(tweetsResponse)
    }

    private fun handleError(error: Throwable) {
        tweetsFlowCallBack.onApiFailed(error)
    }
}