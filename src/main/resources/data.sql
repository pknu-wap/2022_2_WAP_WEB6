INSERT INTO procon_topic(id, content, due_date, pro_con, reason, topic, user_id, book_id)
VALUES (1, '1번째 content', '2022-02-22', true, '1번째 이유', '1번째 topic', 1, 1);
INSERT INTO procon_topic(id, content, due_date, pro_con, reason, topic, user_id, book_id)
VALUES (2, '2번째 content', '2022-02-23', true, '2번째 이유', '2번째 topic', 1, 1);
INSERT INTO procon_topic(id, content, due_date, pro_con, reason, topic, user_id, book_id)
VALUES (3, '3번째 content', '2022-02-24', true, '3번째 이유', '3번째 topic', 2, 2);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (1, 1, 25, 52, true, '1번째 찬반토론 1번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content )
VALUES (2, 1, 52, 25, false, '2번째 찬반토론 2번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (3, 2, 25, 520, true, '1번째 찬반토론 1번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (4, 2, 25, 52, false, '2번째 찬반토론 2번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (5, 3, 25, 52, false, '1번째 찬반토론 1번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (6, 3, 25, 52, false, '2번째 찬반토론 2번째 댓글');
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content)
VALUES (7, 3, 25, 52, true, '3번째 찬반토론 3번째 댓글');

INSERT INTO book(id, title, authors, url, contents)
VALUES (1, '책제목1', '저자1','url1','설명1');
INSERT INTO book(id, title, authors, url, contents)
VALUES (2, '책제목2', '저자2','url2','설명2');
INSERT INTO book(id, title, authors, url, contents)
VALUES (3, '책제목3', '저자3','url3','설명3');
INSERT INTO book(id, title, authors, url, contents)
VALUES (4, '책제목4', '저자4','url4','설명4');



