package hs.gsm.maywave.ai.dto;

import java.util.List;

public record RagDocument(
        String id,
        String title,
        String date,
        String location,
        List<String> emotionTags,
        List<String> actionTags,
        String situation,
        String historicalSummary
) {
}
