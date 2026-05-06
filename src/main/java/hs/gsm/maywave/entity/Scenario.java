package hs.gsm.maywave.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scene_id") // DB 컬럼명이 scene_id이므로 반드시 명시!
    private Long id;

    @Column(name = "role_id") // DB의 role_id와 매핑
    private Integer roleId;

    @Column(length = 1000)
    private String situation;

    @Column(length = 500)
    private String choice1;

    @Column(length = 500)
    private String choice2;

    @Column(length = 500)
    private String choice3;

    // 사진에 있는 나머지 컬럼들도 추가 (선택사항)
    private String aiMessage;
    private String historicalFact;
}