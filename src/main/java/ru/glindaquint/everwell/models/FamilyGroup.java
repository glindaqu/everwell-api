package ru.glindaquint.everwell.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "family_groups")
public class FamilyGroup {
  @Id
  @Column(name = "family_group_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "family_group_id_seq")
  @SequenceGenerator(
      name = "family_group_id_seq",
      sequenceName = "family_group_id_seq",
      allocationSize = 1)
  private Long familyGroupId;

  @JsonIgnore
  @OneToMany(mappedBy = "familyGroup", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<User> members = new HashSet<>();
}
