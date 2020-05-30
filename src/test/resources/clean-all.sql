DELETE FROM board WHERE create_at IS NOT NULL;
DELETE FROM product WHERE create_at IS NOT NULL;
DELETE FROM product_images WHERE create_at IS NOT NULL;
