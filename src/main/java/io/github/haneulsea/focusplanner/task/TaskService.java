package io.github.haneulsea.focusplanner.task;

import io.github.haneulsea.focusplanner.planner.Planner;
import io.github.haneulsea.focusplanner.planner.PlannerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final PlannerRepository plannerRepository;

    public TaskService(TaskRepository taskRepository, PlannerRepository plannerRepository) {
        this.taskRepository = taskRepository;
        this.plannerRepository = plannerRepository;
    }

    @Transactional
    public Task createTask(Task task, Integer plannerId) {
        Planner planner = plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));

        task.setPlanner(planner);

        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(Integer plannerId) {
        Planner planner = plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));

        return new ArrayList<>(planner.getTasks());
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Integer taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found while getting by id " + taskId));
    }

    @Transactional
    public Task updateTaskById(Integer taskId, Map<String, Object> updates) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found while updating by id " + taskId));

        for (String key : updates.keySet()) {
            switch (key) {
                case "title" -> task.setTitle((String) updates.get(key));
                case "startTime" -> task.setStartTime(LocalTime.parse((String) updates.get(key)));
                case "endTime" -> task.setEndTime(LocalTime.parse((String) updates.get(key)));
                default -> throw new IllegalArgumentException("Unknown field: " + key);
            }
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTaskById(Integer taskId) {
        taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found while deleting by id " + taskId));

        taskRepository.deleteById(taskId);
    }

}
