package com.DockerModelRunner.Configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ai_config {

    public ChatClient chatmodel(ChatClient.Builder builder){
        return builder.build();
    }
}
