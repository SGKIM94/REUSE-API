package reuse.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import reuse.domain.Category;
import reuse.domain.QCategory;
import reuse.domain.QImage;
import reuse.domain.QProduct;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;

import java.util.List;

import static reuse.domain.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public ListBoardWithProductResponseView findAllByCategory(Category requestCategory) {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;
        QImage image = QImage.image;

        List<FindWithProductResponseView> findAll = jpaQueryFactory
                .select(
                        Projections.constructor(
                                FindWithProductResponseView.class,
                                board.id,
                                board.content,
                                board.title,
                                board.createAt,
                                board.updateAt,
                                board.seller,
                                board.sellerAddress,
                                product.isSold,
                                product.isUsed,
                                product.price,
                                product.name,
                                product.quality,
                                image.url
                        )
                )
                .from(board)
                .innerJoin(board.product, product)
                .innerJoin(product.category, category)
                .leftJoin(product.productImages.images, image)
                .where(
                        category.teleco.eq(requestCategory.getTeleco())
                                .or(category.manufacturer.eq(requestCategory.getManufacturer()))
                                .or(category.model.eq(requestCategory.getModel()))
                ).fetch();

        return ListBoardWithProductResponseView.toDto(findAll);
    }
}
