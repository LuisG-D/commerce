package com.commerce.commerce.chatRoom;

import com.commerce.commerce.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatRoomController {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/chatrooms")
    public List<ChatRoom> getRooms(){
        List<ChatRoom> rooms = chatRoomRepository.findAll();
        if(rooms.isEmpty()){
            throw new ChatroomsNotExistException("");
        }

        return chatRoomRepository.findAll();
    }

    @RequestMapping(value = "/chatrooms", method = RequestMethod.POST)
    public ChatRoom createRooms(@RequestBody ChatRoomDTO chatRoomDTO){
        ChatRoom newRoom = new ChatRoom(
                chatRoomDTO.getReceiver(), chatRoomDTO.getEmisor(), new ArrayList<>());
        Optional <ChatRoom> existingRoom = chatRoomRepository.findByReceiverAndEmisor
                (newRoom.getReceiver(), newRoom.getEmisor());
        if(existingRoom.isPresent()){
            throw new CreateRoomException(newRoom.getReceiver());
        }
        else if(chatRoomDTO.getReceiver().equals(chatRoomDTO.getEmisor())){
            throw new EqualUserException(newRoom.getEmisor());
        }
        return chatRoomRepository.save(newRoom);
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