package com.ChatMemory.Service;

import java.util.List;

public interface ChatService {
    public String ChatTemplate(String query,String userId);
    void saveData(List<String> list);
}
