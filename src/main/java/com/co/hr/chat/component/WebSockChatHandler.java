package com.co.hr.chat.component;
// import 생략....

import com.co.hr.chat.dto.ChatMessageDto;
import com.co.hr.chat.dto.ChatRoomDto;
import com.co.hr.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

// import 생략....

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
//        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^");
//        session.sendMessage(textMessage);\
        ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
        ChatRoomDto roomDto = chatService.findRoomById(chatMessageDto.getRoomId());
        roomDto.handleActions(session, chatMessageDto, chatService);
    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info("{} 연결되었습니다.", session.getId());
//        super.afterConnectionEstablished(session);
//        sessionMap.put(session.getId(), session);
//
//        //클라이언트에게 메세지 전달
//        session.sendMessage(new TextMessage("연결완료"));
//    }
//
//    /* 클라이언트가 소켓 종료시 동작 */
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info("{} 연결이 종료되었습니다.", session.getId());
//        super.afterConnectionClosed(session, status);
//        sessionMap.remove(session.getId());
//
//        String userName = userMap.get(session.getId());
//        for(String key : sessionMap.keySet()) {
//            WebSocketSession wss = sessionMap.get(key);
//
//            if(wss == session) continue;
//
//            wss.sendMessage(new TextMessage("연결종료"));
//        }
//        userMap.remove(session.getId());
//    }
}