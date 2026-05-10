package hs.gsm.maywave.ai.service;

import hs.gsm.maywave.ai.dto.AiFeedbackRequest;
import hs.gsm.maywave.ai.dto.AiFeedbackResponse;
import hs.gsm.maywave.ai.dto.RagDocument;
import hs.gsm.maywave.ai.prompt.PromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AiFeedbackService {

    private static final int MAX_FEEDBACK_LINES = 2;
    private static final int MAX_FEEDBACK_CHARS = 160;

    private final RagService ragService;
    private final PromptBuilder promptBuilder;
    private final GeminiService geminiService;

    public String generateFeedback(AiFeedbackRequest request) {
        return generateFeedbackWithDocuments(request).message();
    }

    public AiFeedbackResponse generateFeedbackWithDocuments(AiFeedbackRequest request) {
        List<RagDocument> relatedDocuments = ragService.search(request);
        String prompt = promptBuilder.buildFeedbackPrompt(request, relatedDocuments);
        String message = shortenFeedback(geminiService.generate(prompt));

        return new AiFeedbackResponse(message, relatedDocuments);
    }

    private String shortenFeedback(String message) {
        if (!StringUtils.hasText(message)) {
            return message;
        }

        String normalized = message.lines()
                .map(String::trim)
                .filter(line -> !line.isBlank())
                .limit(MAX_FEEDBACK_LINES)
                .collect(Collectors.joining("\n"));

        if (normalized.length() <= MAX_FEEDBACK_CHARS) {
            return normalized;
        }

        int limit = MAX_FEEDBACK_CHARS - 3;
        String clipped = normalized.substring(0, limit).trim();
        int sentenceEnd = findLastSentenceEnd(clipped);

        if (sentenceEnd >= limit / 2) {
            return clipped.substring(0, sentenceEnd + 1);
        }

        return clipped + "...";
    }

    private int findLastSentenceEnd(String text) {
        return Math.max(
                text.lastIndexOf('.'),
                Math.max(text.lastIndexOf('!'), text.lastIndexOf('?'))
        );
    }
}
