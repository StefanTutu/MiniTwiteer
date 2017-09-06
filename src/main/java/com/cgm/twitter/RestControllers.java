package com.cgm.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.Builder;
import com.cgm.builder.ServiceResponse;
import com.cgm.entities.Account;
import com.cgm.entities.Follow;
import com.cgm.entities.Tweet;

@SuppressWarnings("unused")
@RestController
public class RestControllers {
	/*
	 * 
	 * public final static Logger logger = (Logger)
	 * LoggerFactory.getLogger(RestControllers.class);
	 * 
	 * @RequestMapping(value = "/rest/tweet/{id}", method = RequestMethod.GET)
	 * protected Tweet getTweet(@PathVariable Long id) throws Exception {
	 * logger.info("Called get Tweet(" + id + ") service!"); return
	 * Builder.anTweetWithId(); }
	 * 
	 * @RequestMapping(value = "/rest/tweet", method = RequestMethod.POST, consumes
	 * = "application.json", produces = "application/json") public ServiceResponse
	 * createArtist(Tweet tweet) { logger.info("Called create Tweet service!");
	 * return new ServiceResponse(); }
	 * 
	 * @RequestMapping(value = "/rest/tweet", method = RequestMethod.PUT, consumes =
	 * "application/json", produces = "application/json") public ServiceResponse
	 * updateTweet(Tweet tweet) { logger.info("Called update Tweet service!");
	 * return new ServiceResponse(); }
	 * 
	 * @RequestMapping(value = "/rest/tweet/{id}", method = RequestMethod.DELETE)
	 * public ServiceResponse deleteTweet(@RequestHeader("token") String
	 * token, @PathVariable Long id) { logger.info("Called delete Tweet(" + id +
	 * ") service with token: " + token + " !"); return new ServiceResponse(); }
	 */

	@RequestMapping(value = "/mesaj/message", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody Tweet tweet(@RequestBody Tweet tweet, HttpServletRequest request) {
		Builder.tweetByUsers.get(request.getSession().getAttribute("username").toString()).add(tweet);
		System.out.println(
				Builder.tweetByUsers.get(request.getSession().getAttribute("username").toString()).get(0).getTweet());
		return tweet;
	}
	
    @RequestMapping(value = "/tweets", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json","application/xml"})
    @ResponseBody
    public List<Tweet> tweets_XML_JSON(@RequestParam(value = "search", defaultValue = "", required=false) String search) {
        List<Tweet> listTweets = Builder.searchTweets(search);
        return listTweets;
    }

    @RequestMapping(value = "/tweets/{username}", method = RequestMethod.GET, consumes = "application/json", produces = {"application/json","application/xml"})
    @ResponseBody
    public List<Tweet> tweetsUser_XML_JSON(@PathVariable String username, @RequestParam(value = "search", defaultValue = "", required=false) String search) {
    	List<Tweet> listTweets = Builder.searchUserTweets(username, search);
        return listTweets;
    }
	
    
}
