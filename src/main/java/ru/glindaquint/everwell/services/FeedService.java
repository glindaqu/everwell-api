package ru.glindaquint.everwell.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.glindaquint.everwell.dto.requests.feed.InsertFeedRequest;
import ru.glindaquint.everwell.models.Feed;
import ru.glindaquint.everwell.models.FeedProduct;
import ru.glindaquint.everwell.models.Product;
import ru.glindaquint.everwell.models.User;
import ru.glindaquint.everwell.repo.FeedRepository;
import ru.glindaquint.everwell.repo.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {
    @Autowired
    private final FeedRepository feedRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserService userService;

    @Transactional
    public void insertFeed(InsertFeedRequest request) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User not authenticated");
        }

        Feed feed = Feed.builder()
                        .feedType(request.getFeedType())
                        .user(currentUser)
                        .build();

        // Сначала сохраняем Feed, чтобы получить ID
        Feed savedFeed = feedRepository.save(feed);

        // Обрабатываем продукты
        request.getProducts()
               .forEach(feedProduct -> {
                   Product product = productRepository.findById(feedProduct.getProduct()
                                                                           .getProductId())
                                                      .orElseThrow(() -> new EntityNotFoundException("Product not found"));

                   FeedProduct createdFeedProduct = FeedProduct.builder()
                                                               .feed(savedFeed)
                                                               .product(product)
                                                               .quantity(feedProduct.getQuantity())
                                                               .weightInGrams(feedProduct.getWeightInGrams())
                                                               .protein(feedProduct.getProtein())
                                                               .fat(feedProduct.getFat())
                                                               .calories(feedProduct.getCalories())
                                                               .carbohydrates(feedProduct.getCarbohydrates())
                                                               .build();

                   savedFeed.addFeedProduct(createdFeedProduct);
               });
    }

    @Transactional(readOnly = true)
    public Set<FeedProduct> getFeedProducts(@Valid Long feedId) {
        var feed = feedRepository.findById(feedId);
        if (feed.isPresent()) {
            return feed.get()
                       .getFeedProducts();
        }
        return Collections.emptySet();
    }

    @Transactional
    public List<Product> getRecentProductsByUser(@Valid User user) {
        List<Product> products = new ArrayList<>();
        feedRepository.findAll()
                      .stream()
                      .filter(feed -> feed.getUser()
                                          .equals(user))
                      .forEach(feed -> {
                          feed.getFeedProducts()
                              .forEach(product -> products.add(product.getProduct()));
                      });
        return products.subList(0, Math.min(products.size(), 10));
    }

    public Set<Feed> getUsersFeeds(User currentUser) {
        return feedRepository.findAll()
                             .stream()
                             .filter(feed -> Objects.equals(
                                     feed.getUser()
                                         .getUserId(), currentUser.getUserId()
                             ))
                             .collect(Collectors.toSet());
    }
}
