package hs.gsm.maywave.ai.dto;

public record AiFeedbackRequest(
        String role,
        String surroundingSituation,
        String userChoice,
        String choiceMeaning
) {
}
