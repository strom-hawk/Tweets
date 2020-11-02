package com.demoapps.tweets.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoapps.tweets.R
import com.demoapps.tweets.interfaces.TweetsFlowCallBack
import com.demoapps.tweets.model.TweetsAdapter
import com.demoapps.tweets.model.TweetsData
import com.demoapps.tweets.model.TweetsResponse
import com.demoapps.tweets.utils.CommonUtils
import com.demoapps.tweets.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.activity_homescreen.*


/*
*This class is the landing page of the application
*/

class HomeScreen : ActivityBase(), TweetsFlowCallBack {
    private var homeScreenViewModel: HomeScreenViewModel? = null
    private val tweetsList = ArrayList<TweetsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_homescreen)
        init()
        fetchTweets()
    }

    private fun init() {
        homeScreenViewModel = HomeScreenViewModel(this)
        tweetsList.clear()
        val linearLayoutManager = LinearLayoutManager(this)
        rvTweets.layoutManager = linearLayoutManager

    }

    private fun fetchTweets() {
        homeScreenViewModel?.fireFetchTweetsApi()
    }

    override fun onApiCalled() {}

    override fun onApiSuccess(tweetsResponse: TweetsResponse) {
        tweetsList.clear()
        tweetsList.addAll(tweetsResponse.data)

        rvTweets.adapter = TweetsAdapter(this, tweetsList)

    }

    override fun onApiFailed(error: Throwable) {
        CommonUtils.showAlertDialog(this, getString(R.string.something_went_wrong), false)
    }
}