package MyProject;
import java.util.*;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;

import java.util.ArrayList;

public class TwitterData {
	private final static String CONSUMER_KEY = "DRNoYpQqqkPJP5RNncoFsccdq";
    private final static String CONSUMER_KEY_SECRET = "kBc07OSNYymcpLD43ecpMCA7wWXCilOvzraFof0jUr0FulW3lq";
    
  
    public ArrayList<String> search(String searchTerm) throws TwitterException, IOException{
		//Get tweets and return an array of them
    	Twitter twitter = new TwitterFactory().getInstance();
    	twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
    	
    	String accessToken = getSavedAccessToken();
    	String accessTokenSecret = getSavedAccessTokenSecret();
    	AccessToken oathAccessToken = new AccessToken(accessToken,accessTokenSecret);
    	twitter.setOAuthAccessToken(oathAccessToken);

    	//Search twitter        
    	Paging paging = new Paging(1, 200);
    	
        Query query = new Query(searchTerm);
        query.setCount(50);
        QueryResult result = twitter.search(query);
        ArrayList <String>text = new ArrayList<String>();
        for (Status status : result.getTweets()) {
        	text.add(status.getText());
            //System.out.println(i+" @" + status.getUser().getScreenName() + ":" + status.getText());
        }
        return text;
    }
    
	public String[] retrieveTweets() throws TwitterException, IOException{
		//Get tweets and return an array of them
    	Twitter twitter = new TwitterFactory().getInstance();
    	twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
    	
    	String accessToken = getSavedAccessToken();
    	String accessTokenSecret = getSavedAccessTokenSecret();
    	AccessToken oathAccessToken = new AccessToken(accessToken,accessTokenSecret);
    	twitter.setOAuthAccessToken(oathAccessToken);

    	
    	System.out.println("\nAnalyzing...");
    	
    	// Paging, The factory instance is re-useable and thread safe.
    	List <String> tweets = new ArrayList<String>();
        // requesting page 2, number of elements per page is 40
        Paging paging = new Paging(1, 200);
        ResponseList<twitter4j.Status> statuses = twitter.getHomeTimeline(paging);
        for (twitter4j.Status status : statuses) {
            //System.out.println(status.getUser().getScreenName() + ":" + status.getText());
            tweets.add(status.getText());
        }//end for
        
        String[] stockArr = new String[tweets.size()];
        stockArr = tweets.toArray(stockArr);
        return stockArr;
	}//end method
	
	private String getSavedAccessTokenSecret() {
    	//Get saved access token secret
    	return "";
    }//end method
   
    private String getSavedAccessToken() {
    	//Get saved access token 
    	return "";
    }//end method
}
