package io.github.haneulsea.focusplanner.planner;

import io.github.haneulsea.focusplanner.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "planner", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Planner() {
    }

}
