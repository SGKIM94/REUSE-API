INSERT INTO buyer_review
(buyer_id, score, title, content, create_at, update_at) VALUES (1, '10', '첫번 째 라뷰', '첫번 째 라뷰 내용', now(), now());

INSERT INTO buyer_review
(buyer_id, score, title, content, create_at, update_at) VALUES (1, '5', '두번 째 라뷰', '두번 째 라뷰 내용', now(), now());

INSERT INTO buyer_review
(buyer_id, score, title, content, create_at, update_at) VALUES (1, '7', '세번 째 라뷰', '세번 째 라뷰 내용', now(), now());

UPDATE board SET buyer_review_id = 1 WHERE id = '1';
UPDATE board SET buyer_review_id = 2 WHERE id = '2';
UPDATE board SET buyer_review_id = 3 WHERE id = '3';
