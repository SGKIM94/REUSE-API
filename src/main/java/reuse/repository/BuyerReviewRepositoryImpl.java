package reuse.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import reuse.domain.QBoard;
import reuse.dto.review.buyer.FindBuyerReviewRequestView;
import reuse.dto.review.buyer.ListBuyerReviewResponseView;

import java.util.List;

import static reuse.domain.QBuyerReview.buyerReview;

@RequiredArgsConstructor
public class BuyerReviewRepositoryImpl implements BuyerReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ListBuyerReviewResponseView findBySeller(Long sellerId) {
        QBoard board = QBoard.board;

        List<FindBuyerReviewRequestView> findAll = jpaQueryFactory
                .select(
                        Projections.constructor(
                                FindBuyerReviewRequestView.class,
                                buyerReview.buyer,
                                buyerReview.rating,
                                buyerReview.title,
                                buyerReview.content,
                                board
                        )
                )
                .from(board)
                .innerJoin(board.buyerReview, buyerReview)
                .where(
                        board.seller.id.eq(sellerId)
                ).fetch();

        return ListBuyerReviewResponseView.toDto(findAll);
    }
}
