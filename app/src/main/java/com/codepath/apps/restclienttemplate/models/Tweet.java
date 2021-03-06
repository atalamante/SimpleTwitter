package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public long id;

    public Tweet() {}

    public static Tweet fromJson (JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");
        return tweet;
    }

    public static List<Tweet> fromJSONArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int x = 0; x < jsonArray.length(); x++) {
            tweets.add(fromJson(jsonArray.getJSONObject(x)));
        }
        return tweets;
    }

    public static String getFormattedTimestamp (String timeStamp, Tweet tweet) {
        return TimeFormatter.getTimeDifference(tweet.createdAt);
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }
}
