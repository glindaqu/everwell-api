package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.models.BadHabit;
import ru.glindaquint.everwell.repo.BadHabitRepository;

@Service
@RequiredArgsConstructor
public class BadHabitService {
    private final BadHabitRepository badHabitRepository;

    public void save(BadHabit badHabit) {
        badHabitRepository.save(badHabit);
    }

    public void delete(BadHabit badHabit) {
        badHabitRepository.delete(badHabit);
    }
}
