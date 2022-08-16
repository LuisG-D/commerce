package com.commerce.commerce.chatMessage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatRoomId(Long roomId);
    List<ChatMessage> findAllByChatRoomIdAndCreationDateTimeAfter(Long roomId, Date timeFilter);
}