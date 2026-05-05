package hs.gsm.maywave.controller;

import hs.gsm.maywave.dto.ChoiceRequest;
import hs.gsm.maywave.dto.FeedbackResponse;
import hs.gsm.maywave.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping("/session/roles/choice")
    public ResponseEntity<FeedbackResponse> makeChoice(@RequestBody ChoiceRequest request) {
        return ResponseEntity.ok(gameService.processUserChoice(request));
    }
}