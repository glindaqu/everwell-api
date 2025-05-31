package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.glindaquint.everwell.dto.requests.products.InsertProductRequest;
import ru.glindaquint.everwell.dto.responses.DataResponse;
import ru.glindaquint.everwell.models.Product;
import ru.glindaquint.everwell.services.FeedService;
import ru.glindaquint.everwell.services.ProductService;
import ru.glindaquint.everwell.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Продукты")
public class ProductController {
    private final ProductService productService;
    private final FeedService feedService;
    private final UserService userService;

    @GetMapping("get-all")
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts() {
        DataResponse<List<Product>> response = DataResponse.<List<Product>>builder()
                                                           .data(productService.getAllProducts())
                                                           .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-owned-by-user")
    public ResponseEntity<DataResponse<List<Product>>> getOwnedByUser() {
        DataResponse<List<Product>> response = DataResponse.<List<Product>>builder()
                                                           .data(productService.getUsersProducts())
                                                           .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-recent")
    public ResponseEntity<DataResponse<List<Product>>> getRecentProducts() {
        DataResponse<List<Product>> response = DataResponse.<List<Product>>builder()
                                                           .data(feedService.getRecentProductsByUser(userService.getCurrentUser()))
                                                           .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<DataResponse<Product>> addProduct(@RequestBody InsertProductRequest request) {
        return ResponseEntity.ok(DataResponse.<Product>builder()
                                             .data(productService.insertProduct(request))
                                             .build());
    }
}
