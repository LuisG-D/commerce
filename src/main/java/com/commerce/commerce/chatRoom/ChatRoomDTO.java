package com.commerce.commerce.chatRoom;

import lombok.Data;

import java.util.List;

import javax.persistence.Column;

@Data
public class ChatRoomDTO {

    @Column(nullable = false)
    String receiver;

    @Column(nullable = false)
    String emisor;

    String message;

    List<String> products;

    public List<String> getProducts() {
        return this.products;
    }

    public void setProducts(List<String> products){
        this.products = products;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

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