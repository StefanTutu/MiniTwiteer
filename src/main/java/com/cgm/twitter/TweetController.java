package com.cgm.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.cgm.builder.Builder;
import com.cgm.entities.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TweetController {

    // NEW TWEET PAGE
    @RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
    public ModelAndView newTweet(ModelAndView model) {
        Tweet newTweet = new Tweet();
        model.addObject("tweet", newTweet);
        model.setViewName("tweets/newTweetPage");
        return model;
    }

    // NEW TWEET
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/tweet/create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createTweet(@ModelAttribute("tweetForm") Tweet tweet,BindingResult result, 
    		Map model) {
    	if(result.hasErrors()){
    		return new ModelAndView("tweet", model);
    	}
    	model.put("tweet", new Tweet());
    	return new ModelAndView("redirect:/list_tweets",model);
    	}

   // GET ALL TWEETS
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/tweets/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView getArtists() throws Exception {
		Map model = new HashMap();
		model.put("tweets", Builder.multipleTweets());
		return new ModelAndView("tweets/tweetsPage", model);
	}

    // GET TWEETS OF A USER
    @RequestMapping(value = "/tweets/{username}/formatted", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView tweetsUser(ModelAndView model, @PathVariable String username, @RequestParam(value = "search", defaultValue = "", required=false) String search) {
        List<String> listTweets = Builder.searchUserTweets(username, search);
        model.addObject("listTweets", listTweets);
        model.addObject("username", username);
        model.addObject("search", search);
        model.setViewName("tweets/tweetsPage");
        return model;
    }
    
}