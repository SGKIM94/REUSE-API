package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Board;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.dto.board.ListBoardWithProductResponseView;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.repository.FavoriteBoardRepository;

@Service
public class FavoriteBoardService {
    private final FavoriteBoardRepository favoriteBoardRepository;
    private final BoardService boardService;

    public FavoriteBoardService(FavoriteBoardRepository favoriteBoardRepository, BoardService boardService) {
        this.favoriteBoardRepository = favoriteBoardRepository;
        this.boardService = boardService;
    }

    @Transactional
    public FavoriteBoard create(CreateFavoriteBoardRequestView favorite, User loginUser) {
        Board board = boardService.findById(favorite.getBoardId());
        return favoriteBoardRepository.save(FavoriteBoard.toEntity(board, loginUser));
    }

    @Transactional(readOnly = true)
    public ListBoardWithProductResponseView listByUser(User loginUser) {
        return favoriteBoardRepository.findAllByUserId(loginUser.getId());
    }
}
