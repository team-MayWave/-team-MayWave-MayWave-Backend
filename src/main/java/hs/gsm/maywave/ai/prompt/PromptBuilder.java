package hs.gsm.maywave.ai.prompt;

import hs.gsm.maywave.ai.dto.AiFeedbackRequest;
import hs.gsm.maywave.ai.dto.RagDocument;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String buildFeedbackPrompt(AiFeedbackRequest request, List<RagDocument> documents) {
        StringBuilder builder = new StringBuilder();

        builder.append("""
                너는 5.18 민주화운동을 다루는 역사 피드백 AI다.

                반드시 지켜야 할 규칙:
                - 사용자의 선택을 비난하지 않는다.
                - 사용자의 선택을 과도하게 영웅화하지 않는다.
                - 제공된 역사 자료를 바탕으로만 5.18과 연결한다.
                - 당시의 두려움, 혼란, 폭력성, 시민들의 다양한 선택을 존중한다.
                - 답변은 3~5문장으로 짧고 공감적으로 작성한다.
                - 확실하지 않은 내용은 단정하지 않는다.
                - 출처, 문서 번호, 태그 이름은 출력하지 않는다.

                사용자 정보:
                """);

        builder.append("- 역할: ").append(valueOrEmpty(request.role())).append("\n");
        builder.append("- 주변 상황: ").append(valueOrEmpty(request.surroundingSituation())).append("\n");
        builder.append("- 사용자의 선택: ").append(valueOrEmpty(request.userChoice())).append("\n");
        builder.append("- 선택의 의미: ").append(valueOrEmpty(request.choiceMeaning())).append("\n\n");

        builder.append("관련 5.18 역사 자료:\n");
        for (int i = 0; i < documents.size(); i++) {
            RagDocument document = documents.get(i);

            builder.append(i + 1).append(". ").append(document.title()).append("\n");
            builder.append("- 시기: ").append(document.date()).append("\n");
            builder.append("- 장소: ").append(document.location()).append("\n");
            builder.append("- 당시 상황: ").append(document.situation()).append("\n");
            builder.append("- 역사 요약: ").append(document.historicalSummary()).append("\n\n");
        }

        builder.append("""
                최종 출력:
                사용자의 선택을 먼저 짚고, 실제 5.18 당시 시민들이 겪은 상황과 자연스럽게 연결해서 말해라.
                """);

        return builder.toString();
    }

    private String valueOrEmpty(String value) {
        return value == null ? "" : value;
    }
}
