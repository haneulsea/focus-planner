package io.github.haneulsea.focusplanner.task;

import io.github.haneulsea.focusplanner.planner.Planner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Time is required")
    @Column(nullable = false)
    private LocalTime startTime;

    @NotNull(message = "Time is required")
    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "planner_id", nullable = false)
    private Planner planner;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}