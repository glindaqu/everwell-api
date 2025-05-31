package ru.glindaquint.everwell.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glindaquint.everwell.models.FeedProduct;

@Repository
public interface FeedProductRepository extends JpaRepository<FeedProduct, Long> {

}
