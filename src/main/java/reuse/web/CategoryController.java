package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.dto.category.CreateCategoryRequestView;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @PostMapping
    public ResponseEntity create(@RequestBody CreateCategoryRequestView product) {
        return ResponseEntity.ok().build();
    }
}
