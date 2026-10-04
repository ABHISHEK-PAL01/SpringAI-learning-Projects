package com.RAG_Advisors.Service;




import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import javax.naming.CompositeName;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;
    private VectorStore vectorStore;

    ChatServiceImpl(ChatClient chatClient,VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Override
    public String ChatTemplate(String query) {


        return chatClient
                .prompt()
                .user(query)
                .call()
                .content();
    }

    @Override
    public void saveData(List<String> list) {
        List<Document> documentList =  list.stream().map(Document::new).toList();
        this.vectorStore.add(documentList);
    }
}
