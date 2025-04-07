package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TaskPostTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDateTime postDateTime;
    private Integer sequence;

}
