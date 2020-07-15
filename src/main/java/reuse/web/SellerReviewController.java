package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.User;
import reuse.dto.review.seller.CreateSellerReviewRequestView;
import reuse.security.LoginUser;

@RestController
@RequestMapping("/review/buyer")
public class SellerReviewController {

    @PostMapping
    public ResponseEntity create(@RequestBody CreateSellerReviewRequestView review, @LoginUser User buyer) {
        return ResponseEntity.ok().build();
    }
}
