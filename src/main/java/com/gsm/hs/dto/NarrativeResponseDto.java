package com.gsm.hs.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class NarrativeResponseDto {

    private String role;
    private String situation;
    private String choice;
}