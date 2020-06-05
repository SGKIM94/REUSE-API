package reuse.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import reuse.domain.Board;
import reuse.domain.Category;
import reuse.dto.board.ListBoardResponseView;

import static reuse.domain.QBoard.board;

@Repository
public class BoardRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Board.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public ListBoardResponseView findAllByCategory(Category category) {
        return jpaQueryFactory
                .selectFrom(product)
                .innerJoin()
                .where(board.)
    }
}
