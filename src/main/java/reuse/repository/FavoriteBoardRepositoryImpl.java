package reuse.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import reuse.domain.QImage;
import reuse.domain.QProduct;
import reuse.dto.board.FindWithProductResponseView;
import reuse.dto.board.ListBoardWithProductResponseView;

import java.util.List;

import static reuse.domain.QBoard.board;
import static reuse.domain.QFavoriteBoard.favoriteBoard;

@RequiredArgsConstructor
public class FavoriteBoardRepositoryImpl implements FavoriteBoardRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ListBoardWithProductResponseView findAllByUserId(Long id) {
        QProduct product = QProduct.product;
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
                .from(favoriteBoard)
                .innerJoin(favoriteBoard.board, board)
                .innerJoin(board.product, product)
                .leftJoin(board.product.productImages.images, image)
                .where(
                        favoriteBoard.user.id.eq(id)
                ).fetch();

        return ListBoardWithProductResponseView.toDto(findAll);
    }
}
