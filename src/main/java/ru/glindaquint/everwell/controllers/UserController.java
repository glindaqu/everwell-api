package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.glindaquint.everwell.dto.requests.users.UpdateUserRequest;
import ru.glindaquint.everwell.dto.responses.users.GetUserResponse;
import ru.glindaquint.everwell.models.BadHabit;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {
  @Autowired private final UserService userService;

  @Autowired private final Path fileStorageLocation;

  @GetMapping("/get-user")
  @Operation(summary = "Доступен только авторизованным пользователям")
  public ResponseEntity<?> getUser() {
    User user = userService.getCurrentUser();
    return ResponseEntity.ok(
        GetUserResponse.builder()
            .email(user.getEmail())
            .username(user.getUsername())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .patronymic(user.getPatronymic())
            .sex(String.valueOf(user.getSex()))
            .weight(user.getWeight())
            .height(user.getHeight())
            .diseases(user.getDiseases())
            .birthDate(user.getBirthDate())
            .image(user.getImage())
            .badHabits(
                user.getBadHabits().stream().map(BadHabit::getTitle).collect(Collectors.toSet()))
            .build());
  }

  @PostMapping("/update-profile")
  public ResponseEntity<?> updateUser(@RequestBody @Valid UpdateUserRequest request) {
    try {
      userService.updateUser(request);
      return ResponseEntity.ok("saved");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
  }

  @PostMapping(value = "/update-profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> uploadUserImage(@RequestParam("image") MultipartFile image) {
    User user = userService.getCurrentUser();

    try {
      String fileName =
          user.getUserId() + "_" + System.currentTimeMillis() + "_" + image.getOriginalFilename();

      Path targetLocation = fileStorageLocation.resolve(fileName);
      Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      String fileUrl = "images/" + fileName;

      userService.updateUserImage(fileUrl);

      return ResponseEntity.ok(fileUrl);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Uploading error: " + e.getMessage());
    }
  }
}
