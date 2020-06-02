package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateCategoryRequestView category) {
        return ResponseEntity.ok().body(categoryService.create(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable long id) {
        return ResponseEntity.ok().body(categoryService.retrieve(id));
    }
}
