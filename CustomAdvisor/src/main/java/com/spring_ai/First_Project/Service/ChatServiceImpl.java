package com.spring_ai.First_Project.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    ChatServiceImpl(
            @Qualifier("nvidiaChatClient") ChatClient chatClient) {

        this.chatClient = chatClient;
    }

    @Override
    public String chat(String query) {

        return chatClient
                .prompt()
                .user(query)
                .system("give response in friendly way")
                .call()
                .content();
    }
}