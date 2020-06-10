package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.repository.FavoriteBoardRepository;

@Service
public class FavoriteBoardService {
    private FavoriteBoardRepository favoriteBoardRepository;
    private BoardService boardService;

    public FavoriteBoardService(FavoriteBoardRepository favoriteBoardRepository) {
        this.favoriteBoardRepository = favoriteBoardRepository;
    }

    public FavoriteBoard create(CreateFavoriteBoardRequestView favorite, User loginUser) {
        Board board = boardService.findById(favorite.getBoardId());
        return favoriteBoardRepository.save(FavoriteBoard.toEntity(board, loginUser));
    }
}
