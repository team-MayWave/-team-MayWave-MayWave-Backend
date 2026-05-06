package hs.gsm.maywave.ai.dto;

import java.util.List;

public record AiFeedbackResponse(
        String message,
        List<RagDocument> relatedDocuments
) {
}
