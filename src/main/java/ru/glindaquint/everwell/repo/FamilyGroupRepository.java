package ru.glindaquint.everwell.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glindaquint.everwell.models.FamilyGroup;

@Repository
public interface FamilyGroupRepository extends JpaRepository<FamilyGroup, Long> {}
