package ru.ddc.b2bcolab.controller.websocket;

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
import ru.ddc.b2bcolab.controller.websocket.payload.PrivateWebSocketMessage;
import ru.ddc.b2bcolab.model.Message;
import ru.ddc.b2bcolab.model.User;
import ru.ddc.b2bcolab.service.ChatMessageService;
import ru.ddc.b2bcolab.service.MemberStore;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MemberStore memberStore;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

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
    public void getMessage(PrivateWebSocketMessage webSocketMessage) {
        System.out.println("---There");
        System.out.println(webSocketMessage);
//        chatMessageService.createChatMessage(webSocketMessage);
//        Message newMessage = new Message(null, message.receiverId(), message.comment());
//        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);
    }

    @MessageMapping("/privatemessage")
    public void getPrivateMessage(PrivateWebSocketMessage webSocketMessage) {
        System.out.println(webSocketMessage);
//        Message newMessage = new Message(new User(null, webSocketMessage.user().serialId(), webSocketMessage.user().username()), webSocketMessage.receiverId(), webSocketMessage.comment());
//        simpMessagingTemplate.convertAndSendToUser(memberStore.getMember(webSocketMessage.receiverId()).id(), "/topic/privatemessages", newMessage);
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
