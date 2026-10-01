package com.spring_ai.First_Project;

import com.spring_ai.First_Project.Service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstProjectApplicationTests {
	@Autowired
	private ChatService chatService;


	@Test
	void contextLoads() {
	}
	@Test
   void templatetest(){
		var output = this.chatService.fluentAPIChatTemplate();
		System.out.println(output);
   }
}
