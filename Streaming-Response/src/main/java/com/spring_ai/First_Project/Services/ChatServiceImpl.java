package com.spring_ai.First_Project.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    @Value("classpath:/prompts/user-message.st")
            private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
            private Resource systemMessage;


    ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    @Override
    public String chatTemplate(String query) {

        return this.chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

    @Override
    public Flux<String> streamChat(String query) {
        return this.chatClient
                .prompt()
                //.system(system-> system.text(this.systemMessage))
                //.user(user-> user.text(this.userMessage).param("concept",query))
                .user("Explain this briefly and clearly: " + query)
                .stream()
                .content();




    }
}
