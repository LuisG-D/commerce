package com.commerce.commerce.controller;

import com.commerce.commerce.dto.ChatRoomDTO;
import com.commerce.commerce.repository.ChatRoomRepository;
import com.commerce.commerce.entity.ChatMessage;
import com.commerce.commerce.repository.ChatMessageRepository;
import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.entity.ChatRoom;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.exception.*;
import com.commerce.commerce.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ChatRoomController {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    private ChatMessageRepository chatMessageRepository;
    private AppUserRepository userRepository;
    private Mayorista mayorista;

    public ChatRoomController(
        ChatRoomRepository chatRoomRepository,
        ChatMessageRepository chatMessageRepository,
        AppUserRepository userRepository)
    {
        super();
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;

    }

    @GetMapping("/chatrooms")
    public List<ChatRoom> getRooms(){
        return chatRoomRepository.findAll();
    }

    @RequestMapping(value = "/chatrooms", method = RequestMethod.POST)
    public ResponseEntity<String> createRooms(@RequestBody ChatRoomDTO chatRoomDTO){

        Optional<AppUser> emisor = userRepository.findByUsername(chatRoomDTO.getEmisor());
        Optional<AppUser> receiver = userRepository.findByUsername(chatRoomDTO.getReceiver());

        if(!emisor.isPresent()){
            return new ResponseEntity<String>("Usuario emisor no encontrado", HttpStatus.BAD_REQUEST);
        }

        if(!receiver.isPresent()){
            return new ResponseEntity<String>("Usuario receptor no encontrado", HttpStatus.BAD_REQUEST);
        }

        if(chatRoomDTO.getMessage() == null){
            return new ResponseEntity<String>("Escriba un mensaje", HttpStatus.BAD_REQUEST);
        }

        Optional<ChatRoom> chatroomFind =
                chatRoomRepository.findByReceiverAndEmisor(chatRoomDTO.getReceiver(),
                        chatRoomDTO.getEmisor());
        ChatRoom chatRoom; 
        List<ChatMessage> newMessages = new ArrayList<>();
        

        if(!chatroomFind.isPresent()){        
            chatRoom = chatRoomRepository.save(new ChatRoom(chatRoomDTO.getReceiver(), chatRoomDTO.getEmisor()));
        }else {
            chatRoom = chatroomFind.get();
        }
        
        if(chatRoomDTO.getMessage() != null){
            newMessages.add(new ChatMessage(
                chatRoom, 
                chatRoomDTO.getMessage(), 
                new Date(),
                emisor.get() 
            ));
        }

        if(chatRoomDTO.getProducts() != null){  
            String message = "Productos para ofrecer: ";
            message += String.join(", ", chatRoomDTO.getProducts());

            newMessages.add(new ChatMessage(
                chatRoom, 
                message,
                new Date(),
                emisor.get() 
            ));
        }

        chatMessageRepository.saveAll(newMessages);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Mensaje enviado");

        return new ResponseEntity<String>("Mensaje enviado", HttpStatus.OK);
    }


    @RequestMapping("/chatrooms/{id}")
    public Optional<ChatRoom> getRoomById(@PathVariable long id){
    Optional<ChatRoom> room = chatRoomRepository.findById(id);

        if(room.isEmpty()){
            throw new ChatRoomsException(id);
        }
        return chatRoomRepository.findById(id);
    }
}