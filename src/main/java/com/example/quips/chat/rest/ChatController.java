package com.example.quips.chat.rest;

import com.example.quips.chat.dto.SendMessageRequest;
import com.example.quips.chat.domain.model.Conversation;
import com.example.quips.chat.domain.model.Message;
import com.example.quips.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    // Crear o obtener una conversación

    @PostMapping("/conversation")
    public ResponseEntity<Conversation> createOrGetConversation(@RequestParam Long user1Id, @RequestParam Long user2Id) {
        Conversation conversation = chatService.getOrCreateConversation(user1Id, user2Id);

        // Manejar el caso en el que aún no haya mensajes
        if (conversation.getMessages() == null) {
            conversation.setMessages(new ArrayList<>());  // Establecer una lista vacía si no hay mensajes
        }

        return ResponseEntity.ok(conversation);
    }

    // Enviar un mensaje

    @PostMapping("/sendMessage")
    public ResponseEntity<Message> sendMessage(@RequestBody SendMessageRequest request) {  // Cambia a @RequestBody
        Message message = chatService.sendMessage(request.getSenderId(), request.getReceiverId(), request.getContent(), request.getConversationId());
        return ResponseEntity.ok(message);
    }

    // Obtener todos los mensajes de una conversación

    @GetMapping("/conversation/{conversationId}/messages")
    public ResponseEntity<List<Message>> getConversationMessages(@PathVariable Long conversationId) {
        List<Message> messages = chatService.getConversationMessages(conversationId);
        return ResponseEntity.ok(messages);
    }
}
