package com.RAG_Advisors;

import com.RAG_Advisors.Service.ChatService;
import com.RAG_Advisors.Service.DataLoader;
import com.RAG_Advisors.Service.DataTransformer;
import com.RAG_Advisors.helper.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RagAdvisorsApplicationTests {

	@Autowired
	private EmbeddingModel embeddingModel;


	@Autowired
	private ChatService chatService;
	@Autowired
	private DataTransformer dataTransformer;
	@Autowired
	private VectorStore vectorStore;

	@Test
	void saveDataToVectorDatabases() {
		System.out.println("Saving data to databases");
		this.chatService.saveData(Helper.getData());
		System.out.println("Data is saved successfully");

	}

	@Autowired
	private DataLoader dataLoader;
    @Test
	void testDataLoader() {
		var documents = dataLoader.loadJSONData();
		System.out.println(documents.size());
		documents.forEach(item->{
			System.out.println(item);
		});
	}
    @Test
	void testpdfDataLoader(){
		var pdf = dataLoader.loadPdfData();
		System.out.println(pdf.size());
		pdf.forEach(item->{
			System.out.println(item);
			System.out.println("-----------------------------------------");
		});
		System.out.println("Transforming Data......");

		var transform = this.dataTransformer.transform(pdf);
		System.out.println(transform.size());
		transform.forEach(item->{
			System.out.println(item);
		});

		System.out.println("Adding Data To DataBase");
		this.vectorStore.add(transform);
		System.out.println("DONE.");
	}
}
