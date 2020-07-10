package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.Board;
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

    @PostMapping
    public ResponseEntity create(@RequestBody CreateBoardRequestView board, @LoginUser User seller) {
        CreateBoardResponseView savedBoard = boardService.create(board, seller);
        return ResponseEntity.ok().body(savedBoard);
    }

    @GetMapping("")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(boardService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.findById(id));
    }

    @PostMapping("/category")
    public ResponseEntity listByCategory(@RequestBody ListBoardByCategoryRequestView category) {
        return ResponseEntity.ok().body(boardService.listByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity modify(@RequestBody ModifyBoardRequestView modify, @PathVariable Long id) {
        Board modifiedBoard = boardService.modify(modify, id);
        return ResponseEntity.ok().body(modifiedBoard);
    }

    @PostMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation")
    public ResponseEntity reserve(@RequestBody Long id, @LoginUser User seller) {
        Board reserve = boardService.reserve(id, seller);
        return ResponseEntity.ok().body(reserve);
    }

    @PostMapping("/complete")
    public ResponseEntity complete(@RequestBody Long id, @LoginUser User seller) {
        Board reserve = boardService.complete(id, seller);
        return ResponseEntity.ok().body(reserve);
    }
}
