package com.cgm.twitter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.Builder;
import com.cgm.entities.Follow;
import com.cgm.entities.UserStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FollowController {

    // FOLLOW USER
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/users/follow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView followUser(@ModelAttribute("followForm") UserStatus user, Map model) {
    	Follow follow = (Follow) model.get("followPage");
		if (follow == null || follow.getUser_followed() == null) {
			model.put("followPage", new Follow());
		}
		Builder.follow(user.getUsername());

		return new ModelAndView("followPage", model);
    		
    	}


    // UNFOLLOW USER
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/users/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView unfollowUser(@ModelAttribute("unfollowForm") UserStatus user, Map model) {
        Builder.unfollow(user.getUsername());
        Follow follow = (Follow) model.get("follow");
        if (follow ==null || follow.getUser_followed()==null) {
        	model.put("followPage", new Follow());
        }
        return new ModelAndView("followPage", model);
    }

   
    // FOLLOWING PAGE
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/following/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView getFollowing() throws Exception {
		Map model = new HashMap();
		model.put("listFollowing", Builder.listFollowing());
		return new ModelAndView("follows/followingPage", model);
	}

    // FOLLOWERS PAGE
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/followers/formatted", method = RequestMethod.GET)
	protected @ResponseBody ModelAndView getFollowers() throws Exception {
		Map model = new HashMap();
		model.put("listFollowers", Builder.listFollowers());
		return new ModelAndView("follows/followersPage", model);
	}

}