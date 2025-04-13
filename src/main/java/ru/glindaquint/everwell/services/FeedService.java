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

import java.util.HashSet;
import java.util.Set;

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
        request.getProductsIds()
               .forEach(productId -> {
                   Product product = productRepository.findById(productId)
                                                      .orElseThrow(() -> new EntityNotFoundException(
                                                              "Product not found: " + productId));

                   FeedProduct feedProduct = FeedProduct.builder()
                                                        .feed(savedFeed)
                                                        .product(product)
                                                        .quantity(request.getQuantity())
                                                        .portionSize(request.getPortionSize())
                                                        .build();

                   savedFeed.addFeedProduct(feedProduct);
               });
    }

    @Transactional(readOnly = true)
    public Set<Product> getFeedProducts(@Valid Long feedId) {
        var feed = feedRepository.findById(feedId);
        var products = new HashSet<Product>();
        feed.ifPresent(value -> value.getFeedProducts()
                                     .forEach(product -> products.add(product.getProduct())));
        return products;
    }
}
