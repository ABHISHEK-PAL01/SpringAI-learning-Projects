package com.spring_ai.First_Project.controller;

import com.spring_ai.First_Project.Services.ChatService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class model {
    private ChatService chatService;
    model(ChatService chatService){
        this.chatService=chatService;
    }

    @GetMapping("/ai")
    public ResponseEntity<String> chat(@RequestParam(value="q",required=true)String q){

        return ResponseEntity.ok(chatService.chatTemplate(q));
    }

    @GetMapping(value = "/stream-chat",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> streamResponse(
            @RequestParam("q") String query) {

        return this.chatService.streamChat(query);
    }
}



