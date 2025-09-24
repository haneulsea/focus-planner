package io.github.haneulsea.focusplanner.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/planners/{plannerId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@PathVariable Integer plannerId,
                           @RequestBody Task task) {
        return taskService.createTask(task, plannerId);
    }

    @GetMapping
    public List<Task> getAllTasks(@PathVariable Integer plannerId) {
        return taskService.getAllTasks(plannerId);
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Integer plannerId,
                            @PathVariable Integer taskId) {
        return taskService.getTaskById(taskId, plannerId);
    }

    @PatchMapping("/{taskId}")
    public Task updateTaskById(@PathVariable Integer plannerId,
                               @PathVariable Integer taskId,
                               @RequestBody Map<String, Object> updates) {
        return taskService.updateTaskById(taskId, updates, plannerId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable Integer plannerId,
                               @PathVariable Integer taskId) {
        taskService.deleteTaskById(taskId, plannerId);
    }

}
