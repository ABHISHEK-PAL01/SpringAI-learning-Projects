package com.ToolCalling.Configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AI_Config {



    @Bean
    public ChatClient chatModel(ChatClient.Builder builder){
        ChatClient chatClient = builder
                                 .defaultAdvisors(new SimpleLoggerAdvisor())
                                 .build();
        return chatClient;
    }
}
