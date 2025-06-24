package com.smokefree.service;

import com.smokefree.entity.ChatSession;
import com.smokefree.repository.ChatSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatSessionRepository chatSessionRepository;

    public ChatSession createChatSession(int userId, int coachId) {
        ChatSession session = new ChatSession(userId, coachId);
        return chatSessionRepository.save(session);
    }

    public List<ChatSession> getChatSessionsByUserId(int userId) {
        return chatSessionRepository.findByUserId(userId);
    }
}