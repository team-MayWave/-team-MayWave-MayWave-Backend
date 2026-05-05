package hs.gsm.maywave.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackResponse {
    private Long historyId;
    private String aiMessage;
    private String historicalFact;
}