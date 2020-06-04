package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.dto.product.CreateProductRequestView;
import reuse.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity create(@ModelAttribute CreateProductRequestView product) {
        return ResponseEntity.ok().body(productService.create(product));
    }

    @GetMapping
    public ResponseEntity list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/category")
    public ResponseEntity findAllByCategory() {
        return ResponseEntity.ok().build();
    }
}
