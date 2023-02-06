INSERT INTO book(id, title, authors, url, contents)
VALUES (1, '데미안', '헤르만 헤세',
        'https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F540810%3Ftimestamp%3D20221108011611',
        '현실에 대결하는 영혼의 발전을 담은 헤르만 헤세의 걸작 『데미안』. 독일 문학의 거장이자 노벨문학상 수상작가 헤르만 헤세의 자전적 소설이다. 1차 세계대전 직후인 1919년 에밀 싱클레어라는 가명으로 발표했던 작품으로, 열 살 소년이 스무 살 청년이 되기까지 고독하고 힘든 성장의 과정을 그리고 있다. 불안과 좌절에 사로잡힌 청춘의 내면을 다룬 이 작품은 지금까지 수많은 청년세대의 마음에 깊은 울림을 전하고 있다.    목사인 부친과 선교사의 딸인');
INSERT INTO book(id, title, authors, url, contents)
VALUES (2, '아가미', '구병모',
        'https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1605667%3Ftimestamp%3D20221108000129',
        '간절히 숨 쉬고 싶은 우리를 살게 해주는 상처, 『아가미』. 소설가 구병모의 대표작 〈아가미〉가 돌아왔다. 수많은 마니아 독자들 사이에서 재출간 요구가 속출했던 바로 그 작품이 예쁘게 새옷을 갈아입고 세상에 새로이 선을 보인다. 〈아가미〉는 죽음의 문턱에서 아가미를 갖게 된 소년의 슬픈 운명을 그려낸 아름다운 잔혹동화이다. 잇따른 불행으로 삶의 벼랑 끝에 내몰린 한 남자는 돌이킬 수 없는 절망으로 아들을 품에 안은 채 호수로 몸을 던진다. 남자는');
INSERT INTO book(id, title, authors, url, contents)
VALUES (3, '자바를 다루는 기술', '김병부',
        'https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F923727%3Ftimestamp%3D20220103152724',
        '『자바를 다루는 기술』은 자바 언어의 기초 문법을 친절하고 자세하게 설명한다. 객체 지향 프로그래밍 개념은 물론, 자바의 자료구조, 제네릭(generics), 리플렉션(reflection) 등 고급 응용 기법들을 다양한 예제를 통해 익힐 수 있도록 구성하였다. 또한 저자의 실무 경험 속에서 얻은 노하우와 팁들을 제시하고, 오픈 소스 라이브러리 응용법 등을 통해 실무 적응력을 높여 독자들이 다양한 개발 현장에서 자바 프로젝트를 어려움 없이 수행할 수 있도록');
INSERT INTO book(id, title, authors, url, contents)
VALUES (4, '토스트(양장본 HardCover)', '나이젤 슬레이터',
        'https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1422714%3Ftimestamp%3D20190129233256',
        '영국의 유명 요리사이자 작가, 방송인으로도 활동하고 있는 나이젤 슬레이터의 자전적 이야기 『토스트』. 어느 호기심 많은 소년이 바라본 음식과 사랑에 대한 이야기를 담은 소설이다. 향수를 불러일으키는 1960년대를 배경으로 레몬머랭 파이, 봉봉 초콜릿, 로스트 비프 등 맛있는 요리들과 감동적인 에피소드들이 펼쳐진다. 소년의 시선으로 다채로운 음식들을 묘사하고 있으며, 엄마를 잃은 소년의 아픔과 성장통도 함께 보여준다.  이 소설은 2010년 영국 BBC');


INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (1, '2022-02-22', true, '데미안 토론주제 이유', '데미안 토론주제 1 BLA BLA ', 1, 1, '데미안', false);
INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (2, '2022-02-23', true, '데미안 토론주제 이유', '데미안 토론주제 2 BLA BLA ', 1, 1, '데미안', false);

INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (3, '2022-02-24', true, '아가미가 재미있는 이유는 이러이러한 이유 때문이다.', '아가미가 재미 있는 이유', 2, 2, '아가미', false);

INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (4, '2022-02-24', true, '자바는 어렵기 때문이다.', '자바를 재미 없는 이유', 2, 3, '자바를 다루는 기술', true);

INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (5, '2022-02-25', true, '자바는 어렵기 때문이다.', '자바를 재미 없는 이유', 2, 3, '자바를 다루는 기술', false);

INSERT INTO procon_topic(id, due_date, pro_con, reason, topic, user_id, book_id, book_title, expired)
VALUES (6, '2022-02-26', true, '자바는 어렵기 때문이다.', '자바를 재미 없는 이유', 2, 3, '자바를 다루는 기술', false);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (1, 1, 25, 52, true, '데미안 토론주제 찬성 1', 1, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (2, 1, 52, 25, false, '데미안 토론주제 반대 1', 1, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (3, 1, 25, 520, true, '데미안 토론주제 찬성 1', 1, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (4, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (5, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (6, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (7, 3, 25, 52, true, '아가미가 재미 있는 이유는 이러한 이유 때문이다. ', 2, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (8, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (9, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (10, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (11, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (12, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (13, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);
INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (14, 2, 25, 52, false, '데미안 토론주제 반대 2', 2, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (15, 2, 25, 52, true, '데미안 토론주제 반대 2', 2, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (16, 2, 25, 52, true, '데미안 토론주제 반대 2', 2, false, 0);

INSERT INTO comment(id, procontopic_id, dislike_num, like_num, pro_con, content, user_id, reply, parent_comment_id)
VALUES (17, 2, 25, 52, true, '데미안 토론주제 반대 2', 2, false, 0);





