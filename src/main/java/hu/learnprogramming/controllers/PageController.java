package hu.learnprogramming.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.learnprogramming.model.entity.StatusUpdate;
import hu.learnprogramming.service.StatusUpdateService;

@Controller
public class PageController {

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@Value("${message.error.forbiden}")
	private String accessDeniedMessage;

	@RequestMapping("/")
	// @ResponseBody
	ModelAndView home(ModelAndView modelAndView) {
		
		StatusUpdate statusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("app.homepage");
		return modelAndView;
	}
	
	@RequestMapping("/403")
	ModelAndView accessDenied(ModelAndView modelAndView) {
		modelAndView.getModel().put("message", accessDeniedMessage);
		modelAndView.setViewName("app.message");
		return modelAndView;
	}

	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
}
