package com.ChatMemory.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AI_Config {

    @Bean
    public ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository){
      return MessageWindowChatMemory.builder()
              .chatMemoryRepository(jdbcChatMemoryRepository)
              .maxMessages(10)
              .build();
    }

    private Logger logger= LoggerFactory.getLogger(AI_Config.class);

    @Bean
    public ChatMemory chatHistory(){
        return MessageWindowChatMemory.builder()
                .maxMessages(20)
                .build();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.logger.info("ChatMemoryImplementation class: "+chatMemory.getClass().getName());
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();



        return builder
                .defaultAdvisors(messageChatMemoryAdvisor)
                .defaultSystem("You are a helpful coding assistant. You are expert in coding")
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .model("nvidia/nemotron-3.5-lightning-30b-a3b")
                                .temperature(0.3)
                                .maxTokens(300)
                )
                .build();

    }
}
