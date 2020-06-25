package reuse.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import reuse.domain.BuyerReview;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuyerReviewRepositoryImpl implements BuyerReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    // TODO : 테스트 코드 우선 작성
    @Override
    public List<BuyerReview> findBySeller(Long sellerId) {
        return new ArrayList<>();
    }
}
