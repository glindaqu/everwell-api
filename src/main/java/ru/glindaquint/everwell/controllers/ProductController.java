package ru.glindaquint.everwell.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glindaquint.everwell.services.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Продукты")
public class ProductController {
    private final ProductService productService;

    @GetMapping("get-all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
