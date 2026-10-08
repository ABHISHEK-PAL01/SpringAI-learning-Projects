package com.ToolCalling.Service;

import com.ToolCalling.Tools.AI_Tools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
    private ChatClient chatClient;
    ChatServiceImpl(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    @Override
    public String ChatTemplate(String query) {
        return chatClient
                .prompt()
                .tools(new AI_Tools())
                .user(query)
                .call()
                .content();
    }
}
