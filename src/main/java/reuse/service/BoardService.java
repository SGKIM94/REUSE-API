package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ModifyBoardRequestView;
import reuse.repository.BoardRepository;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public CreateBoardResponseView create(CreateBoardRequestView board) {
        Board savedBoard = boardRepository.save(CreateBoardRequestView.toEntity(board));
        if (savedBoard == null) {
            throw new IllegalArgumentException("Fail create board!");
        }

        return CreateBoardResponseView.toDto(savedBoard);
    }

    public ListBoardResponseView list() {
        return ListBoardResponseView.toDto(boardRepository.findAll());
    }

    public void modify(ModifyBoardRequestView modify) {
        Board board = modify.toEntity();
//        board.modify();
    }
}
