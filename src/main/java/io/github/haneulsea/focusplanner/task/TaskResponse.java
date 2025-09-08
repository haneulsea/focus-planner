package io.github.haneulsea.focusplanner.task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TaskResponse {

    private int id;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;

}
