package com.spring_ai.First_Project.Services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


public interface ChatService {
    String chatTemplate(String query);

    Flux<String> streamChat(String query);
}
