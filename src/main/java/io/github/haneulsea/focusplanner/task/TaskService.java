package io.github.haneulsea.focusplanner.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toEntity(taskRequest);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream() // Learn more about streams
                .map(taskMapper::toResponse) // Learn more about :: syntax
                .toList(); // Learn more about this method
    }

    public TaskResponse getTaskById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTaskById(Integer id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskMapper.updateEntity(taskRequest, task);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.deleteById(id);
    }

}
