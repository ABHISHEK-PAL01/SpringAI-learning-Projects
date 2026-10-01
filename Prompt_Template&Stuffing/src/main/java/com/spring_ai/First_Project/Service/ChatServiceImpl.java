package com.spring_ai.First_Project.Service;

import com.spring_ai.First_Project.Entities.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    ChatServiceImpl(ChatClient.Builder builder){
        this.chatClient=builder.build();
    }

    @Override
    public String chat(String query){
        Prompt prompt = new Prompt(query);
        String promptstr = "Act as an expert Historian:{query}";
        String content = chatClient
                .prompt()
                .user(u->u.text(promptstr).param("query",query))
                .call()
                .content();
        return content;
    }

    public String chatTemplate(){
        //First Step Creating Prompt Template
        PromptTemplate strTemplate=PromptTemplate.builder().template("What is {techName}? Tell also about {exampleName}").build();

        //Second Step Render that PromptTemplate
        String rendererMessage=strTemplate.render(Map.of(
                "techName" , "SpringBoot",
                "exampleName","React"
        ));

        //Third Step Creating Prompt
        Prompt prompt = new Prompt(rendererMessage);

        return this.chatClient.prompt(prompt).call().content();
    }
    //This is another way of creating Prompt Template with system Prompt
    public String systemChatTemplate(){
        SystemPromptTemplate systemPromptTemplate = SystemPromptTemplate.builder().template("You are expert Geologist and climatologist.You are very helpful to guide others")
                .build();
        var systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate strTemplate=PromptTemplate.builder().template("Why {techName} climate changes so rapidly. Why now people face {exampleName} suffer human life").build();
        var userMessage=strTemplate.createMessage(Map.of(
                "techName" , "Global",
                "exampleName","disasters"
        ));

        //Third Step Creating Prompt
        Prompt prompt = new Prompt(systemMessage,userMessage);

        return this.chatClient.prompt(prompt).call().content();
    }
    //Here we understand how simply PromptTemplate can we build using ChatClientfluentapi
    public String fluentAPIChatTemplate(){
        return this.chatClient.prompt()
                .system(system ->
                        system.text("You are an helpful coding and tech assistant.Act as mentor or guide to make user decision"))
                .user(user ->
                        user.text("Help me choose between these two tech stack.What should i choose {A} or {B}.I am confuse to take " +
                                "decision.Who is more secure and best in salary and opportunity ")
                                .param("A","MERN")
                                .param("B","Java"))
                .call()
                .content();
    }

}
