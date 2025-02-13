package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glindaquint.everwell.dto.tasks.GetTasksByUserResponse;
import ru.glindaquint.everwell.models.Task;
import ru.glindaquint.everwell.services.TaskService;
import ru.glindaquint.everwell.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Задачи")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/get-owned-by-user")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public ResponseEntity<?> getOwnedByUser() {
        return ResponseEntity.ok(new GetTasksByUserResponse(taskService.getTasksByUserId(
                userService
                        .getCurrentUser()
                        .getUserId()
                ))
        );
    }

    @GetMapping("/get-task")
    public ResponseEntity<?> getTaskById(@RequestParam @Valid Long taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Task not found");
        }
        return ResponseEntity.ok(task);
    }
}