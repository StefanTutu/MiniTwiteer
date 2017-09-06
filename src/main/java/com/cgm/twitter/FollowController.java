package com.cgm.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.Builder;
import com.cgm.entities.Follow;
import com.cgm.entities.UserStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FollowController {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/users/follow", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView followUser(@ModelAttribute("followForm") UserStatus user, Map model) {

		UserStatus userStatus = (UserStatus) model.get("followPage");
		if(userStatus==null || userStatus.getUsername()== null) {
//			model.put("listUsers", new UserStatus(user,true));
		}
		Follow follow = (Follow) model.get("followPage");
		if (follow == null || follow.getUser_followed() == null) {
			model.put("listUsers", new Follow());
		}
		Builder.follow(user.getUsername());

		return new ModelAndView("followPage", model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/users/unfollow", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView unfollowUser(@ModelAttribute("unfollowForm") UserStatus user, Map model) {
		Builder.unfollow(user.getUsername());
		Follow follow = (Follow) model.get("follow");
		if (follow == null || follow.getUser_followed() == null) {
			model.put("listUsers", new Follow());
		}
		return new ModelAndView("followPage", model);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
		public ModelAndView followPage(ModelAndView model) {
		UserStatus followForm = new UserStatus();
		UserStatus unfollowForm = new UserStatus();
		model.addObject("followForm", followForm);
		model.addObject("unfollowForm", unfollowForm);
		model.addObject("listUsers", Builder.userStatusMap);
		model.setViewName("follows/followPage");
		return model;
	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/following/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView getFollowing() throws Exception {
		Map model = new HashMap();
		model.put("listFollowing", Builder.listFollowing());
		return new ModelAndView("follows/followingPage", model);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/followers/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView getFollowers() throws Exception {
		Map model = new HashMap();
		model.put("listFollowers", Builder.listFollowers());
		return new ModelAndView("follows/followersPage", model);
	}

}