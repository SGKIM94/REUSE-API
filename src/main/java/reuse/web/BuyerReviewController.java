package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.BuyerReview;
import reuse.domain.User;
import reuse.dto.review.buyer.CreateBuyerReviewRequestView;
import reuse.security.LoginUser;
import reuse.service.BuyerReviewService;

@RestController
@RequestMapping("/review/buyer")
public class BuyerReviewController {
    private BuyerReviewService buyerReviewService;

    public BuyerReviewController(BuyerReviewService buyerReviewService) {
        this.buyerReviewService = buyerReviewService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CreateBuyerReviewRequestView board, @LoginUser User buyer) {
        BuyerReview buyerReview = buyerReviewService.create(board, buyer);
        return ResponseEntity.ok().body(buyerReview);
    }

    @GetMapping("{id}")
    public ResponseEntity findBySeller(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
