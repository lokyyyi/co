package com.co.hr.chat.controller;

import com.co.hr.chat.dto.ChatMessageDto;
import com.co.hr.chat.dto.ChatRoomDto;
import com.co.hr.chat.service.ChatRoomService;
import com.co.hr.chat.service.ChatService;
import com.co.hr.common.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRestController {
//    private final SimpMessagingTemplate messagingTemplate;
//    private final SimpMessageSendingOperations messagingTemplate;
//    private final ChatRoomService chatRoomService;
//
//    @PostMapping("/message")
//    public ResponseEntity<ResultDto<String>> sendMessage(@RequestBody ChatMessageDto dto){
//        if(ChatMessageDto.MessageType.ENTER.equals(dto.getType())){
//            dto.setMessage(dto.getMessage() + "님이 입장하셨습니다.");
//        }
//        log.info("=======dfdfdf======");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + dto.getRoomId(), dto);
//        log.info("=======dfdfdf======");
//        return ResponseEntity.ok(ResultDto.res(200, dto.getMessage()));
//    }
//
//    @PostMapping("/room")
//    public ResponseEntity<ResultDto<ChatRoomDto>> createRoom(@RequestParam String name){
//        return ResponseEntity.ok(ResultDto.res(200, "채팅방 개설이 되었습니다", chatRoomService.createChatRoom(name)));
//    }
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name){
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDto> findAllRoom(){
        return chatService.findAllRoom();
    }
}
