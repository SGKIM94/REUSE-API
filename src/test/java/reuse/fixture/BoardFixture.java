package reuse.fixture;

import reuse.domain.Board;
import reuse.dto.board.CreateBoardRequestView;
import reuse.dto.board.FindBoardResponseView;
import reuse.dto.board.ListBoardResponseView;
import reuse.dto.board.ModifyBoardRequestView;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductFixture.TEST_PRODUCT_ID;
import static reuse.fixture.UserFixture.TEST_USER;

public class BoardFixture extends CommonFixture {
    public static final String TEST_BOARD_CONTENT = "테스트 판매";
    public static final String TEST_SECOND_BOARD_CONTENT = "테스트 두번쨰 판매";
    public static final String TEST_SELLER_ADDRESS = "경기도 성남시";
    public static final String TEST_BOARD_TITLE = "테스트 판매 게시판";
    public static final String TEST_SECOND_BOARD_TITLE = "테스트 판매 두번째 게시판";
    public static final String TEST_MODIFY_BOARD_TITLE = "수정된 판매 게시판";

    public static final Board TEST_BOARD
            = Board.builder().title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).build();

    public static final Board MODIFY_TEST_BOARD
            = Board.builder().title(TEST_MODIFY_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).build();

    public static final CreateBoardRequestView CREATE_BOARD_REQUEST_VIEW
            = CreateBoardRequestView.builder().content(TEST_BOARD_CONTENT).title(TEST_BOARD_TITLE)
            .sellerAddress(TEST_SELLER_ADDRESS).productId(TEST_PRODUCT_ID).build();

    public static final CreateBoardRequestView CREATE_SECOND_BOARD_REQUEST_VIEW
            = CreateBoardRequestView.builder().content(TEST_SECOND_BOARD_CONTENT).title(TEST_SECOND_BOARD_TITLE)
            .sellerAddress(TEST_SELLER_ADDRESS).productId(TEST_PRODUCT_ID).build();

    public static final FindBoardResponseView FIND_BOARD_RESPONSE_VIEW
            = FindBoardResponseView.builder().id(DEFAULT_ID).title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).seller(TEST_USER).build();

    public static final ListBoardResponseView LIST_BOARD_RESPONSE_VIEW
             = ListBoardResponseView.builder().boards(Arrays.asList(FIND_BOARD_RESPONSE_VIEW, FIND_BOARD_RESPONSE_VIEW))
            .build();

    public static final List<Board> TEST_BOARDS
            = Arrays.asList(TEST_BOARD, TEST_BOARD);

    public static final ModifyBoardRequestView MODIFY_BOARD_REQUEST_DTO
            = ModifyBoardRequestView.builder().board(MODIFY_TEST_BOARD).build();
}
