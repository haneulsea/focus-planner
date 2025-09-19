package io.github.haneulsea.focusplanner.planner;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    public Planner() {
    }

}
