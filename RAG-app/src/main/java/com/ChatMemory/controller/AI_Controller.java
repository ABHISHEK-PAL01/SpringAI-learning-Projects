package com.ChatMemory.controller;

import com.ChatMemory.Service.ChatService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class AI_Controller {
    private ChatService chatService;
    AI_Controller(ChatService chatService){
        this.chatService = chatService;
    }
    @GetMapping("/ai")
    public ResponseEntity<String> nvidiaAi(@RequestParam(value="q",required=true)String q,
                                           String userId){
        return ResponseEntity.ok(chatService.ChatTemplate(q,userId));
    }
}
