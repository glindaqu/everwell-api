package ru.glindaquint.everwell.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.bloodPressure.GetUsersBloodPressuresResponse;
import ru.glindaquint.everwell.dto.bloodPressure.InsertBloodPressureRequest;
import ru.glindaquint.everwell.models.BloodPressure;
import ru.glindaquint.everwell.services.BloodPressureService;
import ru.glindaquint.everwell.services.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blood-pressure")
public class BloodPressureController {
    private final BloodPressureService bloodPressureService;
    private final UserService userService;

    @GetMapping("/get-owned-by-user")
    public ResponseEntity<?> getOwnedByUser() {
        try {
            return ResponseEntity.ok(
                    new GetUsersBloodPressuresResponse(bloodPressureService.getOwnedByUser())
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBloodPressure(@RequestBody @Valid InsertBloodPressureRequest request) {
        try {
            bloodPressureService.saveBloodPressure(BloodPressure.builder()
                    .diastolicPressure(request.getDiastolicPressure())
                    .systolicPressure(request.getSystolicPressure())
                    .heartRate(request.getHeartRate())
                    .ownerId(userService.getCurrentUser().getUserId())
                    .build()
            );
            return ResponseEntity.ok("Saved");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
