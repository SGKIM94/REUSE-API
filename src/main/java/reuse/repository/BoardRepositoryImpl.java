package reuse.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import reuse.domain.Category;
import reuse.domain.QCategory;
import reuse.domain.QProduct;
import reuse.dto.board.FindByCategoryResponseView;
import reuse.dto.board.ListBoardByCategoryResponseView;

import java.util.List;

import static reuse.domain.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ListBoardByCategoryResponseView findAllByCategory(Category requestCategory) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        List<FindByCategoryResponseView> findAll = jpaQueryFactory
                .select(
                        Projections.constructor(
                                FindByCategoryResponseView.class,
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
                .from(board)
                .innerJoin(board.product, product)
                .innerJoin(product.category, category)
                .where(
                        category.teleco.eq(requestCategory.getTeleco())
                                .or(category.manufacturer.eq(requestCategory.getManufacturer()))
                                .or(category.model.eq(requestCategory.getModel()))
                ).fetch();

        return ListBoardByCategoryResponseView.toDto(findAll);
    }
}
