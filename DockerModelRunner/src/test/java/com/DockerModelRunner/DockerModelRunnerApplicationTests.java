package com.DockerModelRunner;

import com.DockerModelRunner.Configuration.Ai_config;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class DockerModelRunnerApplicationTests {


	@Autowired
	private ChatClient chatClient;
	@Test
	void aitesting() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Starting........");
		var query = "hi";
		//System.out.println("Enter Your Query??");
		//var query = sc.nextLine();
		var content = this.chatClient.prompt().user(query).call().content();
		System.out.println(content);
	}

}
