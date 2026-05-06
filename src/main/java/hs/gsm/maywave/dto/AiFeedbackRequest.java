package hs.gsm.maywave.dto;

public record AiFeedbackRequest(
        String role,
        String situation,
        String choiceText
) {
}