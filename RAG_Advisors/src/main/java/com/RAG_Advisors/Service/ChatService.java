package com.RAG_Advisors.Service;

import java.util.List;

public interface ChatService {
    public String ChatTemplate(String query);
    public void saveData(List<String> list);
}
