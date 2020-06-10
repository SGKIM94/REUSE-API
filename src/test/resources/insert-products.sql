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
