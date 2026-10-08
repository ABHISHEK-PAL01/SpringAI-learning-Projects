package com.RAG_Advisors.Service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataLoader {
    public List<Document> loadPdfData();

    public List<Document> loadJSONData();
}
