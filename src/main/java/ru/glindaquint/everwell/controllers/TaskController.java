package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.tasks.DeleteTaskRequest;
import ru.glindaquint.everwell.dto.tasks.GetTasksByUserResponse;
import ru.glindaquint.everwell.dto.tasks.InsertTaskRequest;
import ru.glindaquint.everwell.dto.tasks.InsertTaskResponse;
import ru.glindaquint.everwell.models.Task;
import ru.glindaquint.everwell.services.TaskService;
import ru.glindaquint.everwell.services.UserService;

import java.util.Date;
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

    @GetMapping("/get-single")
    public ResponseEntity<?> getTaskById(@RequestParam @Valid Long taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Task not found");
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertTask(@RequestBody @Valid InsertTaskRequest request) {
        Task task = Task.builder()
                .ownerId(userService.getCurrentUser().getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .repeat(request.getRepeat())
                .isNotificationEnabled(request.getIsNotificationEnabled())
                .deadlineDate(request.getDeadlineDate())
                .build();
        try {
            Task createdTask = taskService.save(task);
            return ResponseEntity.ok(new InsertTaskResponse(createdTask));
        } catch (Exception ignored) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when creating task");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestBody @Valid DeleteTaskRequest request) {
        Task task = Task.builder()
                .ownerId(userService.getCurrentUser().getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .repeat(request.getRepeat())
                .isNotificationEnabled(request.getIsNotificationEnabled())
                .deadlineDate(request.getDeadlineDate())
                .build();
        try {
            taskService.delete(task);
            return ResponseEntity.ok("Task deleted");
        } catch (Exception ignored) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when deleting task");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody @Valid DeleteTaskRequest request) {
        Task task = Task.builder()
                .taskId(request.getTaskId())
                .ownerId(userService.getCurrentUser().getUserId())
                .title(request.getTitle())
                .description(request.getDescription())
                .repeat(request.getRepeat())
                .isNotificationEnabled(request.getIsNotificationEnabled())
                .deadlineDate(request.getDeadlineDate())
                .creationDate(request.getCreationDate())
                .lastChangeDate(new Date())
                .build();
        try {
            taskService.save(task);
            return ResponseEntity.ok("Task saved");
        } catch (Exception ignored) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when saving task");
        }
    }
}