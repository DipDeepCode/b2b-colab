package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.ddc.b2bcolab.controller.payload.TechMessageAction;
import ru.ddc.b2bcolab.controller.payload.TechMessage;
import ru.ddc.b2bcolab.model.*;
import ru.ddc.b2bcolab.service.ChatMessageService;

@RestController
@RequiredArgsConstructor
public class WebSocketController {
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final ChatMessageService chatMessageService;

	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getUser();
		Customer connectedCustomer = (Customer) token.getPrincipal();
		TechMessage techMessage = new TechMessage(connectedCustomer.getPhoneNumber(), TechMessageAction.JOINED);
		simpMessagingTemplate.convertAndSend("/topic/tech", techMessage);
	}

	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getUser();
		Customer disconnectedCustomer = (Customer) token.getPrincipal();
		TechMessage techMessage = new TechMessage(disconnectedCustomer.getPhoneNumber(), TechMessageAction.LEFT);
		simpMessagingTemplate.convertAndSend("/topic/tech", techMessage);
	}

	@MessageMapping("/message")
	public void getMessage(ChatMessage chatMessage, Authentication authentication) {
		chatMessage.setSenderPhoneNumber(((Customer) authentication.getPrincipal()).getPhoneNumber());
		System.out.println("Customer sent message " + chatMessage);

		chatMessage = chatMessageService.addChatMessage(chatMessage);

		Long chatRoomId = chatMessage.getChatRoomId();
		simpMessagingTemplate.convertAndSend("/topic/messages/" + chatRoomId, chatMessage);
		System.out.println("Application sent message " + chatMessage + " to /topic/messages/" + chatRoomId);

	}
}
