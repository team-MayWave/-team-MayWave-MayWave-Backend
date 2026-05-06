package hs.gsm.maywave.ai.service;

import hs.gsm.maywave.ai.config.GeminiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final GeminiProperties properties;
    private final RestClient.Builder restClientBuilder;

    public String generate(String prompt) {
        if (!StringUtils.hasText(properties.getApiKey())) {
            throw new IllegalStateException("Gemini API key is not configured.");
        }

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        Map<?, ?> response = restClientBuilder
                .baseUrl(properties.getBaseUrl())
                .defaultHeader("x-goog-api-key", properties.getApiKey())
                .build()
                .post()
                .uri("/models/{model}:generateContent", properties.getModel())
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(Map.class);

        return extractText(response);
    }

    private String extractText(Map<?, ?> response) {
        if (response == null) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object candidatesObject = response.get("candidates");
        if (!(candidatesObject instanceof List<?> candidates) || candidates.isEmpty()) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object candidateObject = candidates.get(0);
        if (!(candidateObject instanceof Map<?, ?> candidate)) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object contentObject = candidate.get("content");
        if (!(contentObject instanceof Map<?, ?> content)) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object partsObject = content.get("parts");
        if (!(partsObject instanceof List<?> parts) || parts.isEmpty()) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object partObject = parts.get(0);
        if (!(partObject instanceof Map<?, ?> part)) {
            return "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다.";
        }

        Object text = part.get("text");
        return text == null ? "당신의 선택은 당시의 혼란과 두려움 속에서 나온 판단으로 볼 수 있습니다." : String.valueOf(text);
    }
}
