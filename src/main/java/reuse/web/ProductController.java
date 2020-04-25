package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    @PostMapping
    public ResponseEntity create(CreateProductRequestView product) {
        return ResponseEntity.created(URI.create("/products/1")).build();
    }

    @GetMapping
    public ResponseEntity list(Product product) {
        return ResponseEntity.ok().build();
    }
}
