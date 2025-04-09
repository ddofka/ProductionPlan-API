package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class TestInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;
    private String timeName; // 3s, 15s, 30s,
    private Double timeValue;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

}
