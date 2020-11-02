package com.demoapps.tweets.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demoapps.tweets.R
import kotlinx.android.synthetic.main.tweet_item_layout.view.*

class TweetsAdapter(private val context: Context,
                    private val tweetsList: List<TweetsData>): RecyclerView.Adapter<TweetsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.tweet_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return tweetsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tweetName.text = tweetsList.get(position).name
        holder.tweetHandle.text = tweetsList.get(position).handle
        holder.tweetFavCount.text = tweetsList.get(position).favoriteCount
        holder.tweetReTweetCount.text = tweetsList.get(position).retweetCount
        holder.tweetDescription.text = tweetsList.get(position).text
        //holder.tweetImage.setImageBitmap(getImageFromUrl(tweetsList.get(position).profileImageUrl))
    }

    private fun getImageFromUrl(url: String){


    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tweetName = itemView.tweetName
        val tweetHandle = itemView.tweetHandle
        val tweetFavCount = itemView.tweetFavCount
        val tweetReTweetCount = itemView.tweetReTweetCount
        val tweetDescription = itemView.tweetDescription
        val tweetImage = itemView.tweetImage
    }
}