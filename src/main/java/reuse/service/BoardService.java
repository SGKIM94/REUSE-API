package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Board;
import reuse.domain.User;
import reuse.dto.board.*;
import reuse.repository.BoardRepository;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public CreateBoardResponseView create(CreateBoardRequestView board, User seller) {
        Board savedBoard = boardRepository.save(CreateBoardRequestView.toEntity(board, seller));
        return CreateBoardResponseView.toDto(savedBoard);
    }

    @Transactional(readOnly = true)
    public ListBoardResponseView list() {
        return ListBoardResponseView.toDto(boardRepository.findAll());
    }

    public ListBoardWithProductResponseView listByCategory(ListBoardByCategoryRequestView category) {
        return boardRepository.findAllByCategory(category.toEntity());
    }

    @Transactional
    public Board modify(ModifyBoardRequestView modify, Long id) {
        Board board = findById(id);
        return board.modify(modify);
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public FindBoardResponseView retrieve(Long id) {
        return FindBoardResponseView.toDto(findById(id));
    }

    @Transactional
    public void delete(Long id) {
        Board board = findById(id);
        board.delete();
    }

    @Transactional
    public Board reserve(ModifyBoardStatusRequestView boardId, User requester) {
        Board board = findById(boardId.getId());

        Board modifiedBoard = board.registerBuyer(requester);

        return modifiedBoard.reserve();
    }

    @Transactional
    public Board complete(ModifyBoardStatusRequestView boardId) {
        Board board = findById(boardId.getId());

        return board.complete();
    }
}
