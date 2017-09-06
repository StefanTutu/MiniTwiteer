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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TweetController {

	@RequestMapping(value = "/tweet/new", method = RequestMethod.GET)
	public ModelAndView newTweet(ModelAndView model, HttpServletRequest request) {
		model.addObject("tweet",Builder.tweetByUsers);
		model.setViewName("tweets/newTweetPage");
		return model;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/tweet/create", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView createTweet(@ModelAttribute("tweetForm") Tweet tweet, BindingResult result, Map model) {
		if (result.hasErrors()) {
			return new ModelAndView("tweet", model);
		}
		model.put("tweet", new Tweet());
		return new ModelAndView("redirect:/list_tweets", model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/tweets/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView gettweet(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		model.put("tweets", Builder.multipleTweets());
		model.put("tweet",Builder.tweetByUsers);
		return new ModelAndView("tweets/tweetsPage", model);
	}
	
	@RequestMapping(value = "/tweets/{username}/formatted", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView tweetsUser(ModelAndView model, @PathVariable String username,
			@RequestParam(value = "search", defaultValue = "", required = false) String search,
			HttpServletRequest request, HttpServletResponse response) {
		List<Tweet> listTweets = Builder.searchUserTweets(username, search);
		model.addObject("listTweets", listTweets);
		model.addObject("username", username);
		model.addObject("search", search);
		model.setViewName("tweets/tweetsPage");
		return model;
	}
}