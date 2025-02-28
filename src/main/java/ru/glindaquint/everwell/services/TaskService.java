package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glindaquint.everwell.models.Task;
import ru.glindaquint.everwell.repo.TaskRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для манипуляции задачами пользователя
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Получение всех задач, принадлежащих пользователю
     *
     * @param userId Идентефикатор пользователя
     * @return Список задач. Может быть пустым
     * @see Task
     */
    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findAllByOwnerId(userId);
    }

    /**
     * Получение задачи по идентефикатору
     *
     * @param taskId Идентефикатор задачи
     * @return Задачу, если запись с таким Id существует, иначе null
     */
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    /**
     * СОхранение изменений или создание новой задачи
     *
     * @param task Доменная модель задачи
     * @return Созданная/сохранненная задача
     */
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Удаление задачи
     *
     * @param task Доменная мрдель задачи
     */
    @Transactional
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
