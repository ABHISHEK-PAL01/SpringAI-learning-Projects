package com.ChatMemory.Service;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
    private ChatClient chatClient;

    ChatServiceImpl(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    //private ChatClient chatClient;
   // ChatServiceImpl(ChatClient.Builder builder){
      //  this.chatClient = builder.build();
   // }

    @Override
    public String ChatTemplate(String query,String userId) {
        return chatClient
                .prompt()
                .user(query)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,userId))
                .call()
                .content();
    }
}
