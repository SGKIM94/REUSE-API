-- clean all
DELETE FROM favorite_board WHERE id IS NOT NULL;
DELETE FROM board WHERE id IS NOT NULL;
DELETE FROM product WHERE id IS NOT NULL;
DELETE FROM product_images WHERE id IS NOT NULL;
DELETE FROM category WHERE id IS NOT NULL;
DELETE FROM chat_room WHERE id IS NOT NULL;
DELETE FROM buyer_review WHERE id IS NOT NULL;
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
INSERT INTO product (id, explanation, is_sold, is_used, name, price, tax, thumbnail_image, create_at, update_at)
VALUES ('9999999998', '테스트 설명', true, false, '테스트 품목', '20000', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T10%3A41%3A31.267/thumbnail/1590716298286', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, explanation, is_sold, is_used, name, price, tax, thumbnail_image, create_at, update_at)
VALUES ('9999999999', '테스트 설명2', true, false, '테스트 품목2', '50000', '5000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T10%3A41%3A31.267/thumbnail/1590716298286', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product_images (id, first_image, second_image, third_image, fourth_image, fifth_image, sixth_image, create_at, update_at)
VALUES (    '1',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
            'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682/test-product-image',
            '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product_images (id, first_image, second_image, third_image, fourth_image, fifth_image, sixth_image, create_at, update_at)
VALUES (
           '2',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682/test-product-image',
           '2020-05-29 10:25:49', '2020-05-29 10:25:49');


INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('1', '1', '첫번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('2', '2', '두번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('3', '3', '세번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('4', '4', '네번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('5', '5', '다섯번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, thumbnail_image, product_images_id, quality, create_at, update_at)
VALUES ('6', '6', '다섯번 째 상품', false, false, '첫번째', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682', '1', '1', '2020-05-29 10:25:49', '2020-05-29 10:25:49');


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




