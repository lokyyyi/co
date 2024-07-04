package com.co.hr.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
   public enum MessageType {
       ENTER, TALK
   }
   private MessageType type;
   private String roomId;
   private String message;
   private String sender;
   private String filePath;
}
