package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.User;
import reuse.security.LoginUser;

@RestController
@RequestMapping("/products")
public class ProductController {
    @PostMapping
    public ResponseEntity list(@LoginUser User user) {
        return ResponseEntity.ok().build();
    }
}
