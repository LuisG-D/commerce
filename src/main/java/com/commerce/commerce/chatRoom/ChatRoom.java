package com.commerce.commerce.chatRoom;

import com.commerce.commerce.chatMessage.ChatMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "chat_room")

public class ChatRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_room_id")
    private long id;

    /*@Column(nullable = false)
    private String name;*/

    @Column(nullable = false)
    String receiver;

    @Column(nullable = false)
    String emisor;

    @OneToMany(
            mappedBy = "chatRoom",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    @JsonIgnore
    private List<ChatMessage> chatMessageList = new ArrayList<>();

    public ChatRoom() {
    }

    public ChatRoom(String receiver, String emisor, List<ChatMessage> chatMessageList) {
        this.receiver = receiver;
        this.emisor = emisor;
        this.chatMessageList = chatMessageList;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {

        this.chatMessageList = chatMessageList;
    }
}