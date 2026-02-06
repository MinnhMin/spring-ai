package com.example.springai.service;

import com.example.springai.dto.BillItem;
import com.example.springai.dto.ChatRequest;
import com.example.springai.dto.ExpenseInfo;
import com.example.springai.dto.FilmInfo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ChatService {

    private final ChatClient  chatClient;

    public ChatService(ChatClient.Builder builder) {
        chatClient =  builder.build();
    }

    public List<BillItem> chatWithImage(MultipartFile file, String message) {
        // Build Media object from uploaded image for multimodal AI input
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        // Configure AI response behavior (deterministic output)
        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        return chatClient.prompt()
                .options(chatOptions)

                // Define AI role and response style
                .system("You are E-Learning AI ")

                // Combine text and image into a single user message
                .user(promptUserSpec
                -> promptUserSpec.media(media)
                .text(message))
                .call()
                .entity(new ParameterizedTypeReference<List<BillItem>>() {
                });
    }

//    public List<FilmInfo> chat(ChatRequest request) {
//
//        // System prompt to control AI behavior and response style comment
//        SystemMessage systemMessage = new SystemMessage("""
//                You are E-Learning AI
//                You responses are clear, concise, and professional.
//                """);
//
//        UserMessage userMessage = new UserMessage(request.message());
//
//        Prompt prompt = new Prompt(systemMessage, userMessage);
//
//        return chatClient
//                .prompt(prompt)
//                .call()
//                .entity(new ParameterizedTypeReference<List<FilmInfo>>() {
//                });
//    }

    public ExpenseInfo chat(ChatRequest request) {

        // System prompt to control AI behavior and response style comment
        SystemMessage systemMessage = new SystemMessage("""
                You are E-Learning AI
                You responses are clear, concise, and professional.
                """);

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(prompt)
                .call()
                .entity(ExpenseInfo.class);
    }
}
