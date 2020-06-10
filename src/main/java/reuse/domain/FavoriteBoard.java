package reuse.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@NoArgsConstructor
public class FavoriteBoard extends AbstractEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public FavoriteBoard(User user, Board board) {
        this.user = user;
        this.board = board;
    }

    public static FavoriteBoard toEntity(Board board, User loginUser) {
        return FavoriteBoard.builder()
                .user(loginUser)
                .board(board)
                .build();
    }

    public User getUser() {
        return user;
    }

    public Board getBoard() {
        return board;
    }
}
