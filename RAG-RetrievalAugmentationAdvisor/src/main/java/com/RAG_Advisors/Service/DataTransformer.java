package com.RAG_Advisors.Service;

import org.springframework.ai.document.Document;

import java.util.List;

public interface DataTransformer {
    public List<Document> transform(List<Document> documents);
}
