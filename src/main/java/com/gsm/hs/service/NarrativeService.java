package com.gsm.hs.service;

import com.gsm.hs.dto.*;
import com.gsm.hs.entity.*;
import com.gsm.hs.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NarrativeService {

    private final ScenarioRepository scenarioRepository;
    private final ChoiceRepository choiceRepository;

    public NarrativeResponseDto process(NarrativeRequestDto request) {

        Scenario scenario = scenarioRepository.findById(request.getScenarioId())
                .orElseThrow();

        List<Choice> choices =
                choiceRepository.findByScenarioId(scenario.getId());

        Choice selected = choices.stream()
                .filter(c -> c.getId().equals(request.getChoiceId()))
                .findFirst()
                .orElseThrow();

        String role = switch (request.getRoleCode()) {
            case 1 -> "의사";
            case 2 -> "시민";
            case 3 -> "기자";
            default -> "unknown";
        };

        return new NarrativeResponseDto(
                role,
                scenario.getSituation(),
                selected.getContent()
        );
    }
}