package com.cgm.twitter;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.builder.Builder;
import com.cgm.builder.ServiceResponse;
import com.cgm.entities.Tweet;

@SuppressWarnings("unused")
@RestController
public class RestControllers {
	
	public final static Logger logger = (Logger) LoggerFactory.getLogger(RestControllers.class);
	
	@RequestMapping(value="/rest/tweet/{id}", method = RequestMethod.GET)
	protected Tweet getTweet(@PathVariable Long id)throws Exception{
		logger.info("Called get Tweet("+id+") service!");
		return Builder.anTweetWithId();
	}
	
	@RequestMapping(value ="/rest/tweet", method=RequestMethod.POST, consumes = "application.json", produces = "application/json")
	public ServiceResponse createArtist(Tweet tweet) {
		logger.info("Called create Tweet service!");
		return new ServiceResponse();
	}
	
	@RequestMapping(value = "/rest/tweet", method=RequestMethod.PUT, consumes="application/json", produces="application/json" )
	public ServiceResponse updateTweet(Tweet tweet) {
		logger.info("Called update Tweet service!");
		return new ServiceResponse();
	}
	
	@RequestMapping(value= "/rest/tweet/{id}", method= RequestMethod.DELETE)
	public ServiceResponse deleteTweet(@RequestHeader("token") String token, @PathVariable Long id) {
		logger.info("Called delete Tweet("+id+") service with token: "+token+" !");
		return new ServiceResponse();
	}
	
	@RequestMapping(value = "/addTweet", method=RequestMethod.POST, consumes="application/json", produces="application/json" )
	public Tweet addTweet(@RequestBody String user, @RequestBody String tweet){
		logger.info("Called update Tweet service!");
		logger.info(tweet);
		System.out.println(user);
		return new Tweet();
	}
	

}
