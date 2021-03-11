package com.codepath.apps.restclienttemplate.models;

import androidx.room.Embedded;

import java.util.ArrayList;
import java.util.List;

public class TweetWithUser {

    // @Embedded notation flattens the properties of the User object into the object, preserving encapsulation.
    @Embedded
    User user;

    @Embedded(prefix = "tweet_")
    Tweet tweet;

    public static List<Tweet> getTweetList(List<TweetWithUser> tweetWithUsers) {
        List<Tweet> tweets = new ArrayList<>();
        for (int x = 0; x < tweetWithUsers.size(); x++) {
            Tweet tweet = tweetWithUsers.get(x).tweet;
            tweet.user = tweetWithUsers.get(x).user;
            tweets.add(tweet);
        }
        return tweets;
    }


}
