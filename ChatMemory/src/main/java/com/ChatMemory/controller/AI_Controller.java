package com.ChatMemory.controller;

import com.ChatMemory.Service.ChatService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class AI_Controller {
    private ChatService chatService;
    AI_Controller(ChatService chatService){
        this.chatService = chatService;
    }
    @GetMapping("/ai")
    public ResponseEntity<String> nvidiaAi(@RequestParam(value="q",required=true)String q){
        return ResponseEntity.ok(chatService.ChatTemplate(q));
    }
}
