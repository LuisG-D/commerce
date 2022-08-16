package com.commerce.commerce.chatRoom;

import com.commerce.commerce.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{
    Optional<ChatRoom> findByReceiverAndEmisor(String receiver, String emisor);
}