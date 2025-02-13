package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.tasks.GetTasksByUserRequest;
import ru.glindaquint.everwell.dto.tasks.GetTasksByUserResponse;
import ru.glindaquint.everwell.services.TaskService;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Задачи")
public class TaskController {
    private final TaskService service;

    @PostMapping("/get-by-user")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public GetTasksByUserResponse getAll(@RequestBody @Valid GetTasksByUserRequest request) {
        return service.getTasksByUserId(request.getUserId());
    }
}