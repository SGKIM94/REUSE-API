package reuse.fixture;

import reuse.domain.Board;
import reuse.domain.SalesStatusType;
import reuse.dto.board.*;

import java.util.Arrays;
import java.util.List;

import static reuse.fixture.CategoryFixture.*;
import static reuse.fixture.ProductFixture.TEST_PRODUCT;
import static reuse.fixture.ProductFixture.TEST_PRODUCT_ID;
import static reuse.fixture.UserFixture.TEST_SECOND_USER;
import static reuse.fixture.UserFixture.TEST_USER;

public class BoardFixture extends CommonFixture {
    public static final String TEST_BOARD_CONTENT = "테스트 판매";
    public static final String TEST_SECOND_BOARD_CONTENT = "테스트 두번쨰 판매";
    public static final String TEST_SELLER_ADDRESS = "경기도 성남시 TEST DATA";
    public static final String TEST_BOARD_TITLE = "테스트 판매 게시판";
    public static final String TEST_SECOND_BOARD_TITLE = "테스트 판매 두번째 게시판";
    public static final String TEST_MODIFY_BOARD_TITLE = "수정된 판매 게시판";

    public static final long TEST_FIRST_BOARD_ID = 1L;
    public static final long TEST_SECOND_BOARD_ID = 2L;
    public static final long TEST_SIXTH_BOARD_ID = 6L;

    public static final Board TEST_BOARD
            = Board.builder().id(TEST_FIRST_BOARD_ID).title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).buyer(TEST_SECOND_USER).seller(TEST_USER)
            .salesStatus(SalesStatusType.SALE).build();

    public static final Board TEST_SECOND_BOARD
            = Board.builder().title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).salesStatus(SalesStatusType.COMPLETE)
            .seller(TEST_USER).buyer(TEST_SECOND_USER).build();

    public static final Board TEST_THIRD_BOARD
            = Board.builder().title(TEST_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).salesStatus(SalesStatusType.RESERVE)
            .seller(TEST_USER).build();

    public static final Board MODIFY_TEST_BOARD
            = Board.builder().title(TEST_MODIFY_BOARD_TITLE).content(TEST_BOARD_CONTENT)
            .product(TEST_PRODUCT).sellerAddress(TEST_SELLER_ADDRESS).build();

    public static final CreateBoardRequestView CREATE_BOARD_REQUEST_VIEW
            = CreateBoardRequestView.builder().content(TEST_BOARD_CONTENT).title(TEST_BOARD_TITLE)
            .sellerAddress(TEST_SELLER_ADDRESS).productId(TEST_PRODUCT_ID).build();

    public static final CreateBoardRequestView CREATE_SECOND_BOARD_REQUEST_VIEW
            = CreateBoardRequestView.builder().content(TEST_SECOND_BOARD_CONTENT).title(TEST_SECOND_BOARD_TITLE)
            .sellerAddress(TEST_SELLER_ADDRESS).productId(TEST_PRODUCT_ID).build();

    public static final List<Board> TEST_BOARDS
            = Arrays.asList(TEST_BOARD, TEST_BOARD);

    public static final ModifyBoardRequestView MODIFY_BOARD_REQUEST_DTO
            = ModifyBoardRequestView.builder().board(MODIFY_TEST_BOARD).build();

    public static final FindWithProductResponseView FIND_BY_CATEGORY_RESPONSE_VIEW
            = FindWithProductResponseView.builder().board(TEST_BOARD).build();

    public static final FindWithProductResponseView SECOND_FIND_BY_CATEGORY_RESPONSE_VIEW
            = FindWithProductResponseView.builder().board(TEST_BOARD).build();

    public static final ListBoardWithProductResponseView LIST_BOARD_BY_CATEGORY_RESPONSE_VIEW
            = ListBoardWithProductResponseView.builder()
            .boards(Arrays.asList(FIND_BY_CATEGORY_RESPONSE_VIEW, SECOND_FIND_BY_CATEGORY_RESPONSE_VIEW))
            .build();

    public static final ListBoardByCategoryRequestView LIST_BOARD_BY_CATEGORY_REQUEST_VIEW
             = ListBoardByCategoryRequestView.builder().manufacturer(TEST_MANUFACTURER).model(TEST_MODEL)
            .teleco(TEST_TELECO).build();

    public static final ModifyBoardStatusRequestView MODIFY_BOARD_STATUS_REQUEST_VIEW
            = ModifyBoardStatusRequestView.builder().id(DEFAULT_ID).build();
}
