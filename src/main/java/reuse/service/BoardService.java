package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.dto.board.*;
import reuse.dto.user.FindByIdResponseView;
import reuse.repository.BoardRepository;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private ProductService productService;
    private UserService userService;

    public BoardService(BoardRepository boardRepository, ProductService productService, UserService userService) {
        this.boardRepository = boardRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public CreateBoardResponseView create(CreateBoardRequestView board) {
        Product product = productService.findById(board.getProductId());
        //TODO : 게시판에 사용자 정보를 저장하기 위해 조회하거나 LoginUser annotation 사용하도록 필요
        FindByIdResponseView user = userService.findById(board.getUserId());
        Board savedBoard = boardRepository.save(CreateBoardRequestView.toEntity(board, product, user.));
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
