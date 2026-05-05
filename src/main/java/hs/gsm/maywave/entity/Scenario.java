package hs.gsm.maywave.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Scenario {
    @Id
    private String choiceId;

    private String sceneId;
    private String aiMessage;

    @Column(columnDefinition = "TEXT")
    private String historicalFact;
}