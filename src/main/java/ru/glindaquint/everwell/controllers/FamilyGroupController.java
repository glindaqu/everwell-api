package ru.glindaquint.everwell.controllers;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.glindaquint.everwell.dto.responses.DataResponse;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.services.FamilyGroupService;
import ru.glindaquint.everwell.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family-group")
public class FamilyGroupController {
  @Autowired private final UserService userService;
  @Autowired private final FamilyGroupService familyGroupService;

  @GetMapping("/join")
  public void joinFamilyGroup(@RequestParam Long familyGroupId) {
    User user = userService.getCurrentUser();
    familyGroupService.bindUser(user, familyGroupId);
  }

  @GetMapping("/invite")
  public ResponseEntity<DataResponse<String>> getInviteLink() {
    return ResponseEntity.ok(
        DataResponse.<String>builder()
            .data(familyGroupService.generateInviteLink(userService.getCurrentUser()))
            .build());
  }

  @GetMapping("/members")
  public ResponseEntity<?> getMembers() {
    User user = userService.getCurrentUser();
    if (user.getFamilyGroup() != null) {
      return ResponseEntity.ok(
          DataResponse.<Set<User>>builder().data(user.getFamilyGroup().getMembers()).build());
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No group found");
  }

  @GetMapping("/leave")
  public void leaveGroup() {
    User user = userService.getCurrentUser();
    if (user.getFamilyGroup() != null) familyGroupService.unbindUser(user);
  }
}
