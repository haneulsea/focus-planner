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
        Task entity = taskMapper.toEntity(taskRequest);
        Task saved = taskRepository.save(entity);
        return taskMapper.toResponse(saved);
    }

    public TaskResponse getTaskById(Integer id) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.toResponse(entity);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream() // Learn more about streams
                .map(taskMapper::toResponse) // Learn more about :: syntax
                .toList(); // Learn more about this method
    }

    public void deleteTaskById(Integer id) {
        this.taskRepository.deleteById(id);
    }

}
