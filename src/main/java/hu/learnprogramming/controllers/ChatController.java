package hu.learnprogramming.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.learnprogramming.model.dto.SimpleMessage;
import hu.learnprogramming.model.entity.SiteUser;
import hu.learnprogramming.service.UserService;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate simpleMessagingTemplate;
	
	@Autowired
	private Util util;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/chatview/{chatWithUserID}")
	ModelAndView chatView(ModelAndView modelAndView, @PathVariable Long chatWithUserID) {
		
		SiteUser thisUser = util.getUser();
		String chatWithUserName = userService.getUserName(chatWithUserID);		
		
		modelAndView.setViewName("chat.chatview");
		modelAndView.getModel().put("thisUserID", thisUser.getId());
		modelAndView.getModel().put("chatWithUserID", chatWithUserID);
		modelAndView.getModel().put("chatWithUserName", chatWithUserName);
		
		return modelAndView;
	}
	
	@MessageMapping("/message/send/{toUserID}")
	public void send(Principal principal, SimpleMessage message, @DestinationVariable Long toUserID) {
		System.out.println(message);
		
		String fromUserName = principal.getName();
		SiteUser fromUser = userService.get(fromUserName);
		Long fromUserId = fromUser.getId();
		
		String returnReceiptQueue = String.format("/queue/%d", fromUserId);
		simpleMessagingTemplate.convertAndSendToUser(fromUserName, returnReceiptQueue, message);
	}
}
