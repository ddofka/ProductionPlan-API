package org.codeacademy.productionplanapi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TestInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "testInformation", cascade = CascadeType.ALL)
    private List<TestValue> values = new ArrayList<>();

}
