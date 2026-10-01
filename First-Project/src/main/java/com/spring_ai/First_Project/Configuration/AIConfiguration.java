package com.spring_ai.First_Project.Configuration;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {
    //ChatModel Config
    @Bean
    public OpenAiChatModel nvidiaChatModel(){
        return OpenAiChatModel.builder()
                .options(OpenAiChatOptions.builder()
                        .apiKey(System.getenv("NVIDIA_API_KEY"))
                        .baseUrl("https://integrate.api.nvidia.com/v1")
                        .model("nvidia/nemotron-3.5-lightning-30b-a3b")
                        .maxTokens(2048)
                        .build())
                .build();
    }
    @Bean
    public OpenAiChatModel maxChatModel(){
        return OpenAiChatModel.builder()
                .options(OpenAiChatOptions.builder()
                        .apiKey(System.getenv("OPENROUTER_API_KEY"))
                        .baseUrl("https://openrouter.ai/api/v1")
                        .model("minimax/minimax-m3:free")
                        .maxTokens(2000)
                        .build())
                .build();
    }
    //ChatClient
    @Bean(name = "nvidiaChatClient")
    public ChatClient nvidiaChatClient(
            OpenAiChatModel nvidiaChatModel) {

        return ChatClient.builder(nvidiaChatModel).build();
    }


    @Bean(name = "gemmaChatClient")
    public ChatClient maxChatClient(
            OpenAiChatModel maxChatModel) {

        return ChatClient.builder(maxChatModel).build();
    }
}