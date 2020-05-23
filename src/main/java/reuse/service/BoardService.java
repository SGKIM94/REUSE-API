package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.CreateBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ModifyBoardRequestView;
import reuse.repository.BoardRepository;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private ProductService productService;

    public BoardService(BoardRepository boardRepository, ProductService productService) {
        this.boardRepository = boardRepository;
        this.productService = productService;
    }

    public CreateBoardResponseView create(CreateBoardRequestView board) {
        Product product = productService.findById(board.getProductId());
        Board savedBoard = boardRepository.save(CreateBoardRequestView.toEntity(board, product));
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
