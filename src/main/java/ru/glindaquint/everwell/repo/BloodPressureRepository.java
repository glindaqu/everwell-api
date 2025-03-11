package ru.glindaquint.everwell.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glindaquint.everwell.models.BloodPressure;

import java.util.List;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {
    List<BloodPressure> getBloodPressuresByOwnerId(Long ownerId);
}
