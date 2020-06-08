package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.User;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ListBoardByCategoryRequestView;
import reuse.dto.board.ModifyBoardRequestView;
import reuse.security.LoginUser;
import reuse.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CreateBoardRequestView board, @LoginUser User seller) {
        CreateBoardResponseView savedBoard = boardService.create(board, seller);
        return ResponseEntity.ok().body(savedBoard);
    }

    @GetMapping("")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(boardService.list());
    }

    @PostMapping("/category")
    public ResponseEntity listByCategory(@RequestBody ListBoardByCategoryRequestView category) {
        return ResponseEntity.ok().body(boardService.listByCategory(category));
    }

    @PutMapping
    public ResponseEntity modify(@RequestBody ModifyBoardRequestView modify) {
        boardService.modify(modify);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }
}
