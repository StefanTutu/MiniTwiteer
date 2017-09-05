package com.cgm.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cgm.entities.Account;
import com.cgm.entities.Follow;
import com.cgm.entities.Tweet;
import com.cgm.entities.UserStatus;

public class Builder {

	public static Account allAccount() {
		Account account = new Account();
		account.setPassword("1234");
		account.setUsername("user1");
		account.setPassword("1234");
		account.setUsername("user2");
		account.setPassword("1234");
		account.setUsername("user3");
		account.setPassword("1234");
		account.setUsername("user4");
		account.setPassword("1234");
		account.setUsername("user5");
		return account;
	}

	public static Tweet allTweet() {
		Tweet tweet = new Tweet();
		tweet.setTweet("Tweet 1");
		tweet.setUser_username("user1");
		tweet.setTweet("Tweet 2");
		tweet.setUser_username("user1");
		tweet.setTweet("Tweet 3");
		tweet.setUser_username("user3");
		return tweet;
	}

	public static Follow allFollow() {
		Follow follow = new Follow();
		follow.setFollower("user2");
		follow.setUser_followed("user1");
		follow.setFollower("user1");
		follow.setUser_followed("user2");
		follow.setFollower("user3");
		follow.setUser_followed("user1");
		return follow;
	}

	public static UserStatus allUserStatus() {
		UserStatus userStatus = new UserStatus();
		userStatus.setUsername("user1");
		userStatus.setStatus(true);
		userStatus.setUsername("user2");
		userStatus.setStatus(true);
		userStatus.setUsername("user3");
		userStatus.setStatus(true);
		userStatus.setUsername("user4");
		userStatus.setStatus(true);
		userStatus.setUsername("user5");
		userStatus.setStatus(true);
		return userStatus;
	}

	public static List<Tweet> tweet() {
		List<Tweet> tweet = new ArrayList<Tweet>();
		return tweet;
	}

	public static List<Follow> follow() {
		List<Follow> follow = new ArrayList<Follow>();
		return follow;
	}

	public static List<UserStatus> userStatus() {
		List<UserStatus> userStatus = new ArrayList<UserStatus>();
		return userStatus;
	}

	public static Set<Tweet> tweets() {
		Set<Tweet> tweets = new HashSet<Tweet>();
		tweets.add(allTweet());
		return tweets;
	}

	public static Set<Follow> follows() {
		Set<Follow> follows = new HashSet<Follow>();
		follows.add(allFollow());
		return follows;
	}

	public static Set<UserStatus> usersStatus() {
		Set<UserStatus> usersStatus = new HashSet<UserStatus>();
		usersStatus.add(allUserStatus());
		return usersStatus;
	}

	public static Tweet anTweet(String username, String tweetText) {
		Tweet tweet = new Tweet();
		tweet.setUser_username(username);
		tweet.setTweet(tweetText);
		return tweet;
	}

	public static Follow anFollow(String username1, String username2) {
		Follow follow = new Follow();
		follow.setFollower(username1);
		follow.setUser_followed(username2);
		return follow;
	}

	public static UserStatus anUserStatus(String username, Boolean status) {
		UserStatus userStatus = new UserStatus();
		userStatus.setUsername(username);
		userStatus.setStatus(status);
		return userStatus;
	}

	public static List<String> searchTweets(String search) {
		List<String> Tweet = new ArrayList<String>();
		Tweet.add("tweet 1");
		Tweet.add("tweet 2");
		List<String> tweetClone = new ArrayList<String>();
		for (String tweetSearch : Tweet) {
			if (tweetSearch.equals(search)) {
				tweetClone.add(tweetSearch);
			}
		}
		return tweetClone;
	}

	public static List<String> searchUserTweets(String username, String search) {

		username = getUsername();
		Tweet tweet = new Tweet();
		tweet.setTweet("The cat is on the table");
		List<String> tweetClone = new ArrayList<String>();
		if (tweet.getTweet().contains(search)) {
			tweetClone.add(search);
		}
		return tweetClone;

	}

	public static String getUsername() {
		String username;
		Account account = new Account();
		username = account.getUsername();
		return username;

	}

	public static Follow follow(String user) {
		String username = getUsername();
		Follow follow = new Follow();
		follow.setFollower(username);
		follow.setUser_followed(user);
		return follow;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static Follow unfollow(String user) {
		String username = getUsername();
		Set<Follow> follows = new HashSet<Follow>();
		follows.add(allFollow());
		follows.remove(user);
		follows.remove(username);
		return (Follow) follows();
	}

	@SuppressWarnings("unlikely-arg-type")
	public static HashSet<UserStatus> listUsers() {
		final String username = getUsername();
		HashSet<Account> accounts = new HashSet<Account>();
		accounts.add(allAccount());
		accounts.remove(username);
		UserStatus user = new UserStatus();
		Set<UserStatus> userStatus = new HashSet<UserStatus>();
		userStatus.add(allUserStatus());
		user.setUsername(username);
		if (user.getUsername() != null) {
			user.setStatus(true);
		} else {
			user.setStatus(false);
		}

		return listUsers();
	}

	public static Tweet anTweetWithId() {
		Tweet tweet = new Tweet();
		tweet.setId(1);
		tweet.setTweet("Some tweet text");
		tweet.setUser_username("user 1");
		return tweet;
	}

	public static List<Tweet> multipleTweets() {
		List<Tweet> tweetss = new ArrayList<Tweet>();
		tweetss.add(anTweet("user1", "tweet user1"));
		tweetss.add(anTweet("user2", "tweet user2"));
		tweetss.add(anTweet("user1", "tweet user1 second"));
		tweetss.add(anTweet("user3", "tweet user3"));
		tweetByUser.put("user1", "tweet user1 map");
		tweetByUser.put("user2", "tweet user2 map");
		return tweetss;
	}

	public static Map<String, List<Tweet>> tweetByUsers = new HashMap<String, List<Tweet>>();
	
	

	public static Map<String, String> tweetByUser = new HashMap<String, String>();

	public static Map<String, List<Follow>> followByUser = new HashMap<String, List<Follow>>();

	public static List<Follow> listFollowing() {
		List<Follow> following = new ArrayList<Follow>();
		following.add(new Follow("user1", "user2"));
		following.add(new Follow("user1", "user3"));
		following.add(new Follow("user1", "user4"));
		followByUser.put("user1", following);
		return following;

	}

	public static List<Follow> listFollowers() {

		List<Follow> following = new ArrayList<Follow>();
		following.add(new Follow("user2", "user1"));
		following.add(new Follow("user3", "user1"));
		following.add(new Follow("user4", "user1"));
		following.add(new Follow("user5", "user1"));
		followByUser.put("user2", following);
		followByUser.put("user3", following);
		followByUser.put("user4", following);
		followByUser.put("user5", following);
		return following;

	}

	static {
		tweetByUsers.put("user1", new ArrayList<Tweet>());
		tweetByUsers.put("user2", new ArrayList<Tweet>());
		tweetByUsers.put("user3", new ArrayList<Tweet>());
	}
	
	public static ArrayList<Tweet> tweet;

	public static ArrayList<Tweet> getMessages() {
		return tweet;
	}

	public static ArrayList<Tweet> tweets;

	public static void addTweet(String tweet) {
		Tweet newTweet = new Tweet();
		newTweet.setTweet(tweet);
		newTweet.setUser_username(getUsername());
		tweets.add(newTweet);
		tweetByUser.put(getUsername(), tweet);
	}
}
