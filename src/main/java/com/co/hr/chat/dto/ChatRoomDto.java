package com.co.hr.chat.dto;

import com.co.hr.chat.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//public class ChatRoomDto {
//    private String roomId;
//    private String name;
//
//    public static ChatRoomDto create(String name){
//        ChatRoomDto chatRoomDto = new ChatRoomDto();
//        chatRoomDto.name = name;
//        chatRoomDto.roomId = UUID.randomUUID().toString();
//        chatRoomDto.name = name;
//        return chatRoomDto;
//    }
//
//}
public class ChatRoomDto {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoomDto(String roomId, String name){
        this.roomId = roomId;
        this.name = name;
    }
    public void handleActions(WebSocketSession session, ChatMessageDto chatMessageDto, ChatService chatService){
        if(chatMessageDto.getType().equals(ChatMessageDto.MessageType.ENTER)){
            sessions.add(session);
            chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장했습니다. 하이하이댄스");
        }
        sendMessage(chatMessageDto, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService){
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
