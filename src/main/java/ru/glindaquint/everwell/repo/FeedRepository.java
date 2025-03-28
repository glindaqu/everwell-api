package ru.glindaquint.everwell.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glindaquint.everwell.models.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
}
