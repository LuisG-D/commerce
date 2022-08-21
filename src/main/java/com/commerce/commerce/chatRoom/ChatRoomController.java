package com.commerce.commerce.chatRoom;

import com.commerce.commerce.chatMessage.ChatMessage;
import com.commerce.commerce.chatMessage.ChatMessageDTO;
import com.commerce.commerce.chatMessage.ChatMessageRepository;
import com.commerce.commerce.entity.AppUser;
import com.commerce.commerce.entity.Mayorista;
import com.commerce.commerce.exception.*;
import com.commerce.commerce.repository.AppUserRepository;
import com.commerce.commerce.repository.MayoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatRoomController {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    private AppUserRepository userRepository;
    private Mayorista mayorista;

    @GetMapping("/chatrooms")
    public List<ChatRoom> getRooms(){
        return chatRoomRepository.findAll();
    }
    @RequestMapping(value = "/chatrooms", method = RequestMethod.POST)
    public ChatRoom createRooms(@RequestBody ChatRoomDTO chatRoomDTO,ArrayList<ChatMessageDTO> chatMessageListDTO){
        ArrayList<ChatMessage> newMessages = new ArrayList<>();
        for (ChatMessageDTO chatMessageDTO : chatMessageListDTO) {
            ChatMessage newMessage = new ChatMessage();
            Optional<ChatRoom> room = chatRoomRepository.findById(chatMessageDTO.getChatRoomId());
            Optional<AppUser> user = userRepository.findByUsername(chatMessageDTO.getEmisor());
                newMessage.setChatRoom(room.get());
                newMessage.setTextMessage("Hola buenas a todos");

                newMessage.setEmisor(user.get());

                newMessages.add(newMessage);
            }



        ChatRoom newRoom = new ChatRoom(
                chatRoomDTO.getReceiver(), chatRoomDTO.getEmisor(), newMessages);
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