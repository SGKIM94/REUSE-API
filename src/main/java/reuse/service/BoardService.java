package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.dto.board.*;
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
        board.modify(modify);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public FindBoardResponseView retrieve(Long id) {
        return FindBoardResponseView.toDto(findById(id));
    }
}
