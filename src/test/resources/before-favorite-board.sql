-- clean all
DELETE FROM favorite_board WHERE id IS NOT NULL;
DELETE FROM board WHERE id IS NOT NULL;
DELETE FROM image WHERE id IS NOT NULL;
DELETE FROM product WHERE id IS NOT NULL;
DELETE FROM category WHERE id IS NOT NULL;
DELETE FROM chat_room WHERE id IS NOT NULL;
DELETE FROM buyer_review WHERE id IS NOT NULL;
DELETE FROM seller_review WHERE id IS NOT NULL;

-- clean user
DELETE FROM user WHERE id IS NOT NULL;

-- insert users
INSERT INTO user (id, create_at, update_at, name, social_token_id, social_type)
VALUES ('1', '2020-05-29 10:25:49', '2020-05-29 10:25:49', '홍길동', 'toeknid', 'NAVER');

-- insert categories
INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('1', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '1', '1', '1');

INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('2', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '2', '2', '2');

INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('3', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '3', '3', '3');

INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('4', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '4', '4', '4');

INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('5', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '5', '5', '5');

INSERT INTO category
(id, create_at, update_at, teleco, manufacturer, model)
VALUES ('6', '2020-06-03 09:22:12', '2020-06-03 09:22:12', '1', '2', '2');

-- insert products and images
INSERT INTO product (id, explanation, is_sold, is_used, name, price, tax, create_at, update_at)
VALUES ('9999999998', '테스트 설명', true, false, '테스트 품목', '20000', '1000', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, explanation, is_sold, is_used, name, price, tax, create_at, update_at)
VALUES ('9999999999', '테스트 설명2', true, false, '테스트 품목2', '50000', '5000', '2020-05-29 10:25:49', '2020-05-29 10:25:49');


INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('1', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('2', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('3', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('4', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('5', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('6', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999998', '2020-05-29 09:57:52', '2020-05-29 09:57:52');


INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('7', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999999', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('8', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999999', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('9', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999999', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('10', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999999', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('11', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '9999999999', '2020-05-29 09:57:52', '2020-05-29 09:57:52');


INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('1', '1', '첫번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('2', '2', '두번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('3', '3', '세번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('4', '4', '네번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('5', '5', '다섯번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, quality, create_at, update_at)
VALUES ('6', '6', '다섯번 째 상품', false, false, '첫번째', '1000', 'S', '2020-05-29 10:25:49', '2020-05-29 10:25:49');


INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('12', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '1', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('13', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '2', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('14', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '3', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('15', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '4', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('16', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '5', '2020-05-29 09:57:52', '2020-05-29 09:57:52');

INSERT INTO image (id, url, product_id, update_at, create_at)
VALUES ('17', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/1/foo1', '6', '2020-05-29 09:57:52', '2020-05-29 09:57:52');


-- insert boards
INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('1', '테스트 게시판1', '경기도 성남시', '첫번째 제목', '1', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('2', '테스트 게시판2', '서울 특별시', '두번째 제목', '2', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('3', '테스트 게시판3', '전라도 광역시', '세번째 제목', '3', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('4', '테스트 게시판4', '전주시', '네번째 제목', '4', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('5', '테스트 게시판5', '부산 광역시', '다섯번째 제목', '5', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO board
(id, content, seller_address, title, product_id, seller_id, is_deleted, create_at, update_at)
VALUES('6', '테스트 게시판6', '강원도 강릉', '다섯번째 제목', '6', '1', false, '2020-05-29 10:25:49', '2020-05-29 10:25:49');


-- insert favorite boards
INSERT INTO favorite_board
(id, board_id, user_id, create_at, update_at)
VALUES('1', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO favorite_board
(id, board_id, user_id, create_at, update_at)
VALUES('2', '2', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO favorite_board
(id, board_id, user_id, create_at, update_at)
VALUES('3', '3', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO favorite_board
(id, board_id, user_id, create_at, update_at)
VALUES('4', '3', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');




