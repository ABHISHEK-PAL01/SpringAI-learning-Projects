package com.spring_ai.First_Project.ChatController;

import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class FirstController {
    private ChatClient nvidiaChatClient;
    private ChatClient maxChatClient;

    public FirstController(@Qualifier("nvidiaChatClient")ChatClient nvidiaChatClient,@Qualifier("maxChatClient") ChatClient maxChatClient) {
        this.nvidiaChatClient = nvidiaChatClient;
        this.maxChatClient = maxChatClient;
    }

    @GetMapping("/nemotron")
    public ResponseEntity<String> chat(@RequestParam(value="q",required=true)String q){
     var resultResponse=nvidiaChatClient.prompt(q).call().content();

        return ResponseEntity.ok(resultResponse);
    }
    @GetMapping("/max")
    public ResponseEntity<String> chatting(@RequestParam(value="q",required=true)String q){
        var resultResponse=maxChatClient.prompt(q).call().content();
        return ResponseEntity.ok(resultResponse);
    }

}
