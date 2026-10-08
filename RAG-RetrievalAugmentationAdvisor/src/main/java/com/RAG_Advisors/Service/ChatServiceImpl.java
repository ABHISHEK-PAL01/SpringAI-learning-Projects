package com.RAG_Advisors.Service;




import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.ai.rag.retrieval.join.ConcatenationDocumentJoiner;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import javax.sql.rowset.spi.TransactionalWriter;
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
       var Advisor = RetrievalAugmentationAdvisor.builder()
               .documentRetriever(VectorStoreDocumentRetriever.builder()
                       .topK(20)
                       .similarityThreshold(0.0)
                       .vectorStore(this.vectorStore)
                       .build())
               .queryAugmenter(ContextualQueryAugmenter.builder()
                       .allowEmptyContext(true)
                       .build())
               .build();

        return chatClient
                .prompt()
                .advisors(Advisor)
                .user(query)
                .call()
                .content();
    }

    @Override
    public void saveData(List<String> list) {
        List<Document> documentList =  list.stream().map(Document::new).toList();
        this.vectorStore.add(documentList);
    }

    @Override
    public String getResponse(String userQuery) {
        var advisor = RetrievalAugmentationAdvisor.builder()
                //PRE-RETRIEVAL
                .queryTransformers(
                        RewriteQueryTransformer.builder()
                                .chatClientBuilder(chatClient.mutate().clone())
                                .build(),
                        TranslationQueryTransformer.builder().chatClientBuilder(chatClient.mutate().clone()).targetLanguage("English").build()
                )
                .queryExpander(MultiQueryExpander.builder().chatClientBuilder(chatClient.mutate().clone()).numberOfQueries(3).build())
                //RETRIEVAL
                .documentRetriever(
                        VectorStoreDocumentRetriever.builder()
                                .vectorStore(vectorStore)
                                .topK(5)
                                .similarityThreshold(0.3)
                                .build()
                )
                .documentJoiner(new ConcatenationDocumentJoiner())
                .queryAugmenter(ContextualQueryAugmenter.builder().build())

                .build();


        return chatClient
                .prompt()
                .user(userQuery)
                .call()
                .content();
    }
}
