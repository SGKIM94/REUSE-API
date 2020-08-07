package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.domain.SellerReview;
import reuse.domain.User;
import reuse.dto.review.seller.CreateSellerReviewRequestView;
import reuse.security.LoginUser;
import reuse.service.SellerReviewService;

@RestController
@RequestMapping("/review/seller")
public class SellerReviewController {
    private SellerReviewService sellerReviewService;

    public SellerReviewController(SellerReviewService sellerReviewService) {
        this.sellerReviewService = sellerReviewService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateSellerReviewRequestView review, @LoginUser User seller) {
        SellerReview sellerReview = sellerReviewService.create(review, seller);
        return ResponseEntity.ok().body(sellerReview);
    }
}
