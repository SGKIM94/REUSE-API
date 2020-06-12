package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.FavoriteBoard;
import reuse.domain.User;
import reuse.dto.favorite.CreateFavoriteBoardRequestView;
import reuse.security.LoginUser;
import reuse.service.FavoriteBoardService;

@RestController
@RequestMapping("/favorites/board")
public class FavoriteBoardController {
    private FavoriteBoardService favoriteBoardService;

    public FavoriteBoardController(FavoriteBoardService favoriteBoardService) {
        this.favoriteBoardService = favoriteBoardService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CreateFavoriteBoardRequestView board, @LoginUser User loginUser) {
        FavoriteBoard favoriteBoard = favoriteBoardService.create(board, loginUser);
        return ResponseEntity.ok().body(favoriteBoard.getId());
    }

    @GetMapping("")
    public ResponseEntity listByUser(@LoginUser User loginUser) {
        return ResponseEntity.ok().body(favoriteBoardService.listByUser(loginUser));
    }
}
