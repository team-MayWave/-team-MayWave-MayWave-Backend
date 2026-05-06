package hs.gsm.maywave.dto;

public record PlayRequest(
        Integer roleId,
        Long scenarioId,
        Integer choice
) {
}