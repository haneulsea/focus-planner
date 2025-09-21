package io.github.haneulsea.focusplanner.task;

import io.github.haneulsea.focusplanner.planner.Planner;
import io.github.haneulsea.focusplanner.planner.PlannerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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

    public Task createTask(Task task, Integer plannerId) {
        Planner planner = plannerRepository
                .findById(plannerId)
                .orElseThrow(() -> new RuntimeException("Planner not found while getting by id " + plannerId));

        task.setPlanner(planner);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while getting by id " + id));
    }

    public Task updateTaskById(Integer id, Map<String, Object> updates) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while updating by id " + id));

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

    public void deleteTaskById(Integer id) {
        taskRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while deleting by id " + id));

        taskRepository.deleteById(id);
    }

}
