package com.spring_ai.First_Project.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;

import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;



@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder

                .defaultOptions(OpenAiChatOptions.builder()
                                .model("nvidia/nemotron-3.5-lightning-30b-a3b")
                                .temperature(0.3)
                                .maxTokens(1000)

                )
                .build();
    }

    @Override
    public String chat(String query) {
        String prompt = "hi";
        String content = chatClient
                .prompt()
                .user(query)
                .system("give response in friendly way")
                .call()
                .content();
        return content;
    }

}
