package com.ChatMemory;


import com.ChatMemory.Service.ChatService;
import com.ChatMemory.helper.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatMemoryApplicationTests {

	@Autowired
	private EmbeddingModel embeddingModel;



    @Autowired
	private ChatService chatService;
	@Test
    void saveDataToVectorDatabases(){
		System.out.println("Saving data to databases");
		this.chatService.saveData(Helper.getData());
		System.out.println("Data is saved successfully");

	}


}
