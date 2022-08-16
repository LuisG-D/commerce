package com.commerce.commerce.chatMessage;

import java.util.Date;

public class ChatMessageDTO {


    private Long chatRoomId;


    private String emisor;

    private String textMessage;

    private Date created_at;

    public ChatMessageDTO(Long chatRoomId,String emisor, String textMessage, Date created_at) {
        this.chatRoomId = chatRoomId;
        this.emisor = emisor;
        this.textMessage = textMessage;
        this.created_at = created_at;
    }

    public ChatMessageDTO() {

    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }


    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }


    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}