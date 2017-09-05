package com.cgm.twitter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.Builder;
import com.cgm.builder.ServiceResponse;
import com.cgm.entities.Account;
import com.cgm.entities.Tweet;

@SuppressWarnings("unused")
@RestController
public class RestControllers {

	public final static Logger logger = (Logger) LoggerFactory.getLogger(RestControllers.class);

	@RequestMapping(value = "/rest/tweet/{id}", method = RequestMethod.GET)
	protected Tweet getTweet(@PathVariable Long id) throws Exception {
		logger.info("Called get Tweet(" + id + ") service!");
		return Builder.anTweetWithId();
	}

	@RequestMapping(value = "/rest/tweet", method = RequestMethod.POST, consumes = "application.json", produces = "application/json")
	public ServiceResponse createArtist(Tweet tweet) {
		logger.info("Called create Tweet service!");
		return new ServiceResponse();
	}

	@RequestMapping(value = "/rest/tweet", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ServiceResponse updateTweet(Tweet tweet) {
		logger.info("Called update Tweet service!");
		return new ServiceResponse();
	}

	@RequestMapping(value = "/rest/tweet/{id}", method = RequestMethod.DELETE)
	public ServiceResponse deleteTweet(@RequestHeader("token") String token, @PathVariable Long id) {
		logger.info("Called delete Tweet(" + id + ") service with token: " + token + " !");
		return new ServiceResponse();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addTweet", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ModelAndView addTweet(HttpServletRequest req, @ModelAttribute("message") String message) throws Exception {
		logger.info("Called update Tweet service!");

		Map model = new HashMap();
		model.put("tweets", Builder.multipleTweets());
		Account acc = (Account) req.getSession().getAttribute("username");
		Builder.addTweet(message);
		model.put("tweets", Builder.getMessages());
		return new ModelAndView("tweets/tweetsPage", model);

	}

}
