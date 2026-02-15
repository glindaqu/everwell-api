package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.models.FamilyGroup;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.repo.FamilyGroupRepository;

@RequiredArgsConstructor
@Service
public class FamilyGroupService {
  @Autowired private final FamilyGroupRepository familyGroupRepository;

  public boolean bindUser(User user, FamilyGroup familyGroup) {
    if (user.getFamilyGroup() == null) return false;
    familyGroup.getMembers().add(user);
    familyGroupRepository.save(familyGroup);
    return true;
  }

  public boolean bindUser(User user, Long familyGroupId) {
    if (user.getFamilyGroup() == null) return false;
    FamilyGroup familyGroup = familyGroupRepository.getById(familyGroupId);
    familyGroup.getMembers().add(user);
    familyGroupRepository.save(familyGroup);
    return true;
  }

  public FamilyGroup createFamilyGroup(User user) {
    FamilyGroup familyGroup = new FamilyGroup();
    familyGroup.getMembers().add(user);
    familyGroupRepository.save(familyGroup);
    user.setFamilyGroup(familyGroup);
    return familyGroup;
  }

  public boolean unbindUser(User user) {
    if (user.getFamilyGroup() == null) return false;
    FamilyGroup familyGroup = user.getFamilyGroup();
    familyGroup.getMembers().remove(user);
    familyGroupRepository.save(familyGroup);
    return true;
  }

  public String generateInviteLink(User user) {
    if (user.getFamilyGroup() == null) {
      createFamilyGroup(user);
    }

    return "family-group/join?familyGroupId=" + user.getFamilyGroup().getFamilyGroupId().toString();
  }
}
