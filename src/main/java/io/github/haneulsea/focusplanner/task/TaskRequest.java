package io.github.haneulsea.focusplanner.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

}
