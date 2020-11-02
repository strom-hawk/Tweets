package com.demoapps.tweets.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
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
    private val searchedTweetsList = ArrayList<TweetsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_homescreen)
        init()
        bindView()
        fetchTweets()
    }

    private fun init() {
        homeScreenViewModel = HomeScreenViewModel(this)
        tweetsList.clear()
        val linearLayoutManager = LinearLayoutManager(this)
        rvTweets.layoutManager = linearLayoutManager

    }

    private fun fetchTweets() {
        progressBarLayout.visibility = View.VISIBLE
        rvTweets.visibility = View.GONE
        homeScreenViewModel?.fireFetchTweetsApi()
    }

    override fun onApiCalled() {}

    override fun onApiSuccess(tweetsResponse: TweetsResponse) {
        tweetsList.clear()
        tweetsList.addAll(tweetsResponse.data)
        searchedTweetsList.addAll(tweetsResponse.data)

        rvTweets.adapter = TweetsAdapter(this, searchedTweetsList)
        progressBarLayout.visibility = View.GONE
        rvTweets.visibility = View.VISIBLE
    }

    override fun onApiFailed(error: Throwable) {
        progressBarLayout.visibility = View.GONE
        rvTweets.visibility = View.VISIBLE
        CommonUtils.showAlertDialog(this, getString(R.string.something_went_wrong), false)
    }

    private fun bindView(){
        etSearchTweetText.addTextChangedListener(tweetSearchTextWatcher)

        searchTweetButton.setOnClickListener {
            if(TextUtils.isEmpty(etSearchTweetText.text)){
                CommonUtils.showAlertDialog(this, getString(R.string.tweets_search_box_error), false)
            }else{
                searchedTweetsList.clear()
                for(tweets in tweetsList){
                    if(tweets.name.contains(etSearchTweetText.text, ignoreCase = true)||
                        tweets.handle.contains(etSearchTweetText.text, ignoreCase = true) ||
                        tweets.text.contains(etSearchTweetText.text, ignoreCase = true)){
                        searchedTweetsList.add(tweets)
                    }
                }
                rvTweets.adapter?.notifyDataSetChanged()
                if(searchedTweetsList.isEmpty()){
                    noTweetsError.visibility = View.VISIBLE
                    rvTweets.visibility = View.GONE
                }else{
                    noTweetsError.visibility = View.GONE
                    rvTweets.visibility = View.VISIBLE
                }
            }
        }
    }

    private val tweetSearchTextWatcher = object : TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            if(etSearchTweetText.text.isEmpty()){
                searchedTweetsList.clear()
                searchedTweetsList.addAll(tweetsList)
                rvTweets.adapter?.notifyDataSetChanged()
                noTweetsError.visibility = View.GONE
                rvTweets.visibility = View.VISIBLE
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }
}