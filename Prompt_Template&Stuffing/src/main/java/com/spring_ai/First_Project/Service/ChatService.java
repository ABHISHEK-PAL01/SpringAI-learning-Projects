package com.spring_ai.First_Project.Service;

import com.spring_ai.First_Project.Entities.Tut;

public interface ChatService {
    public String chat(String query);

    public String chatTemplate();

    public String systemChatTemplate();
    public String fluentAPIChatTemplate();
}
