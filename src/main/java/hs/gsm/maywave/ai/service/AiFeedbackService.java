package hs.gsm.maywave.ai.service;

import hs.gsm.maywave.ai.dto.AiFeedbackRequest;
import hs.gsm.maywave.ai.dto.AiFeedbackResponse;
import hs.gsm.maywave.ai.dto.RagDocument;
import hs.gsm.maywave.ai.prompt.PromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiFeedbackService {

    private final RagService ragService;
    private final PromptBuilder promptBuilder;
    private final GeminiService geminiService;

    public String generateFeedback(AiFeedbackRequest request) {
        return generateFeedbackWithDocuments(request).message();
    }

    public AiFeedbackResponse generateFeedbackWithDocuments(AiFeedbackRequest request) {
        List<RagDocument> relatedDocuments = ragService.search(request);
        String prompt = promptBuilder.buildFeedbackPrompt(request, relatedDocuments);
        String message = geminiService.generate(prompt);

        return new AiFeedbackResponse(message, relatedDocuments);
    }
}
