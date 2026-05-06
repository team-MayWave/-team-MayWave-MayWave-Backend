package com.gsm.hs.controller;

import com.gsm.hs.dto.*;
import com.gsm.hs.service.NarrativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/narrative")
@RequiredArgsConstructor
public class NarrtiveController {

    private final NarrativeService narrativeService;

    @PostMapping
    public ResponseEntity<NarrativeResponseDto> play(
            @RequestBody NarrativeRequestDto request
    ) {
        return ResponseEntity.ok(
                narrativeService.process(request)
        );
    }
}