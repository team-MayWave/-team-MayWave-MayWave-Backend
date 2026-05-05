package hs.gsm.maywave.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChoiceHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;
    private String sceneId;
    private String choiceId;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}