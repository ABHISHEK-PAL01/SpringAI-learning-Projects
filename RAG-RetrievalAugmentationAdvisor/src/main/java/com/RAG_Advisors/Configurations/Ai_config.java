package com.RAG_Advisors.Configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ai_config {
    @Autowired
    private VectorStore vectorStore;
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){





        return builder
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .model("nvidia/nemotron-3.5-lightning-30b-a3b")
                                .temperature(0.3)
                                .maxTokens(300)
                )
                .build();

    }




}
