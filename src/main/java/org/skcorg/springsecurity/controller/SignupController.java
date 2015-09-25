package org.skcorg.springsecurity.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.skcorg.springsecurity.domain.CalendarUser;
import org.skcorg.springsecurity.model.SignupForm;
import org.skcorg.springsecurity.service.CalendarService;
import org.skcorg.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {
	private final UserContext userContext;
	private final CalendarService calendarService;

	@Autowired
	public SignupController(UserContext userContext,
			CalendarService calendarService) {
		if (userContext == null) {
			throw new IllegalArgumentException("userContext cannot be null");
		}
		if (calendarService == null) {
			throw new IllegalArgumentException("calendarService cannot be null");
		}
		this.userContext = userContext;
		this.calendarService = calendarService;
	}

	@RequestMapping("/signup/form")
	public String signup(HttpServletRequest request,
			@ModelAttribute SignupForm signupForm) {
		SignupForm sessionSignupForm = (SignupForm) request.getSession()
				.getAttribute("signupForm");
		if (sessionSignupForm != null) {
			signupForm.setEmail(sessionSignupForm.getEmail());
			signupForm.setOpenId(sessionSignupForm.getOpenId());
		}
		return "signup/form";
	}

	@RequestMapping(value = "/signup/new", method = RequestMethod.POST)
	public String signup(@Valid SignupForm signupForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "signup/form";
		}

		String email = signupForm.getEmail();
		if (calendarService.findUserByEmail(email) != null) {
			result.rejectValue("email", "errors.signup.email",
					"Email address is already in use.");
			return "signup/form";
		}

		CalendarUser user = new CalendarUser();
		user.setEmail(email);
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		user.setPassword(signupForm.getPassword());
		user.setOpenId(signupForm.getOpenId());
		CalendarUser calendarUser = calendarService.createUser(user);
		if (calendarUser != null) {
			user.setId(calendarUser.getId());
		}
		userContext.setCurrentUser(user);
		redirectAttributes.addFlashAttribute("message", "Success");
		return "redirect:/";
	}
}
