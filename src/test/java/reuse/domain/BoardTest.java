package reuse.domain;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static reuse.fixture.BoardFixture.*;
import static reuse.fixture.BuyerReviewFixture.TEST_BUYER_REVIEW;
import static reuse.fixture.SellerReviewFixture.TEST_SELLER_REVIEW;
import static reuse.fixture.UserFixture.TEST_SOCIAL_TOKEN_ID;
import static reuse.fixture.UserFixture.TEST_USER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardTest {
    @DisplayName("Board 에 BuyerReview 를 연결시키기위한 외래키를 저장 시 존재하지 않을 경우 예외처리하는지")
    @Test
    @Order(1)
    public void mappingBuyerReviewFail() {
        //when
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            TEST_BOARD.mappingBuyerReview(null);
        }).getMessage();

        assertThat(errorMessage).isEqualTo("Empty buyerReview when mapping to the Board!");
    }

    @DisplayName("게시판을 예약 시 SalesStatus 값을 RESERVE 로 변경시키는지")
    @Test
    @Order(2)
    public void reserve() {
        //given
        Board board = TEST_BOARD;

        //when
        board.reserve();

        assertThat(board.getSalesStatus()).isEqualTo(SalesStatusType.RESERVE);
    }

    @DisplayName("게시판을 예약 시 SalesStatus 값을 COMPLETE 로 변경시키는지")
    @Test
    @Order(3)
    public void complete() {
        //given
        Board board = TEST_THIRD_BOARD;

        //when
        board.complete();

        //then
        assertThat(board.getSalesStatus()).isEqualTo(SalesStatusType.COMPLETE);
    }

    @DisplayName("구매 예약 요청을 한 로그인 사용자와 게시글에 등록되어있는 판매자가 다른 경우 예외를 처리하는지")
    @Test
    @Order(4)
    public void verifyThatBuyerAndRequestAreTheSame() {
        //given
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            TEST_SECOND_BOARD.verifyThatBuyerAndRequestAreTheSame(TEST_SOCIAL_TOKEN_ID);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("판매자와 예약 신청한 사용자가 다릅니다.");
    }


    @DisplayName("예약 신청 시 게시글이 판매 중인 상태 값이 아닐 때 예외를 처리하는지")
    @Test
    @Order(5)
    public void verifyThatBoardCanChangeStatus() {
        //given
        String errorMessage = assertThrows(IllegalArgumentException.class, () -> {
            TEST_SECOND_BOARD.verifyThatBoardCanChangeStatus(SalesStatusType.SALE);
        }).getMessage();

        //then
        assertThat(errorMessage).isEqualTo("현재 상태 :  COMPLETE | 요구되는 상태 SALE");
    }

    @DisplayName("Board 에 BuyerReview 를 연결시키기위한 외래키를 저장하는지")
    @Test
    @Order(6)
    public void mappingBuyerReview() {
        //given
        BuyerReview buyerReview = TEST_BUYER_REVIEW;
        Board board = TEST_BOARD;

        //when
        board.mappingBuyerReview(buyerReview);

        //then
        assertThat(board.getBuyerReviewId()).isEqualTo(buyerReview.getId());
    }

    @DisplayName("게시글이 삭제가 되는지")
    @Test
    @Order(7)
    public void delete() {
        //given
        Board board = TEST_BOARD;

        //when
        board.delete();

        //then
        assertThat(board.getIsDeleted()).isTrue();
    }

    @DisplayName("게시물을 수정 파라미터의 Board 가 비었을 때 빈 Board 를 리턴하는지")
    @Test
    @Order(8)
    public void modifyWhenEmpty() {
        //when
        Board modifiedBoard = TEST_BOARD.modify(null);

        //then
        assertThat(modifiedBoard.getTitle()).isNotBlank();
    }

    @DisplayName("게시물을 수정 가능한지")
    @Test
    @Order(9)
    public void modify() {
        //when
        Board modifiedBoard = TEST_BOARD.modify(MODIFY_BOARD_REQUEST_DTO);

        //then
        assertThat(modifiedBoard.getTitle()).isEqualTo(TEST_MODIFY_BOARD_TITLE);
    }

    @DisplayName("게시물에 구매자를 등록 가능한지")
    @Test
    @Order(10)
    public void registerBuyer() {
        //when
        Board modifiedBoard = TEST_BOARD.registerBuyer(TEST_USER);

        //then
        assertThat(modifiedBoard.getBuyerId()).isEqualTo(DEFAULT_ID);
    }

    @DisplayName("게시물에 판매자의 후기를 등록 가능한지")
    @Test
    @Order(11)
    public void registerSellerReview() {
        //when
        Board modifiedBoard = TEST_BOARD.registerSellerReview(TEST_SELLER_REVIEW);

        //then
        assertThat(modifiedBoard.getSellerReview().getId()).isEqualTo(TEST_SELLER_REVIEW.getId());
    }

}
