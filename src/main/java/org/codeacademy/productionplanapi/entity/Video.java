package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name= "director_id")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Release> releases;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestInformation> tests;

    private LocalDate filmingStart;
    private LocalDate editStart;

    @Enumerated(EnumType.STRING)
    private ProductionStage stage;
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String compilationName;
    private String referenceLink;
    private String comment;

}
