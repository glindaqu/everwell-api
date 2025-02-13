package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.dto.tasks.GetTasksByUserResponse;
import ru.glindaquint.everwell.repo.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public GetTasksByUserResponse getTasksByUserId(Long userId) {
        return new GetTasksByUserResponse(taskRepository.findAllByOwnerId(userId));
    }
}
