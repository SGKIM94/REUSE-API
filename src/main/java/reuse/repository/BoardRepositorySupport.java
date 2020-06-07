package reuse.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import reuse.domain.Board;
import reuse.domain.Category;
import reuse.domain.QCategory;
import reuse.domain.QProduct;
import reuse.dto.board.FindAllByCategoryResponseView;
import reuse.dto.board.FindAllByCategoryResponseViews;

import java.util.List;

import static reuse.domain.QBoard.board;

@Repository
public class BoardRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Board.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public FindAllByCategoryResponseViews findAllByCategory(Category requestCategory) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        List<FindAllByCategoryResponseView> findAll = jpaQueryFactory
                .select(
                        Projections.constructor(
                                FindAllByCategoryResponseView.class,
                                board.id,
                                board.content,
                                board.title,
                                board.createAt,
                                board.updateAt,
                                board.seller,
                                board.sellerAddress,
                                product.thumbnailImage,
                                product.isSold,
                                product.isUsed,
                                product.price,
                                product.name,
                                product.quality
                        )
                )
                .innerJoin(board.product, product).on(board.product.id.eq(product.id))
                .innerJoin(product.category, category).on(product.category.id.eq(category.id))
                .where(
                        category.teleco.eq(requestCategory.getTeleco())
                                .or(category.manufacturer.eq(requestCategory.getManufacturer()))
                                .or(category.model.eq(requestCategory.getModel()))
                ).fetch();

        return FindAllByCategoryResponseViews.toDto(findAll);
    }
}
