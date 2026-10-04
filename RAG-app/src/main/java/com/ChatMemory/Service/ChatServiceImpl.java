package com.ChatMemory.Service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatServiceImpl implements ChatService{
    private ChatClient chatClient;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private VectorStore vectorStore;
    @Value("classpath:/prompt/system-message.st")
    private Resource systemMessage;
    @Value("classpath:/prompt/user-message.st")
    private Resource userMessage;

    ChatServiceImpl(ChatClient chatClient,VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    //private ChatClient chatClient;
   // ChatServiceImpl(ChatClient.Builder builder){
      //  this.chatClient = builder.build();
   // }

    @Override
    public String ChatTemplate(String query,String userId) {
        QuestionAnswerAdvisor ragAdvisor = QuestionAnswerAdvisor.builder(vectorStore).build();
        //load data from vector database
        SearchRequest searchRequest = SearchRequest.builder()
                .topK(5)
                .similarityThreshold(0.6)
                .query(query)
                .build();
       List<Document> documents = this.vectorStore.similaritySearch(searchRequest);
       List<String> documentList = documents.stream().map(Document::getText).toList();
       String contextData = String.join(",",documentList);
       logger.info("Context Data: {}",contextData);

       //similar result user query
        //pass in context



        return chatClient
                .prompt()
                .advisors(ragAdvisor)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,"user123"))
                .system(system->system.text(this.systemMessage).param("documents",contextData))
                .user(user->user.text(this.userMessage).param("query",query))
                .call()
                .content();
    }

    @Override
    public void saveData(List<String> list) {

      List<Document> documentList =  list.stream().map(Document::new).toList();
      this.vectorStore.add(documentList);
    }
}
