package hs.gsm.maywave.dto;

import lombok.Getter;           // 이게 있어야 함
import lombok.NoArgsConstructor;  // 이게 있어야 함
import lombok.AllArgsConstructor; // 이게 있어야 함

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceRequest {
    private Long sessionId;
    private String sceneId;
    private String choiceId;
}