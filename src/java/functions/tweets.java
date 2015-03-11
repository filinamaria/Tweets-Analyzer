package functions;

import java.sql.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class tweets {
    private String tweetuser[];
    private String tweettext[];
    
    public tweets(){
        tweetuser = new String[0];
        tweettext = new String[0];
    }
    public String[] GetTweetUser(){
        return tweetuser;
    }
    public String[] GetTweetText(){
        return tweettext;
    }
    public void SetTweet(String hashtag) throws TwitterException {
        Statement stmt1 = null;
        Statement stmt2 = null;
        
        tweettext = new String[100];
        tweetuser = new String[100];
        int i;
        for (i=0; i<100; i++){
            tweettext[i] = "null";
            tweetuser[i] = "null";
        }
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("nbkWBzHuX0LYmUEqINsee62uj")
                .setOAuthConsumerSecret("XIeZJiJ5WuZJAqh6ZpEpk7I8EGNu3dKUgvhwb7VSYrlLpq7T8t")
                .setOAuthAccessToken("76862882-RCWeqJSMi3OxXxhiqNh7CWAeGwZv3KvsJuY1eKe85")
                .setOAuthAccessTokenSecret("TlYgj3g49aLlTbbFpkAJ4JtnjDbZNsrcOpcYVrywwQ8w4");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        //
        int j = 0;
        Query query = new Query(hashtag);
        query.setCount(100);
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()){
            tweetuser[j] = status.getUser().getScreenName();
            tweettext[j] = status.getText();
            j++;
        }
    }
}
