package com.may18.init;

import com.may18.entity.Scenario;
import com.may18.entity.Choice;
import com.may18.repository.ScenarioRepository;
import com.gsm.hs.ChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ScenarioRepository scenarioRepository;
    private final ChoiceRepository choiceRepository;

    @Override
    public void run(String... args) {

        Scenario scenario = Scenario.builder()
                .roleCode(2)
                .situation("군인들이 시내로 이동합니다")
                .build();

        scenarioRepository.save(scenario);

        Choice c1 = Choice.builder()
                .content("가까이 가서 본다")
                .scenario(scenario)
                .build();

        Choice c2 = Choice.builder()
                .content("멀리서 지켜본다")
                .scenario(scenario)
                .build();

        choiceRepository.save(c1);
        choiceRepository.save(c2);
    }
}