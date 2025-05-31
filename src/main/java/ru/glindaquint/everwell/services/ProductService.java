package ru.glindaquint.everwell.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glindaquint.everwell.dto.requests.products.InsertProductRequest;
import ru.glindaquint.everwell.models.Product;
import ru.glindaquint.everwell.repo.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product insertProduct(InsertProductRequest request) {
        return productRepository.save(Product.builder()
                                             .title(request.getTitle())
                                             .calories(request.getCalories())
                                             .weightInGrams(request.getWeightInGrams())
                                             .fat(request.getFat())
                                             .protein(request.getProtein())
                                             .carbohydrates(request.getCarbohydrates())
                                             .owner(userService.getCurrentUser())
                                             .build());
    }

    public List<Product> getUsersProducts() {
        return productRepository.findAll()
                                .stream()
                                .filter(product -> Objects.equals(
                                        product.getOwner()
                                               .getUserId(),
                                        userService.getCurrentUser()
                                                   .getUserId()
                                ))
                                .collect(Collectors.toList());
    }
}
