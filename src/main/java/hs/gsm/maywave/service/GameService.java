package hs.gsm.maywave.service;

import hs.gsm.maywave.dto.ChoiceRequest;
import hs.gsm.maywave.dto.FeedbackResponse;
import hs.gsm.maywave.entity.ChoiceHistory;
import hs.gsm.maywave.entity.Scenario;
import hs.gsm.maywave.repository.ChoiceHistoryRepository;
import hs.gsm.maywave.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final ScenarioRepository scenarioRepository;
    private final ChoiceHistoryRepository choiceHistoryRepository;

    @Transactional
    public FeedbackResponse processUserChoice(ChoiceRequest request) {
        // 1. 시나리오 정보 조회
        Scenario scenario = scenarioRepository.findById(request.getChoiceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 선택지를 찾을 수 없습니다: " + request.getChoiceId()));

        // 2. 유저 선택 기록 저장
        ChoiceHistory history = ChoiceHistory.builder()
                .sessionId(request.getSessionId())
                .sceneId(request.getSceneId())
                .choiceId(request.getChoiceId())
                .build();
        choiceHistoryRepository.save(history);

        // 3. 응답 생성
        return FeedbackResponse.builder()
                .historyId(history.getId())
                .aiMessage(scenario.getAiMessage())
                .historicalFact(scenario.getHistoricalFact())
                .build();
    }
}