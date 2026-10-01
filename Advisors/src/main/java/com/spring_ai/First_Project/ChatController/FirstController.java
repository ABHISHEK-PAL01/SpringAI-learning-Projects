package com.spring_ai.First_Project.ChatController;

import com.spring_ai.First_Project.Entities.Tut;
import com.spring_ai.First_Project.Service.ChatService;
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
    private ChatService chatService;
    FirstController(ChatService chatService){
        this.chatService = chatService;
    }

    @GetMapping("/max")
    public ResponseEntity<String> chatting(@RequestParam(value="q",required=true)String q){

        return ResponseEntity.ok(chatService.chat(q));
    }


}
