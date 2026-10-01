package com.spring_ai.First_Project.Service;

import com.spring_ai.First_Project.Entities.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient=builder
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("minimax/minimax-m3:free")
                        .temperature(0.3)
                        .maxTokens(1000)
                        .build()
                .build());
    }

    @Override
    public String chat(String query) {
        String prompt = "Tell me about india";
        String content = chatClient
                .prompt()
                .user(prompt)
                .system("As an expert Astrologer")
                .call()
                .content();
        return content;
    }

}
