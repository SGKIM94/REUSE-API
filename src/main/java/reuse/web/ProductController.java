package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.service.ProductService;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateProductRequestView product) {
        CreateProductResponseView savedProduct = productService.create(product);
        return ResponseEntity.created(URI.create("/products/" + savedProduct.getId())).build();
    }

    @GetMapping
    public ResponseEntity list(@RequestBody Product product) {
        return ResponseEntity.ok().build();
    }
}
