package org.skcorg.springsecurity.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This displays the welcome screen that shows what will be happening in this
 * chapter.
 * 
 * @author Sumit Chandna
 * 
 */
@Controller
public class WelcomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "index";
	}

	@RequestMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/events/";
		}
		return "redirect:/";
	}

	@RequestMapping("/login/form")
	public String login() {
		return "login";
	}

	@RequestMapping("/errors/403")
	public String error403() {
		return "/errors/403";
	}
}