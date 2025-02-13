package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.models.Task;
import ru.glindaquint.everwell.repo.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findAllByOwnerId(userId);
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findByTaskId(taskId);
    }
}
