package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;
import org.codeacademy.productionplanapi.enums.PostStatus;
import org.codeacademy.productionplanapi.enums.Priority;
import org.codeacademy.productionplanapi.enums.ProductionStage;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User userId;
    private PostStatus status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Director directorId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Editor editorId;
    private ProductionStage stage;
    private String compilationName;
    private Priority priority;
    private LocalDate filmingStart;
    private LocalDate editStart;
    private LocalDateTime postDateTimeOne;
    private LocalDateTime postDateTimeTwo;
    private LocalDateTime postDateTimeThree;
    private String referenceLink;
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    private TestInformation testId;

}
