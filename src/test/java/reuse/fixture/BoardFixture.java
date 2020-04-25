package reuse.fixture;

import reuse.domain.Board;
import reuse.dto.board.CreateBoardRequestView;

import static reuse.fixture.ProductFixture.TEST_PRODUCT;

public class BoardFixture extends CommonFixture {
    public static final String TEST_BOARD_CONTENT = "테스트 판매";
    public static final String TEST_SELLER_ADDRESS = "경기도 성남시";
    public static final String TEST_BOARD_TITLE = "테스트 판매 게시판";

    public static final Board TEST_BOARD
            = Board.builder().id(DEFAULT_ID).title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).build();


    public static final CreateBoardRequestView CREATE_BOARD_REQUEST_VIEW
            = CreateBoardRequestView.builder().board(TEST_BOARD).build();
}
