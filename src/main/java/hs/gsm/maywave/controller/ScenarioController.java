package hs.gsm.maywave.controller;

import hs.gsm.maywave.dto.GameResponse;
import hs.gsm.maywave.dto.PlayRequest;
import hs.gsm.maywave.service.ScenarioGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioGameService scenarioGameService;

    @PostMapping("/play")
    public GameResponse play(@RequestBody PlayRequest request) {
        return scenarioGameService.play(
                request.roleId(),
                request.scenarioId(),
                request.choice()
        );
    }
}