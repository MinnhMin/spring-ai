package com.example.springai.controller;

import com.example.springai.dto.ChatRequest;
import com.example.springai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    String chatMessage(@RequestBody ChatRequest request) {
        return chatService.chat(request);
    }

    // Endpoint for multimodal AI chat using text and image input
    @PostMapping("/chat-with-image")
    String chatWithImage(@RequestParam("file") MultipartFile file,
                         @RequestParam("message") String message) {
        return chatService.chatWithImage(file, message);
    }
}
