INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, tax, thumbnail_image, create_at, update_at)
VALUES ('9999999998', '123', '테스트 설명', true, false, '테스트 품목', '20000', '1000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T10%3A41%3A31.267/thumbnail/1590716298286', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product (id, category_id, explanation, is_sold, is_used, name, price, tax, thumbnail_image, create_at, update_at)
VALUES ('9999999999', '123', '테스트 설명2', true, false, '테스트 품목2', '50000', '5000', 'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T10%3A41%3A31.267/thumbnail/1590716298286', '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product_images (first_image, second_image, third_image, fourth_image, fifth_image, sixth_image, create_at, update_at)
VALUES (
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682/test-product-image',
        '2020-05-29 10:25:49', '2020-05-29 10:25:49');

INSERT INTO product_images (first_image, second_image, third_image, fourth_image, fifth_image, sixth_image, create_at, update_at)
VALUES (
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682',
           'https://reuse-s3.s3.ap-northeast-2.amazonaws.com/products/2020-05-29T09%3A57%3A51.887/1590713808682/test-product-image',
        '2020-05-29 10:25:49', '2020-05-29 10:25:49');
