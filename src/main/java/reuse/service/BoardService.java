package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.domain.User;
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

    public CreateBoardResponseView create(CreateBoardRequestView board, User seller) {
        Product product = productService.findById(board.getProductId());
        Board savedBoard = boardRepository.save(CreateBoardRequestView.toEntity(board, product, seller));
        return CreateBoardResponseView.toDto(savedBoard);
    }

    public ListBoardResponseView list() {
        return ListBoardResponseView.toDto(boardRepository.findAll());
    }

    public ListBoardByCategoryResponseView listByCategory(ListBoardByCategoryRequestView category) {
        return boardRepository.findAllByCategory(category.toEntity());
    }

    public Board modify(ModifyBoardRequestView modify) {
        Board board = modify.toEntity();
        board.modify(modify);
        return board;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }

    public FindBoardResponseView retrieve(Long id) {
        return FindBoardResponseView.toDto(findById(id));
    }

    public void delete(Long id) {
        Board board = findById(id);
        board.delete();
    }
}
