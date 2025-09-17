package io.github.haneulsea.focusplanner.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while getting by id " + id));
    }

    public Task updateTaskById(Integer id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while updating by id " + id));

        task.setTitle(updatedTask.getTitle());
        task.setStartTime(updatedTask.getStartTime());
        task.setEndTime(updatedTask.getEndTime());

        return taskRepository.save(task);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found while deleting by id " + id));

        taskRepository.deleteById(id);
    }

}
