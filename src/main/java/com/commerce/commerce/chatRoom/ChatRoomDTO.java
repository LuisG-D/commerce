package com.commerce.commerce.chatRoom;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ChatRoomDTO {

    @Column(nullable = false)
    String receiver;
    @Column(nullable = false)
    String emisor;



    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

}