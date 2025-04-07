package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;

@Entity
public class TestValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    private int sequence;

    @ManyToOne
    @JoinColumn(name = "test_information_id")
    private TestInformation testInformation;

}
