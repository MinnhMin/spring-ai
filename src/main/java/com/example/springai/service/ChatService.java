package com.example.springai.service;

import com.example.springai.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient  chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient =  builder.build();
    }

    public String chat(ChatRequest request) {

        // System prompt to control AI behavior and response style comment
        SystemMessage systemMessage = new SystemMessage("""
                You are E-Learning AI
                You responses are clear, concise, and professional.
                """);

        UserMessage userMessage = new UserMessage(request.message);

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
