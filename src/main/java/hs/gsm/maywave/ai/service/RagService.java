package hs.gsm.maywave.ai.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hs.gsm.maywave.ai.dto.AiFeedbackRequest;
import hs.gsm.maywave.ai.dto.RagDocument;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RagService {

    private final ObjectMapper objectMapper;
    private List<RagDocument> documents = List.of();

    @PostConstruct
    void loadDocuments() throws Exception {
        ClassPathResource resource = new ClassPathResource("rag/may18-history.json");

        try (InputStream inputStream = resource.getInputStream()) {
            documents = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<RagDocument>>() {
                    }
            );
        }
    }

    public List<RagDocument> search(AiFeedbackRequest request) {
        String query = normalize(String.join(" ",
                valueOrEmpty(request.role()),
                valueOrEmpty(request.surroundingSituation()),
                valueOrEmpty(request.userChoice()),
                valueOrEmpty(request.choiceMeaning())
        ));

        return documents.stream()
                .sorted(Comparator
                        .comparingInt((RagDocument document) -> score(document, query))
                        .reversed())
                .limit(3)
                .toList();
    }

    private int score(RagDocument document, String query) {
        int score = 0;

        score += countMatches(document.emotionTags(), query) * 3;
        score += countMatches(document.actionTags(), query) * 4;
        score += countTextMatches(document.title(), query);
        score += countTextMatches(document.situation(), query);
        score += countTextMatches(document.historicalSummary(), query);

        return score;
    }

    private int countMatches(List<String> tags, String query) {
        if (tags == null) {
            return 0;
        }

        return (int) tags.stream()
                .map(this::normalize)
                .filter(tag -> !tag.isBlank())
                .filter(query::contains)
                .count();
    }

    private int countTextMatches(String text, String query) {
        String normalizedText = normalize(text);
        if (normalizedText.isBlank()) {
            return 0;
        }

        int score = 0;
        for (String word : query.split(" ")) {
            if (word.length() >= 2 && normalizedText.contains(word)) {
                score++;
            }
        }
        return score;
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }

    private String normalize(String value) {
        return valueOrEmpty(value)
                .replace(",", " ")
                .replace(".", " ")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}
