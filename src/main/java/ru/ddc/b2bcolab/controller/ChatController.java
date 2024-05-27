package ru.ddc.b2bcolab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.ddc.b2bcolab.model.Message;
import ru.ddc.b2bcolab.model.User;
import ru.ddc.b2bcolab.service.MemberStore;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MemberStore memberStore;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/chat")
    public String index() {
        return "chat";
    }

    @MessageMapping("/user")
    public void getusers(User user, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        User newUser = new User(user.id(), null, user.username());
        headerAccessor.getSessionAttributes().put("user", newUser);
        memberStore.addMember(newUser);
        sendMembersList();
        Message newMessage = new Message(new User(null, null, user.username()), null, null);
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);

    }

    @MessageMapping("/message")
    public void getMessage(Message message, Principal principal) throws Exception {
        System.out.println("---There");
        System.out.println("message.comment:    " + message.comment());
        System.out.println("message.receiverId: " + message.receiverId());
        System.out.println("message.user:       " + message.user());
        System.out.println("principal:          " + principal);
        Message newMessage = new Message(null, message.receiverId(), message.comment());
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);
    }

    @MessageMapping("/privatemessage")
    public void getPrivateMessage(Message message) throws Exception {
        Message newMessage = new Message(new User(null, message.user().serialId(), message.user().username()), message.receiverId(), message.comment());
        simpMessagingTemplate.convertAndSendToUser(memberStore.getMember(message.receiverId()).id(), "/topic/privatemessages", newMessage);

    }

    @EventListener
    public void handleSessionConnectEvent(SessionConnectEvent event) {
        System.out.println("Session Connect Event");
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        System.out.println("Session Disconnect Event");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }
        User user = (User) sessionAttributes.get("user");
        if (user == null) {
            return;
        }
        memberStore.removeMember(user);
        sendMembersList();

        Message message = new Message(new User(null, null, user.username()), null, "");
        simpMessagingTemplate.convertAndSend("/topic/messages", message);

    }

    private void sendMembersList() {
        List<User> memberList = memberStore.getMembersList();
        memberList.forEach(
                sendUser -> simpMessagingTemplate.convertAndSendToUser(sendUser.id(), "/topic/users", memberStore.filterMemberListByUser(memberList, sendUser)));
    }
}
