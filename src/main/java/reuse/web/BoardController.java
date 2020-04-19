package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
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
}
