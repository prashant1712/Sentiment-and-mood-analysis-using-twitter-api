
package MyProject;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Prashant
 */
public class get_favourite {
    public  ArrayList <String>find(String s) {
        ArrayList <String>t = new ArrayList<String>();
    try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
              .setOAuthConsumerKey("DRNoYpQqqkPJP5RNncoFsccdq")
              .setOAuthConsumerSecret("kBc07OSNYymcpLD43ecpMCA7wWXCilOvzraFof0jUr0FulW3lq")
              .setOAuthAccessToken("1140773094-JJxbIjL3CYD2yiB3IbjRk7SJd6dxgplC3eR4FzU")
              .setOAuthAccessTokenSecret("fFLOgEmqWjnF8gTZ6aMBJfgUOdv7wZwV8e6TuyVTZJTvj");
             /*Query query = new Query("spmailme4");
               QueryResult result;
               query.setSince("2017-04-15");
               query.setUntil("2017-04-29");*/
            	  TwitterFactory factory = new TwitterFactory(cb.build());
              Twitter twitter = factory.getInstance();
              Paging paging = new Paging(1,12);
              String[] srch = new String[] {s};
              //System.out.println(Arrays.toString(srch));
              ResponseList<User> users = twitter.lookupUsers(srch);
              for (User user : users) {
                //System.out.println("Friend's Name " + user.getName()); // this print my friends name
                  
                     System.out.println("hi");
                       if (user.getStatus() != null) 
                    {
                       // System.out.println("Friend timeline");
                    
                    
                       //result=twitter.search(query);
                       //System.out.println(result.getTweets());
                  
                   
                        ResponseList<Status> statusess = twitter.getFavorites(s, paging);
                   
                    
                    for (Status status3 : statusess) 
                     {
                            t.add(status3.getText());
                            /*c++;
                            System.out.println(c);
                     */}
                    
                   
                    }
                    
                }
              }
            
            catch (TwitterException te){
                te.printStackTrace();
                System.out.println("Failed to get timeline: " + te.getMessage());
                System.exit(-1);
        }
    return t;
    }
}
