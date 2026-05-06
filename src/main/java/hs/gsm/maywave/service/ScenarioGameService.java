package hs.gsm.maywave.service;

import hs.gsm.maywave.ai.dto.AiFeedbackRequest;
import hs.gsm.maywave.ai.service.AiFeedbackService;
import hs.gsm.maywave.dto.GameResponse;
import hs.gsm.maywave.entity.Scenario;
import hs.gsm.maywave.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioGameService {

    private final ScenarioRepository scenarioRepository;
    private final AiFeedbackService aiFeedbackService;

    public GameResponse play(Integer roleId, Long scenarioId, Integer choice) {

        // 1. 시나리오 조회
        Scenario scenario = scenarioRepository.findByIdAndRoleId(scenarioId, roleId)
                .orElseThrow(() -> new RuntimeException("Scenario not found"));

        // 2. 역할 변환
        String role = convertRole(roleId);

        // 3. 선택 텍스트 변환
        String choiceText = getChoiceText(scenario, choice);

        // 4. AiFeedbackRequest 생성 (필드 4개)
        AiFeedbackRequest request = new AiFeedbackRequest(
                role,
                scenario.getSituation(),
                choiceText,
                null
        );

        String message = aiFeedbackService.generateFeedback(request);

        // 5. 결과 반환
        return new GameResponse(message);
    }

    private String convertRole(Integer roleId) {
        return switch (roleId) {
            case 1 -> "의사";
            case 2 -> "시민";
            case 3 -> "기자";
            default -> "unknown";
        };
    }

    private String getChoiceText(Scenario s, Integer choice) {
        return switch (choice) {
            case 1 -> s.getChoice1();
            case 2 -> s.getChoice2();
            case 3 -> s.getChoice3();
            default -> "";
        };
    }
}