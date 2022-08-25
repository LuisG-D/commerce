package com.commerce.commerce.entity;

import com.commerce.commerce.dto.ChatMessageDTO;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "messages")
public class ChatMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private long id;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;


    @ManyToOne
    @JoinColumn(name = "emisor", nullable = false)
    private AppUser emisor;

    @Column(nullable = false)
    private String textMessage;

    //TimeStamp
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;


    public ChatMessage() {
    }

    public ChatMessage(ChatRoom chatRoom,
                       String textMessage,
                       Date creationDateTime,
                       AppUser emisor) {
        this.chatRoom = chatRoom;
        this.emisor = emisor;
        this.textMessage = textMessage;
        this.creationDateTime = creationDateTime;
    }

    public ChatMessageDTO createDTO() {
    return new ChatMessageDTO(chatRoom.getId(),
            emisor.getUsername(),
            textMessage,
            creationDateTime);}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Date getCreated_at() {
        return creationDateTime;
    }

    public void setCreated_at(Date created_at) {
        this.creationDateTime = created_at;
    }

    public AppUser getEmisor() {
        return emisor;
    }

    public void setEmisor(AppUser emisor) {
        this.emisor = emisor;
    }


}