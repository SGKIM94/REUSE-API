package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.dto.board.ListBoardWithProductResponseView;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.repository.FavoriteBoardRepository;

@Service
public class FavoriteBoardService {
    private FavoriteBoardRepository favoriteBoardRepository;
    private BoardService boardService;

    public FavoriteBoardService(FavoriteBoardRepository favoriteBoardRepository, BoardService boardService) {
        this.favoriteBoardRepository = favoriteBoardRepository;
        this.boardService = boardService;
    }

    public FavoriteBoard create(CreateFavoriteBoardRequestView favorite, User loginUser) {
        Board board = boardService.findById(favorite.getBoardId());
        return favoriteBoardRepository.save(FavoriteBoard.toEntity(board, loginUser));
    }

    public ListBoardWithProductResponseView listByUser(User loginUser) {
        return favoriteBoardRepository.findAllByUserId(loginUser.getId());
    }
}
