package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;
    //Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the data at the position
        Tweet tweet = tweets.get(position);
        //Bind the tweet with the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    //Add a list of items
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    //Define a ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvTwitterHandle;
        TextView tvTimeStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvTwitterHandle = itemView.findViewById(R.id.tvTwitterHandle);
            itemView.setOnClickListener(this);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.name);
            String screenNameWithAt = "@" + tweet.user.screenName;
            tvTwitterHandle.setText(screenNameWithAt);
            tvTimeStamp.setText(tweet.getFormattedTimestamp(tweet.createdAt, tweet));
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.i("TweetsAdapter", String.valueOf(position));
            if (position != RecyclerView.NO_POSITION) {
                Tweet tweet = tweets.get(position);
                Intent intent = new Intent(context, TwitterDetailActivity.class);
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                context.startActivity(intent);
            }
        }
    }
}
