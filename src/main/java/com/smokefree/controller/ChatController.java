package com.smokefree.controller;

import com.smokefree.dto.ChatSessionDTO;
import com.smokefree.entity.ChatSession;
import com.smokefree.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat_sessions")
    public ResponseEntity<ChatSession> createChatSession(@RequestBody ChatSessionDTO dto) {
        ChatSession session = chatService.createChatSession(dto.getUserId(), dto.getCoachId());
        return new ResponseEntity<>(session, HttpStatus.CREATED);
    }

    @GetMapping("/chat_sessions/{userId}")
    public ResponseEntity<List<ChatSession>> getChatSessions(@PathVariable int userId) {
        List<ChatSession> sessions = chatService.getChatSessionsByUserId(userId);
        return ResponseEntity.ok(sessions);
    }
}