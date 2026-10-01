package com.spring_ai.First_Project.Service;

import com.spring_ai.First_Project.Entities.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    @Override
    public String chat(String query) {
        String prompt = "Tell me about my future wife,how is she,tell about her nature,behavior,body type,skin.And My name is Abhishek Pal. I was born in 24 August 2001";
        //String content= chatClient
                        // .prompt()
                        // .user(prompt)
                        // .system("As an expert Astrologer")
                        // .call()
                        // .content();
        Prompt prompt1 = new Prompt(prompt);

       // var content = chatClient
               // .prompt(prompt1)
               // .call()
               //.content();

        // We can also get meta data of prompt
        var metadata = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getMetadata();
        System.out.println(metadata);
        return "";
    }
    //then you get output of metadata of your prompt
    /*{ id: gen-1788697995-OzvkjoJ3O5xUINgoAU8L, usage: DefaultUsage{promptTokens=204,
     completionTokens=100, totalTokens=304,
    cacheReadInputTokens=128}, rateLimit: org.springframework.ai.chat.metadata.EmptyRateLimit@20a7ee36 }
     */

    @Override
    public Tut response(String query) {

        Prompt prompt2 = new Prompt(query);
        Tut tutorial = chatClient
                .prompt(prompt2)
                .call()
                .entity(Tut.class);

        return tutorial;
    }

}
