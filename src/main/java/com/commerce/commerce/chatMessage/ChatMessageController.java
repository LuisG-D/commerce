package com.commerce.commerce.chatMessage;


import com.commerce.commerce.chatRoom.ChatRoom;
import com.commerce.commerce.chatRoom.ChatRoomRepository;
import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.exception.*;
import com.commerce.commerce.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChatMessageController {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private AppUserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(ChatMessageController.class);


    @GetMapping("/chatmessages")
    public List<ChatMessageDTO> getAllMessages() {
        List<ChatMessage> existMessages = chatMessageRepository.findAll();
        if (existMessages.isEmpty()) {

            throw new MessageNotExistException("");

        }

        return chatMessageRepository.findAll().parallelStream().map(ChatMessage::createDTO).collect(Collectors.toList());
    }

    @GetMapping("/chatmessages/{id}")
    public Optional<ChatMessageDTO> getMessageById(@PathVariable long id) {

        Optional<ChatMessage> message = chatMessageRepository.findById(id);

        if (!message.isPresent()) {
            throw new IdChatException(id);
        }


        return chatMessageRepository.findById(id).map(ChatMessage::createDTO);
    }

    @PostMapping(value = "/chatmessages")
    public List<ChatMessageDTO> postNewMessage(@RequestBody List<ChatMessageDTO> chatMessageListDTO) {
        List<ChatMessage> newMessages = new ArrayList<>();

        for (ChatMessageDTO chatMessageDTO : chatMessageListDTO) {
            ChatMessage newMessage = new ChatMessage();
            Optional<ChatRoom> room = chatRoomRepository.findById(chatMessageDTO.getChatRoomId());
           // Optional<AppUser> user = userRepository.findByUsername(chatMessageDTO.getAuthorName());
            Optional<AppUser> user = userRepository.findByUsername(chatMessageDTO.getEmisor());
            if (room.isEmpty()) {
                log.warn("ROOM NOT FOUND");
                throw new UsernameNotFoundException("room");
            } else if (user.isEmpty()) {
                log.warn("USER NOT FOUND");
                throw new UsernameNotFoundException("user");
            } else {
                newMessage.setChatRoom(room.get());
                newMessage.setEmisor(user.get());
                newMessage.setCreated_at(chatMessageDTO.getCreated_at());
                newMessage.setTextMessage(chatMessageDTO.getTextMessage());
                newMessages.add(newMessage);
            }

        }
        return chatMessageRepository
                .saveAll(newMessages)
                .parallelStream()
                .map(ChatMessage::createDTO)
                .collect(Collectors.toList())
                ;
    }


    @GetMapping("/chatrooms/{roomId}/chatmessages")
    public List<ChatMessageDTO> findAllByChatRoomId(@PathVariable long roomId) {
        List<ChatMessage> message = chatMessageRepository.findAllByChatRoomId(roomId);
        if(message.isEmpty()) {
            throw new MessagesInChatIdException(roomId);
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.now().minusMinutes(5);
        Date date = Date.from(zonedDateTime.toInstant());

        return chatMessageRepository.findAllByChatRoomId(roomId)
                .parallelStream().map(ChatMessage::createDTO)
                .collect(Collectors.toList());


    }
}

