package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ModifyBoardRequestView;
import reuse.service.BoardService;

import java.net.URI;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody CreateBoardRequestView board) {
        CreateBoardResponseView savedBoard = boardService.create(board);
        return ResponseEntity.created(URI.create("/boards/" + savedBoard.getId())).build();
    }

    @GetMapping("")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(boardService.list());
    }

    @PutMapping
    public ResponseEntity modify(@RequestBody ModifyBoardRequestView modify) {
        boardService.modify(modify);
        return ResponseEntity.ok().build();
    }

}
