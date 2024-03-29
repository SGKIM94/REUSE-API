package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.Board;
import reuse.domain.User;
import reuse.dto.board.*;
import reuse.security.LoginUser;
import reuse.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

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
        ListBoardResponseView boards = boardService.list();
        return ResponseEntity.ok().body(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieve(@PathVariable Long id) {
        FindBoardResponseView board = boardService.retrieve(id);
        return ResponseEntity.ok().body(board);
    }

    @PostMapping("/category")
    public ResponseEntity listByCategory(@RequestBody ListBoardByCategoryRequestView category) {
        return ResponseEntity.ok().body(boardService.listByCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity modify(@RequestBody ModifyBoardRequestView modify, @PathVariable Long id) {
        Board modifiedBoard = boardService.modify(modify, id);
        return ResponseEntity.ok().body(modifiedBoard.getId());
    }

    @PostMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation")
    public ResponseEntity reserve(@RequestBody ModifyBoardStatusRequestView boardId, @LoginUser User seller) {
        Board reserve = boardService.reserve(boardId, seller);
        return ResponseEntity.ok().body(reserve.getId());
    }

    @PostMapping("/complete")
    public ResponseEntity complete(@RequestBody ModifyBoardStatusRequestView boardId) {
        Board complete = boardService.complete(boardId);
        return ResponseEntity.ok().body(complete.getId());
    }
}
