package com.co.hr.chat.service;

import com.co.hr.chat.dto.ChatRoomDto;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private Map<String, ChatRoomDto> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRoom(){
        List chatRooms = new ArrayList(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoomDto findRoomById(String id){
        return chatRoomMap.get(id);
    }

//    public ChatRoomDto createChatRoom(String name){
//        ChatRoomDto chatRoomDto = ChatRoomDto.create(name);
//        chatRoomMap.put(chatRoomDto.getRoomId(), chatRoomDto);
//        return chatRoomDto;
//    }

}
