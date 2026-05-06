package com.gsm.hs;

import com.gsm.hs.entity.*;
import com.gsm.hs.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataIntializer implements CommandLineRunner {

    private final ScenarioRepository scenarioRepository;
    private final ChoiceRepository choiceRepository;

    @Override
    public void run(String... args) {

        // =========================
        // 🟢 시민 1
        // =========================
        Scenario citizen1 = scenarioRepository.save(
                Scenario.builder()
                        .roleCode(2)
                        .situation("잠시후, 군인들이 시내로 이동합니다")
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("가까이 가서 본다")
                        .scenario(citizen1)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("멀리서 지켜본다")
                        .scenario(citizen1)
                        .build()
        );

        // =========================
        // 🟢 시민 2
        // =========================
        Scenario citizen2 = scenarioRepository.save(
                Scenario.builder()
                        .roleCode(2)
                        .situation("많은 인파의 사람들이 몰려 옆에 사람이 쓰러진다")
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("쓰러진 사람에게 다가간다")
                        .scenario(citizen2)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("뒤로 물러나 상황을 피한다")
                        .scenario(citizen2)
                        .build()
        );

        // =========================
        // 🟡 의사 1
        // =========================
        Scenario doctor1 = scenarioRepository.save(
                Scenario.builder()
                        .roleCode(1)
                        .situation("동료가 전남대쪽에서 응급 환자가 몰려온다는 소식을 전함")
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("환자에게 바로 달려간다")
                        .scenario(doctor1)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("병원으로 돌아가 대비한다")
                        .scenario(doctor1)
                        .build()
        );

        // =========================
        // 🟡 의사 2
        // =========================
        Scenario doctor2 = scenarioRepository.save(
                Scenario.builder()
                        .roleCode(1)
                        .situation("갑자기 몰려오는 응급환자들에 의해 선택의 갈림길에 놓임")
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("이 환자를 먼저 살린다")
                        .scenario(doctor2)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("다른 부상자들을 확인한다")
                        .scenario(doctor2)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("병원으로 이송을 요청한다")
                        .scenario(doctor2)
                        .build()
        );

        // =========================
        // 🔴 기자 1
        // =========================
        Scenario reporter1 = scenarioRepository.save(
                Scenario.builder()
                        .roleCode(3)
                        .situation("군인들과 시민들이 뒤엉키면서 부상자들이 발생하는 상황을 실시간으로 촬영 중")
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("계속 촬영한다")
                        .scenario(reporter1)
                        .build()
        );

        choiceRepository.save(
                Choice.builder()
                        .content("도망친다")
                        .scenario(reporter1)
                        .build()
        );
    }
}