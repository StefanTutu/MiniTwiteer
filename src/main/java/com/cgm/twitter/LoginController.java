package com.cgm.twitter;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgm.entities.Account;
import com.cgm.model.AccountModel;

@Controller
@RequestMapping(value = "account")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "redirect:account/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Locale locale, ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		modelMap.put("account", new Account());
		Account acc = checkCookie(request);
		if (acc == null) {
			modelMap.put("acount", new Account());
			return "loginPage";
		} else {
			AccountModel accModel = new AccountModel();
			if (accModel.login(acc.getUsername(), acc.getPassword())) {
				session.setAttribute("username", acc.getUsername());
				request.getSession().setAttribute("username", acc.getUsername());
				return "indexPage";
			} else {
				modelMap.put("error", "Account's Invalid");
				return "loginPage";
			}
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute(value = "account") Account account, ModelMap modelMap, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		AccountModel accModel = new AccountModel();
		if (accModel.login(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			if (request.getParameter("remember") != null) {
				Cookie ckUsername = new Cookie("username", account.getUsername());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password", account.getPassword());
				ckPassword.setMaxAge(3600);
				response.addCookie(ckPassword);
			}
			return "indexPage";
		} else {
			modelMap.put("error", "Account's Invalid");
			return "loginPage";
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute("username");
		for (Cookie ck : request.getCookies()) {
			if (ck.getName().equalsIgnoreCase("username")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
			if (ck.getName().equalsIgnoreCase("password")) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
		}
		return "redirect:login";
	}

	public Account checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Account account = null;
		String username = "", password = "";
		for (Cookie ck : cookies) {
			if (ck.getName().equalsIgnoreCase("username"))
				username = ck.getValue();
			if (ck.getName().equalsIgnoreCase("password"))
				password = ck.getValue();
		}
		if (!username.isEmpty() && !password.isEmpty())
			account = new Account(username, password);
		return account;

	}

}
