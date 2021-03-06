package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TwitterDetailActivity extends AppCompatActivity {
    Tweet tweet;
    TextView tvTweetBody;
    TextView tvName;
    TextView tvHandle;
    ImageView ivProfilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_detail);
        tvTweetBody = findViewById(R.id.tvTweetBody);
        tvName = findViewById(R.id.tvName);
        tvHandle = findViewById(R.id.tvHandle);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

        tweet = Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d("TweetDetailActivity", tweet.getBody());

        tvTweetBody.setText(tweet.getBody());
        tvName.setText(tweet.user.name);
        String handle = "@" + tweet.user.screenName;
        tvHandle.setText(handle);
    }
}