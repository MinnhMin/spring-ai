package com.example.springai.controller;

import com.example.springai.dto.ChatRequest;
import com.example.springai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    String chatMessage(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }
}
