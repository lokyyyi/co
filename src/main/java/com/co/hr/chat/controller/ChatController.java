package com.co.hr.chat.controller;

import com.co.hr.chat.dto.ChatMessageDto;
import com.co.hr.chat.dto.ChatRoomDto;
import com.co.hr.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ChatController {
//    private final SimpMessagingTemplate  messagingTemplate;
    //private final SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/chat/message")
//    public void message(ChatMessageDto body) {
//        log.info(String.valueOf(body.getType()));
//        log.info(String.valueOf(ChatMessageDto.MessageType.ENTER));
//
//        System.out.println(body.getType());
//        System.out.println(ChatMessageDto.MessageType.ENTER);
//        if (ChatMessageDto.MessageType.ENTER.equals(body.getType())) {
//            body.setMessage(body.getSender() + "님이 입장하셨습니다.");
//        }
//        messagingTemplate.convertAndSend("/sub/chat/room/" + body.getRoomId(), body);
//    }
}
