package ru.glindaquint.everwell.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glindaquint.everwell.models.BadHabit;

@Repository
public interface BadHabitRepository extends JpaRepository<BadHabit, Long> {
}
