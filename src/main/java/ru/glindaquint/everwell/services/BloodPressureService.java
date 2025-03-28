package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.models.BloodPressure;
import ru.glindaquint.everwell.repo.BloodPressureRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodPressureService {
    private final BloodPressureRepository bloodPressureRepository;
    private final UserService userService;

    public List<BloodPressure> getOwnedByUser() {
        List<BloodPressure> bloodPressures = bloodPressureRepository.getBloodPressuresByUser(
                userService.getCurrentUser()
        );
        bloodPressures.sort(Comparator.comparing(BloodPressure::getMeasurementDateTime).reversed());
        return bloodPressures;
    }

    public boolean saveBloodPressure(BloodPressure bloodPressure) {
        BloodPressure savedInstance = bloodPressureRepository.save(bloodPressure);
        return savedInstance.getBloodPressureId() != null;
    }
}
