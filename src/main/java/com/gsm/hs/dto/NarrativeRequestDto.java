package com.gsm.hs.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NarrativeRequestDto {

    private Integer roleCode;
    private Long scenarioId;
    private Long choiceId;
}