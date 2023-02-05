-- 230104 수정사항
CREATE SEQUENCE SEQ_ingTag_tag_id INCREMENT BY 1 START WITH 1;
drop sequence SEQ_ingTag_tag_id;


-- 230103 수정 사항
alter table ingredient drop column quantity;
alter table ingredient drop column price;

alter table ri add quantity varchar2(50);
alter table ri add price nmber(10);
alter table ri add code_detail varchar2(50);

-- 뷰 : interest_view, favorite_view, ri_view


create or replace view riView 
as
select r.rnum, i.code, i.iname, r.subject
from recipe r, ingredient i, ri a
where r.rnum=a.rnum and i.code=a.code;

alter table recipe add thumbnail varchar2(100) not null;
alter table members add useyn char(1) default 'Y';

select * from processImg;
select * from recipe;
select * from members;
select * from admins;
select * from qna;
select * from reply;
select * from recipeTag;
select * from ingTag;

alter table processImg drop primary key;

insert into admins(aid,pwd,phone) values('admin', 'admin', '010-1111-1111');

insert into qna(qseq, id, qsubject, qcontent) values(SEQ_qna_qseq.nextVal, 'somi', '레시피 재료가 잘못 기재됐습니다.', '수정할 수 있을까요?');
insert into reply(replyseq, id, rnum, content) values(SEQ_reply_replyseq.nextVal, 'scott', 7, '정말 맛있어 보입니다.');

insert into members(id, pwd, name, phone, email, nick, address1, address2, zip_num, img)
values('scott', '1234', '홍길동', '010-8469-7436', 'ezen@neodo.gosu', '검성길동', 
'서울시 성동구 성수동 1가', '1번지21호', '133-110', '/imageProfile/profile1.png');

insert into members(id, pwd, name, phone, email, nick, address1, address2, zip_num, img)
values('somi', '1234', '아이유', '010-8771-9256', 'aiu@doko.aiu', '우윳빛깔아이유', 
'서울시 송파구 잠실2동', '리센츠 아파트 201동 505호', '130-120', '/imageProfile/profile2.png');

alter table members modify nick varchar2(50);
alter table recipe modify subject varchar2(100);
alter table qna modify qsubject varchar2(200);

create sequence recipe_seq increment by 1 start with 1;
drop sequence recipe_seq;

alter table ingredient modify quantity varchar2(50);

select * from recipe;
select * from PROCESSIMG;
-- 1번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '복숭아 코블러', '지금 한창 제철인 천도복숭아를 활용하여 만드는 상큼한 디저트 메뉴!', 60, 6 , '/imageThumb/peachThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 1, '/imageRecipe/peach1.jpg', '복숭아는 씨를 제거하고 적당한 크기로 썬다.', 1);
insert into processImg(rnum, links, description, iseq) values( 1, '/imageRecipe/peach2.jpg', '볼에 복숭아, 설탕, 계피 가루를 넣고 섞어 절인다.', 2);
insert into processImg(rnum, links, description, iseq) values( 1, '/imageRecipe/peach3.jpg', '다른 볼에 중력분, 설탕, 베이킹파우더, 소금, 우유를 넣고 섞는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 1, '/imageRecipe/peach4.jpg', '오븐 용기에 버터를 넣고 전자레인지에 돌려 녹인다.', 4);
insert into processImg(rnum, links, description, iseq) values( 1, '/imageRecipe/peach5.jpg', '에어프라이어에 넣고 160℃에서 30분 굽는다.', 5);
-- 재료 : 천도복숭아 2개, 설탕 3숟가락, 계피가루 1/3숟가락, 중력분 80g, 설탕 70g, 베이킹파우더 약간, 소금 약간, 우유 2/3종이컵, 버터 40g
select * from ingTag;
select * from recipeTag;
UPDATE ingTag SET tag_id=1 WHERE tag='천도복숭아';
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '천도복숭아');
insert into recipeTag(tag_id, rnum, quantity) values(1, 1, '2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'설탕');
insert into recipeTag(tag_id, rnum, quantity) values(2, 1, '3스푼');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'계피가루');
insert into recipeTag(tag_id, rnum, quantity) values(3, 1, '1/3스푼');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'중력분');
insert into recipeTag(tag_id, rnum, quantity) values(4, 1, '80g');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'베이킹파우더');
insert into recipeTag(tag_id, rnum, quantity) values(5, 1, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'소금');
insert into recipeTag(tag_id, rnum, quantity) values(6, 1, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'우유');
insert into recipeTag(tag_id, rnum, quantity) values(7, 1, '2/3종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal,'버터');
insert into recipeTag(tag_id, rnum, quantity) values(8, 1, '40g');


-- 2번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '앙쿠르트 스프', '스프를 컵에 담고 그위에 페이스트리 반죽을 덮어서 오븐에 바삭하게 구운 음식', 60, 4 , '/imageThumb/piesoupThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 2, '/imageRecipe/piesoup1.jpg', '양파는 채썰고 감자는 슬라이스한다. 냄비에 버터를 녹이고 양파를 충분히 볶는다', 1);
insert into processImg(rnum, links, description, iseq) values( 2, '/imageRecipe/piesoup2.jpg', '감자를 넣고 함께 볶다가 물, 치킨스톡을 넣고 끓인다.', 2);
insert into processImg(rnum, links, description, iseq) values( 2, '/imageRecipe/piesoup3.jpg', '감자가 익으면 핸드블렌더로 갈아준다.', 3);
insert into processImg(rnum, links, description, iseq) values( 2, '/imageRecipe/piesoup4.jpg', '생크림을 넣고 약불에서 끓여 농도를 맞춘다. 소금, 후추로 간 한다.오븐 용기에 담고 페이스트리 생지로 용기를 덮는다', 4);
insert into processImg(rnum, links, description, iseq) values( 2, '/imageRecipe/piesoup5.jpg', '오븐에 넣고 170℃에서 15분 굽는다.', 5);
-- 재료 : 감자 2개, 양파 1/2개, 버터 1조각, 물 2종이컵,  생크림 1/2종이컵, 치킨스톡 큐브 1개, 소금 약간, 후추 약간, 페이스트리생지 1장, 달걀 1개
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '감자');
insert into recipeTag(tag_id, rnum, quantity) values(9, 2, '2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '양파');
insert into recipeTag(tag_id, rnum, quantity) values(10, 2, '1/2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '버터'); -- 기존 8
insert into recipeTag(tag_id, rnum, quantity) values(8, 2, '1조각');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '물');
insert into recipeTag(tag_id, rnum, quantity) values(11, 2, '2종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '생크림');
insert into recipeTag(tag_id, rnum, quantity) values(12, 2, '1/2종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '치킨스톡큐브');
insert into recipeTag(tag_id, rnum, quantity) values(13, 2, '1개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '소금'); -- 기존 6
insert into recipeTag(tag_id, rnum, quantity) values(6, 2, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '후추');
insert into recipeTag(tag_id, rnum, quantity) values(14, 2, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '페이스트리생지');
insert into recipeTag(tag_id, rnum, quantity) values(15, 2, '1장');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '달걀');
insert into recipeTag(tag_id, rnum, quantity) values(16, 2, '1개');



-- 3번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '미역된장국', '집에 국 없을 때 미역, 두부 넣어 간단하게 만드는 국, 미역된장국 만드는 법', 30, 2 , '/imageThumb/swsoupThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 3, '/imageRecipe/swsoup1.jpg', '미역은 물에 불리고 두부는 한입 크기로 썬다. 냄비에 불린 미역, 참기름, 국간장을 넣고 약한 불로 볶는다.', 1);
insert into processImg(rnum, links, description, iseq) values( 3, '/imageRecipe/swsoup2.jpg', '다시물을 붓고 된장을 풀어주면서 끓인다.', 2);
insert into processImg(rnum, links, description, iseq) values( 3, '/imageRecipe/swsoup3.jpg', '한소끔 끓인 국에 다진마늘과 두부를 넣고 한번 더 끓인다.', 3);
-- 재료 : 자른미역 10g, 두부 1/2모, 다시물 1L, 된장 1숟가락, 참기름 1숟가락, 국간장 1숟가락, 다진마늘 1/2숟가락
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '자른미역');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '10g');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '두부');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1/2모');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '다시물');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1L');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '된장');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '참기름');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '국간장');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '다진마늘');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 3, '1/2숟가락');

-- 4번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '알배기배추무침', '반찬 없을때 간단히 만드는 알배기배추 요리', 15, 3 , '/imageThumb/cabbageThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 4, '/imageRecipe/cabbage1.jpg', '끓는 물에 소금을 넣고 배추를 데친다', 1);
insert into processImg(rnum, links, description, iseq) values( 4, '/imageRecipe/cabbage2.jpg', '찬물에 헹군 후 물기를 짜고 먹기 좋은 크기로 썬다.', 2);
insert into processImg(rnum, links, description, iseq) values( 4, '/imageRecipe/cabbage3.jpg', '쪽파는 3~4cm 길이로 썬다.', 3);
insert into processImg(rnum, links, description, iseq) values( 4, '/imageRecipe/cabbage4.jpg', '볼에 양념재료를 모두 넣고 섞는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 4, '/imageRecipe/cabbage5.jpg', '알배추, 쪽파에 양념을 넣고 무친 후 통깨를 뿌려 완성한다.', 5);
-- 재료 : 알배추 300g, 소금 1/2숟가락, 쪽파 2줄, 통깨 약간, 된장 1+1/2숟가락, 고춧가루 1/2숟가락, 매실액 1/3숟가락, 다진마늘 1/2숟가락, 참기름 1숟가락
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '알배추');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '300g');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '소금'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '1/2숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '쪽파'); 
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '2줄');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '통깨');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '된장'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '1.5스푼');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '고춧가루');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '1/2숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '매실액');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '1/3숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '다진마늘'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 4, '1/2숟가락');
-- 5번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '바지락야채죽', '봄 제철 식재료로 만드는 요리!바지락야채죽', 30, 1 , '/imageThumb/clamThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 5, '/imageRecipe/clam1.jpg', '끓는 물에 소금을 넣고 배추를 데친다', 1);
insert into processImg(rnum, links, description, iseq) values( 5, '/imageRecipe/clam2.jpg', '찬물에 헹군 후 물기를 짜고 먹기 좋은 크기로 썬다.', 2);
insert into processImg(rnum, links, description, iseq) values( 5, '/imageRecipe/clam3.jpg', '쪽파는 3~4cm 길이로 썬다.', 3);
insert into processImg(rnum, links, description, iseq) values( 5, '/imageRecipe/clam4.jpg', '볼에 양념재료를 모두 넣고 섞는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 5, '/imageRecipe/clam5.jpg', '알배추, 쪽파에 양념을 넣고 무친 후 통깨를 뿌려 완성한다.', 5);
-- 재료 : 쌀 1종이컵, 바지락살 1종이컵, 부추 30g, 양파 1/3개, 당근 1/4개, 소금 약간, 참기름 1숟가락, 물 4종이컵
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '쌀');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '1종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '바지락살');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '1종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '부추');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '30g');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '양파'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '1/3개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '당근');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '1/4개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '소금'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '약간');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '참기름'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '물'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 5, '4종이컵');

-- 6번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '믹스베리레몬에이드', '더위를 잊게 해줄 상큼한 음료 한 잔~ 과일티백으로 만드는 믹스베리레몬에이드 레시피를 소개해드릴게요.', 20, 5 , '/imageThumb/lemonadeThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 6, '/imageRecipe/lemonade1.jpg', '믹스베리 티백을 따뜻한 물에 진하게 우린다.', 1);
insert into processImg(rnum, links, description, iseq) values( 6, '/imageRecipe/lemonade2.jpg', '레몬은 즙을 짜서 믹스베리 티 우린 것과 섞는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 6, '/imageRecipe/lemonade3.jpg', '컵에 코코넛 젤리>얼음>사이다를 넣고 2를 넣는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 6, '/imageRecipe/lemonade4.jpg', '동결건조베리를 토핑으로 올려 완성한다.', 4);
-- 재료 : 레몬 2개, 사이다 300ml, 믹스베리 티 2개, 따뜻한물 50ml, 코코넛 젤리 1숟가락, 동결건조베리 적당량
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '레몬');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '사이다');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '300ml');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '믹스베리티');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '따뜻한물'); -- 기존 물과 분류 x
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '50ml');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '코코넛젤리');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '동결건조베리');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 6, '적당량');

-- 7번 레시피 
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '마제소바', '다진 고기를 볶아서 비벼먹는 일본식 비빔면!', 30, 4 , '/imageThumb/majesobaThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 7, '/imageRecipe/majesoba1.png', '[맛간장] 냄비에 맛간장 재료를 넣고 한소끔 끓인 다음 불을 끄고 가쓰오부시를 넣어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 7, '/imageRecipe/majesoba2.png', '부추와 쪽파는 쫑쫑 썰어 담고 팬에 고추기름, 다진마늘, 돼지고기 다짐육을 넣고 볶다가 양념을 넣고 자작하게 볶아요.', 2);
insert into processImg(rnum, links, description, iseq) values( 7, '/imageRecipe/majesoba3.png', '끓는 물에 면을 삶고 찬물에 헹궈요.', 3);
insert into processImg(rnum, links, description, iseq) values( 7, '/imageRecipe/majesoba4.png', '면에 맛간장 3숟가락, 고추기름 2숟가락을 넣고 비벼준 다음 그릇에 담아요.', 4);
insert into processImg(rnum, links, description, iseq) values( 7, '/imageRecipe/majesoba5.png', '부추, 파, 김, 다진마늘, 산초가루, 볶아둔 고기를 담고 달걀노른자를 올려 마무리해요.', 5);
-- 재료 : 돼지고기다짐육 2종이컵, 부추 1줌, 쪽파 1줌, 다진마늘 1숟가락, 우동면 2인분, 김가루 약간, 산초가루 약간, 달걀노른자 2개 [맛간장] 간장 1/2종이컵, 맛술 1/2종이컵, 다시마 2조각, 설탕 2숟가락, 가쓰오부시 1/2종이컵 [고기양념] 고추기름 1숟가락, 다진마늘 1숟가락, 굴소스 2숟가락, 맛간장 2숟가락, 두반장 1+1/2숟가락, 설탕 1숟가락, 맛술 2숟가락, 후추 약간 [다시마식초] 다시마 1조각, 식초 1/2종이컵, 설탕 1숟가락
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '돼지고기다짐육');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '2종이컵');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '부추'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '1줌');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '쪽파'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '1줌');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '다진마늘'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '우동면');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '2인분');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '달걀노른자');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '2개');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '맛간장');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '3숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '설탕'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 7, '1숟가락');

-- 8번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '오이고추 된장무침', '불 없이 간단하게 만들어 여름에 시원하고 아삭하게  즐길 수 있는 국민 밥반찬 메뉴!', 5, 3 , '/imageThumb/cucumberThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 8, '/imageRecipe/cucumber1.png', '오이고추를 먹기 좋은 크기로 자른다.', 1);
insert into processImg(rnum, links, description, iseq) values( 8, '/imageRecipe/cucumber2.png', '양념 재료를 섞는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 8, '/imageRecipe/cucumber3.png', '오이고추와 양념을 버무린다.', 3);
-- 재료 : 오이고추 100g [양념재료] 된장 1숟가락, 고추장 1/3숟가락, 마늘 1/2숟가락, 올리고당 1/2숟가락, 마요네즈 1숟가락, 깨소금 1/3숟가락
select * from ingTag;
select * from recipeTag;
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '오이고추');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '100g');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '된장'); -- 기존
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '1숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '고추장');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '1/3숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '마늘');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '1/2숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '올리고당');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '1/2숟가락');
insert into ingTag(tag_id, tag) values(SEQ_ingTag_tag_id.nextVal, '마요네즈');
insert into recipeTag(tag_id, rnum, quantity) values(tag_id, 8, '1숟가락');




-- 9번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '레몬블루베리팬케이크', '브런치 메뉴 추천 촉촉하고 새콤달콤한 레몬 블루베리 팬케이크', 30, 6 , '/imageThumb/pancakeThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 9, '/imageRecipe/pancake1.png', '냄비에 레몬 블루베리 콩포트 재료를 모두 넣고 저어가며 졸여요. 우유에 레몬즙을 넣어서 버터 밀크를 만들어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 9, '/imageRecipe/pancake2.png', '부추와 쪽파는 쫑쫑 썰어 담고 팬에 고추기름, 다진마늘, 돼지고기 다짐육을 넣고 볶다가 양념을 넣고 자작하게 볶아요.', 2);
insert into processImg(rnum, links, description, iseq) values( 9, '/imageRecipe/pancake3.png', '볼에 가루 재료를 모두 넣고 섞은 다음 달걀, 버터 밀크, 녹인 버터를 넣고 섞어요.', 3);
insert into processImg(rnum, links, description, iseq) values( 9, '/imageRecipe/pancake4.png', '블루베리를 넣고 가볍게 섞은 다음 팬에 오일을 두르고 반죽을 올려 앞뒤로 노릇하게 구워요.', 4);
-- 재료 : 밀가루중력분 2종이컵, 설탕 2+1/2숟가락, 베이킹파우더 2/3숟가락, 베이킹소다 1/4숟가락, 소금 1/3숟가락, 달걀 2개, 우유 180ml, 레몬즙 1숟가락, 녹인 무염버터 3숟가락, 블루베리 1종이컵, 오일 적당량 [레몬 블루베리 콩포트]​ 냉동 블루베리 2종이컵, 설탕 2/3종이컵, 레몬제스트 1숟가락, 레몬즙 3숟가락

-- 10번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '들깨떡만두국', '저는 버섯 잔뜩 넣은 들깨수제비를 정말 좋아해요! 먹으면서 떡사리와 만두를 넣으면더 든든하고 맛있겠다! 싶어서 만들어 본 레시피!', 20, 2 , '/imageThumb/sesameThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 10, '/imageRecipe/sesame1.jpg', '건표고버섯은 물에 불린 다음 얇게 썰고 대파는 어슷 썰어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 10, '/imageRecipe/sesame2.jpg', '냄비에 건표고버섯 불린 물 500ml, 생수 500ml, 만개한알을 넣고 끓여요.', 2);
insert into processImg(rnum, links, description, iseq) values( 10, '/imageRecipe/sesame3.jpg', '떡국떡, 만두, 건표고버섯을 넣고 끓인 다음 국간장, 다진마늘을 넣고 끓여요.', 3);
insert into processImg(rnum, links, description, iseq) values( 10, '/imageRecipe/sesame4.jpg', '대파, 후추를 넣고 끓여요. 들깨가루, 들기름을 넣은 다음 그릇에 담고 실고추를 올려 완성해요.', 4);
-- 재료 : 만두 6개, 떡국떡 2종이컵, 건표고버섯 4개, 대파 1/3대, 만개한알 2개, 국간장 1숟가락, 다진마늘 1/3숟가락, 후추 약간, 들깨가루 4숟가락, 들기름 1숟가락, 실고추 약간

-- 11번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '크림치즈떡', '쫀득한 식감에 크림치즈의 진한 풍미와 고소한 콩가루가 합쳐진 너무 쉬운 간식메뉴에요', 20, 6 , '/imageThumb/mochiThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 11, '/imageRecipe/mochi1.jpg', '크림치즈떡의 재료를 볼에 넣고 섞는다.', 1);
insert into processImg(rnum, links, description, iseq) values( 11, '/imageRecipe/mochi2.jpg', '냄비에 넣고 약불로 뭉칠 때까지 끓인다.', 2);
insert into processImg(rnum, links, description, iseq) values( 11, '/imageRecipe/mochi3.jpg', '덩어리가 충분히 뭉쳐지면 그릇에 랩을 씌워 반죽을 붓는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 11, '/imageRecipe/mochi4.jpg', '동그란 모양이 나오도록 랩을 밀봉하여 얼음물에 충분히 식힌다.', 4);
insert into processImg(rnum, links, description, iseq) values( 11, '/imageRecipe/mochi5.jpg', '랩 채로 냉장한 후 먹기 직전에 꿀, 콩가루를 뿌려 완성한다.', 5);
-- 재료 : 크림치즈 200g, 우유 200g, 전분 6숟가락, 설탕 4숟가락, 식초 2숟가락

-- 12번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '마늘쫑파스타', '제철 마늘쫑으로 고소한 건새우와 굴소스를 넣어 감칠맛을 살린 파스타를 만들어봤어요', 30, 4 , '/imageThumb/garlicpastaThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 12, '/imageRecipe/garlicpasta1.jpg', '마늘쫑은 4cm 길이로 썰고 마늘은 편으로 썬다.', 1);
insert into processImg(rnum, links, description, iseq) values( 12, '/imageRecipe/garlicpasta2.jpg', '비엔나소시지에 칼집을 넣는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 12, '/imageRecipe/garlicpasta3.jpg', '끓는 물에 소금을 넣고 면을 삶는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 12, '/imageRecipe/garlicpasta4.jpg', '달군 팬에 올리브유를 두르고 마늘, 소시지를 볶다가 마늘쫑, 건새우, 페퍼론치노를 넣는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 12, '/imageRecipe/garlicpasta5.jpg', '면과 면수를 약간 넣고 볶다가 간장, 굴소스를 넣고 소금, 후추로 간한다.', 5);
-- 재료 : 파스타면 2인분, 마늘종 10줄, 비엔나소시지 10개, 건새우 15g, 마늘 5알, 간장 1숟가락, 굴소스 2숟가락, 페페론치노 4개, 소금 약간, 후추 약간, 올리브유 2숟가락

-- 13번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '스리라차목살필라프', '도톰한 돼지고기 목살과 매콤 칠리소스로 맛을 낸 필라프 레시피를 소개해드릴게요', 30, 4 , '/imageThumb/srirachaThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 13, '/imageRecipe/sriracha1.jpg', '목살은 한입 크기로 썬다. 양파, 당근은 굵게 다지고 마늘은 편으로 썬다.', 1);
insert into processImg(rnum, links, description, iseq) values( 13, '/imageRecipe/sriracha2.jpg', '달군 팬에 식용유를 두르고 달걀후라이를 한다.', 2);
insert into processImg(rnum, links, description, iseq) values( 13, '/imageRecipe/sriracha3.jpg', '달군 팬에 식용유를 두르고 마늘을 볶다가 목살, 맛술, 간장을 넣고 볶는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 13, '/imageRecipe/sriracha4.jpg', '고기가 익으면 양파, 당근, 마늘을 넣고 함께 볶다가 밥, 굴소스, 칠리갈릭 스리라차 소스를 넣고 잘 섞은 후 소금, 후추로 간한다.', 4);
insert into processImg(rnum, links, description, iseq) values( 13, '/imageRecipe/sriracha5.jpg', '그릇에 담고 달걀후라이를 올린 후 파슬리 가루, 마요네즈, 스리라차 소스를 뿌려 완성한다.', 5);
-- 재료 : 돼지고기목살 150g, 양파 1/4개, 당근 1/4개, 마늘 6알, 밥 2공기, 맛술 1숟가락, 간장 1숟가락, 굴소스 1숟가락, 칠리갈릭 스리라차소스 2숟가락, 소금 약간, 후추 약간, 식용유 2숟가락, 달걀 2개, 마요네즈 1/2숟가락, 파슬리가루 약간

-- 14번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '청경채된장무침', '단돈 2천원으로 만들 수 있는 쉽고 빠른 밑반찬', 10, 3 , '/imageThumb/pakchoiThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 14, '/imageRecipe/pakchoi1.jpg', '씻은 청경채의 밑동을 자른다.', 1);
insert into processImg(rnum, links, description, iseq) values( 14, '/imageRecipe/pakchoi2.jpg', '끓는 소금물에 청경채를 데쳐 찬물에 헹군 후 물기를 짠다', 2);
insert into processImg(rnum, links, description, iseq) values( 14, '/imageRecipe/pakchoi3.jpg', '볼에 양념재료를 모두 넣고 섞는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 14, '/imageRecipe/pakchoi4.jpg', '청경채에 만들어둔 양념을 넣고 조물조물 무친다.', 4);
insert into processImg(rnum, links, description, iseq) values( 14, '/imageRecipe/pakchoi5.jpg', '그릇에 담고 통깨를 뿌려 완성한다.', 5);
-- 재료 : 청경채 200g, 소금 약간, 통깨 약간, 된장 1숟가락, 고춧가루 1/2숟가락, 매실액 1/3숟가락, 다진마늘 1/2숟가락, 참기름 1/2숟가락

-- 15번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '가지냉국', '가지를 전자레인지에 살짝 쪄서 간단하게 만드는 가지냉국', 30, 2 , '/imageThumb/coldeggplantsoupThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 15, '/imageRecipe/coldeggplantsoup1.jpg', '물에 국간장, 식초, 소금, 액젓을 넣고 섞어 냉장고에 차갑게 보관한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 15, '/imageRecipe/coldeggplantsoup2.jpg', '가지는 깨끗하게 세척 후 한입 크기로 길게 썬다. 오이는 가늘게 채썰고 고추는 송송 썬다.', 2);
insert into processImg(rnum, links, description, iseq) values( 15, '/imageRecipe/coldeggplantsoup3.jpg', '가지를 전자레인지에 4~5분 돌려 찐다.', 3);
insert into processImg(rnum, links, description, iseq) values( 15, '/imageRecipe/coldeggplantsoup4.jpg', '한김 식혀준 후 양념 재료를 넣고 조물조물 버무린다.', 4);
insert into processImg(rnum, links, description, iseq) values( 15, '/imageRecipe/coldeggplantsoup5.jpg', '차가워진 냉국에 양념된 가지와 오이, 고추를 넣고 섞는다.', 5);
-- 재료 : 가지 2개, 오이 1/2개, 청양고추 1개, 홍고추 1개, 물 600ml, 국간장 1숟가락, 식초 3숟가락, 소금 1/3숟가락, 액젓 1/2숟가락, 국간장 1/2숟가락, 다진마늘 1/2숟가락, 매실액 1숟가락, 통깨 1숟가락

-- 16번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '분홍소세지조림', '달달하면서 매콤해 밥반찬으로 딱인 소세지조림', 15, 3 , '/imageThumb/braisedsausageThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 16, '/imageRecipe/braisedsausage1.jpg', '소세지는 두툼하게 썰고 청양고추는 송송 썬다.', 1);
insert into processImg(rnum, links, description, iseq) values( 16, '/imageRecipe/braisedsausage2.jpg', '고추장, 굴소스, 고춧가루, 올리고당, 다진마늘, 참기름을 섞어 양념을 만든다', 2);
insert into processImg(rnum, links, description, iseq) values( 16, '/imageRecipe/braisedsausage3.jpg', '팬에 식용유를 두르고 소세지를 앞뒤로 굽는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 16, '/imageRecipe/braisedsausage4.jpg', '양념을 넣고 볶는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 16, '/imageRecipe/braisedsausage5.jpg', '물을 넣어 약한 불로 졸이듯 볶다가 청양고추를 넣고 볶는다.', 5);
-- 재료 : 소세지 300g, 청양고추 1개, 물 1종이컵, 식용유 적당량, [양념] 고추장 1숟가락, 굴소스 1숟가락, 고춧가루 1숟가락, 올리고당 1숟가락, 다진마늘 1/2숟가락, 참기름 1/2숟가락

-- 17번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '망고수박빙수', '달콤함이 더 좋은 망고수박으로 시원한 망고수박빙수 만들어보세요!', 120, 6 , '/imageThumb/mangowatermelonThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 17, '/imageRecipe/mangowatermelon1.jpg', '망고수박은 화채스쿱을 이용해 적당히 파낸다.', 1);
insert into processImg(rnum, links, description, iseq) values( 17, '/imageRecipe/mangowatermelon2.jpg', '남은 속은 숟가락을 이용해 깔끔하게 파낸 후 우유, 연유와 함께 믹서기에 갈아준다.', 2);
insert into processImg(rnum, links, description, iseq) values( 17, '/imageRecipe/mangowatermelon3.jpg', '갈아준 수박을 밀폐용기에 담아 냉동실에 넣고 3시간 이상 얼린다.', 3);
insert into processImg(rnum, links, description, iseq) values( 17, '/imageRecipe/mangowatermelon4.jpg', '수박통에 얼린 수박을 긁어 담고 스쿱으로 파낸 수박, 연유, 장식용 초콜릿을 올려 완성한다.', 4);
-- 재료 : 망고 수박 1/2통, 우유 1종이컵, 연유 90g, 초콜릿 또는 과자 약간

-- 18번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '초계김밥', '더위에 잃은 입맛을 살려줄 새콤달콤 초계김밥', 30, 1 , '/imageThumb/vinegargimbapThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 18, '/imageRecipe/vinegargimbap1.jpg', '밥에 단촛물을 넣고 섞어 간 한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 18, '/imageRecipe/vinegargimbap2.jpg', '볼에 겨자소스 재료를 섞은 후 닭가슴살에 넣어 무친다.', 2);
insert into processImg(rnum, links, description, iseq) values( 18, '/imageRecipe/vinegargimbap3.jpg', '오이는 길게 반을 갈라 얇게 어슷썰고 소금을 뿌려 살짝 절인 후 물에 헹궈 물기를 짠다.', 3);
insert into processImg(rnum, links, description, iseq) values( 18, '/imageRecipe/vinegargimbap4.jpg', '김에 밥을 펴 깔고 쌈무, 파프리카, 오이, 닭가슴살을 올려 돌돌 만다.', 4);
insert into processImg(rnum, links, description, iseq) values( 18, '/imageRecipe/vinegargimbap5.jpg', '참기름을 바르고 한 입 크기로 썰어 완성한다.', 5);
-- 재료 : 김밥용 김 2장, 현미밥 1+1/2공기, 닭가슴살통조림 1캔, 쌈무 10장, 파프리카 1/2개, 오이 1/4개, 참기름 약간, 연겨자 1숟가락, 설탕 1/2숟가락, 간장 1/2숟가락, 식초 1/2숟가락, 식초 1+1/2숟가락, 설탕 1숟가락, 소금 1/2숟가락

-- 19번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '양배추콥샐러드', '신선한 채소들을 한입 크기로 썰어 한끼 식사로도 좋은 샐러드 레시피', 15, 4 , '/imageThumb/cabbagesaladThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 19, '/imageRecipe/cabbagesalad1.jpg', '양배추, 라디치오, 방울토마토, 닭가슴살, 아보카도, 삶은 달걀은 한 입 크기로 썰어 준비해요.', 1);
insert into processImg(rnum, links, description, iseq) values( 19, '/imageRecipe/cabbagesalad2.jpg', '그릇에 준비한 재료를 가지런히 담아요.', 2);
insert into processImg(rnum, links, description, iseq) values( 19, '/imageRecipe/cabbagesalad3.jpg', '플레인 요거트를 재료 위에 뿌려요.', 3);
insert into processImg(rnum, links, description, iseq) values( 19, '/imageRecipe/cabbagesalad4.jpg', '슈레드 치즈를 올려 완성해요.', 4);
-- 재료 : 양배추 1/6개, 라디치오 1/4개, 방울토마토 6개, 닭가슴살 1개, 삶은 달걀 2개, 블랙올리브 1/2종이컵, 아보카도 1/2개, 플레인 요거트 1개, 슈레드치즈 1/2종이컵

-- 20번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '두부김밥', '밥 대신 두부로 만드는 두부김밥 레시피', 10, 1 , '/imageThumb/tofugimbapThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 20, '/imageRecipe/tofugimbap1.jpg', '두부는 면보로 물기를 제거한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 20, '/imageRecipe/tofugimbap2.jpg', '물기를 제거한 두부에 간장, 참기름, 깨를 넣고 으깨듯이 섞어 양념을 한다.', 2);
insert into processImg(rnum, links, description, iseq) values( 20, '/imageRecipe/tofugimbap3.jpg', '달걀을 고루 풀어 팬에 지단을 부친다.', 3);
insert into processImg(rnum, links, description, iseq) values( 20, '/imageRecipe/tofugimbap4.jpg', '팬에 기름을 살짝 둘러 양념한 두부를 볶아 수분을 날린다.', 4);
insert into processImg(rnum, links, description, iseq) values( 20, '/imageRecipe/tofugimbap5.jpg', '김>지단>두부>파프리카, 단무지, 우엉을 넣고 돌돌 말아 김밥을 완성한다.', 5);
-- 재료 : 김 2장, 두부 2모, 노랑 파프리카 1/2개, 빨간 파프리카 1/2개, 달걀 3개, 단무지 2줄, 우엉조림 4줄, 간장 1/2숟가락, 참기름 1숟가락, 깨 약간, 소금 약간, 식용유 적당량

-- 21번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '틈새게티볶음밥', '두 가지 라면이 만나 이색 볶음밥으로 재탄생!', 30, 4 , '/imageThumb/teumsaericeThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 21, '/imageRecipe/teumsaerice1.jpg', '대파는 송송 썰고 양파는 굵게 다진다.', 1);
insert into processImg(rnum, links, description, iseq) values( 21, '/imageRecipe/teumsaerice2.jpg', '지퍼백에 면을 넣고 잘게 부신다.', 2);
insert into processImg(rnum, links, description, iseq) values( 21, '/imageRecipe/teumsaerice3.jpg', '끓는 물에 면과 건더기스프를 넣고 익힌 후 물은 자작할 정도로만 남긴다.', 3);
insert into processImg(rnum, links, description, iseq) values( 21, '/imageRecipe/teumsaerice4.jpg', '달군 팬에 올리브유를 두르고 대파, 양파를 볶다가 면, 밥을 넣는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 21, '/imageRecipe/teumsaerice5.jpg', '스프를 넣고 잘 섞으며 볶다가 체다치즈를 올려 완성한다.', 5);
-- 재료 : 틈새 라면 1개, 짜파게티 1개, 밥 1+1/2공기, 양파 1/6개, 대파 1/2대, 체다치즈 2장, 올리브유 약간

-- 22번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '함박스테이크 브리또', '또띠아 속에 도톰한 함박스테이크와 볶음밥, 치즈가 한번에!', 30, 4 , '/imageThumb/hamburgburritoThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 22, '/imageRecipe/hamburgburrito1.jpg', '파프리카, 양파는 굵게 다진다.', 1);
insert into processImg(rnum, links, description, iseq) values( 22, '/imageRecipe/hamburgburrito2.jpg', '달군 팬에 기름을 넉넉히 두르고 함박스테이크를 구운 후 적당한 크기로 자른다.', 2);
insert into processImg(rnum, links, description, iseq) values( 22, '/imageRecipe/hamburgburrito3.jpg', '다른 팬에 기름을 두르고 양파, 파프리카를 볶다가 밥, 소스, 마요네즈, 후추를 넣고 섞는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 22, '/imageRecipe/hamburgburrito4.jpg', '또띠아에 체다치즈(2장)>볶은 밥>함박스테이크>청상추(2장)>피자치즈(1숟가락)를 올려 돌돌 만다.', 4);
insert into processImg(rnum, links, description, iseq) values( 22, '/imageRecipe/hamburgburrito5.jpg', '마른 팬에 치즈가 녹을 정도로만 구워 완성한다.', 5);
-- 재료 : 또띠아 2장, 함박 스테이크 2개, 데미그라스 소스 2개, 마요네즈 2숟가락, 밥 1+1/2공기, 파프리카 1/4개, 양파 1/4개, 청상추 4장, 후추 약간, 체다치즈 4장, 피자치즈 2숟가락

-- 23번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '새우잣소스무침', '오동통한 새우로 손님맞이 고급 반찬 만들기', 20, 3 , '/imageThumb/shrimpnutThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 23, '/imageRecipe/shrimpnut1.jpg', '물에 청주를 넣고 끓으면 새우를 넣어 살짝 데친다.', 1);
insert into processImg(rnum, links, description, iseq) values( 23, '/imageRecipe/shrimpnut2.jpg', '어슷 썬 오이는 소금을 살짝 뿌려 절인 후 물기를 제거한다.', 2);
insert into processImg(rnum, links, description, iseq) values( 23, '/imageRecipe/shrimpnut3.jpg', '밤, 사과는 편 썬다.', 3);
insert into processImg(rnum, links, description, iseq) values( 23, '/imageRecipe/shrimpnut4.jpg', '절구에 잣을 넣고 빻다가 연겨자, 설탕, 소금, 식초, 물을 넣고 섞는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 23, '/imageRecipe/shrimpnut5.jpg', '새우, 오이, 밤, 사과에 잣소스를 넣고 가볍게 무친다.', 5);
-- 재료 : 새우 14마리, 오이 1/2개, 밤 4알, 사과 1/4개, 청주 1숟가락, 잣 1/2종이컵, 연겨자 1/2숟가락, 설탕 1/2숟가락,소금 약간, 식초 1숟가락, 물 2숟가락

-- 24번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '콩나물굴국', '통통한 굴로 만드는 콩나물 굴국, 뜨끈한 굴국밥 시원하게 끓이는 법', 30, 2 , '/imageThumb/beansproutsoupThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 24, '/imageRecipe/beansproutsoup1.jpg', '굴은 소금물에 흔들어 씻어 준비한다.콩나물은 씻어서 준비하고, 무는 0.5cm x 4cm 정도로 나박썰기한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 24, '/imageRecipe/beansproutsoup2.jpg', '대파는 어슷하게 썬다.청양고추, 홍고추는 송송 썬다.', 2);
insert into processImg(rnum, links, description, iseq) values( 24, '/imageRecipe/beansproutsoup3.jpg', '냄비에 멸치다시마육수, 무를 넣고 끓인다. 팔팔 끓으면 콩나물을 넣고 5분 정도 끓인다.', 3);
insert into processImg(rnum, links, description, iseq) values( 24, '/imageRecipe/beansproutsoup4.jpg', '다진마늘, 맛술, 국간장, 새우젓, 후추를 넣는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 24, '/imageRecipe/beansproutsoup5.jpg', '굴, 대파, 청양고추, 홍고추를 넣고 한소끔 더 끓여 완성한다.', 5);
-- 재료 : 굴 200g, 콩나물 150g, 무 100g, 대파 1/2대, 청양고추 1개, 홍고추 1개, 다진마늘 1/2숟가락, 맛술 1숟가락, 국간장 1숟가락, 새우젓 1숟가락, 후추 약간, 멸치다시마육수 5종이컵

-- 25번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '애호박 오코노미야끼', '새우, 베이컨을 토핑으로 넣고 오코노미야끼소스를 잔뜩 뿌리면 야채를 안먹는 아이들도 좋아해요!', 30, 4 , '/imageThumb/okonomiyakiThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 25, '/imageRecipe/okonomiyaki1.png', '양배추는 채 썰고 애호박은 멀티 필러를 이용해 얇고 길게 채 썰어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 25, '/imageRecipe/okonomiyaki2.png', '쪽파는 송송 썰고 새우와 베이컨은 작게 썰어요.', 2);
insert into processImg(rnum, links, description, iseq) values( 25, '/imageRecipe/okonomiyaki3.png', '부침가루, 물, 달걀 1개를 넣고 섞은 다음 애호박, 양배추, 쪽파,새우,베이컨을 넣고 섞어요.', 3);
insert into processImg(rnum, links, description, iseq) values( 25, '/imageRecipe/okonomiyaki4.jpg', '팬에 식용유를 두르고 반죽을 올려서 앞뒤로 익히고 슬라이스치즈를 올려요.', 4);
insert into processImg(rnum, links, description, iseq) values( 25, '/imageRecipe/okonomiyaki5.jpg', '오코노미야끼 소스, 마요네즈, 가쓰오부시, 쪽파를 올려 완성해요.', 5);
-- 재료 : 애호박 2/3개, 양배추 약간, 칵테일새우 6마리, 베이컨 2줄, 슬라이스치즈 2장, 부침가루 1종이컵,물 1종이컵, 달걀 1개, 쪽파 3~4줄기, 오코노미야끼소스 적당량, 마요네즈 적당량, 가쓰오부시 적당량

-- 26번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '통마늘은행볶음', '노릇노릇 최고의 맥주 도둑, 통마늘 은행 볶음', 10, 3 , '/imageThumb/ginkgoThumb.png');
insert into processImg(rnum, links, description, iseq) values( 26, '/imageRecipe/ginkgo1.png', '볼에 마늘을 담고 전자레인지에서 30초 익힌다.', 1);
insert into processImg(rnum, links, description, iseq) values( 26, '/imageRecipe/ginkgo2.png', '팬에 버터를 녹이고 마늘을 약불로 볶는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 26, '/imageRecipe/ginkgo3.png', '마늘이 노릇해지면 은행을 넣고 볶는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 26, '/imageRecipe/ginkgo4.png', '은행이 초록빛을 띄면 소금으로 간한다.', 4);
insert into processImg(rnum, links, description, iseq) values( 26, '/imageRecipe/ginkgo5.png', '노릇하게 볶아 완성한다.', 5);
-- 재료 : 깐 은행 1종이컵, 마늘 15알, 버터 1/2숟가락, 소금 약간

-- 27번 레시피 
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '된장비빔국수', '구수하고 짭짤한 맛에 감칠맛이 최고인 된장비빔국수 레시피', 15, 4 , '/imageThumb/doenjangnoodleThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 27, '/imageRecipe/doenjangnoodle1.png', '끓는물에 소면을 삶아 찬물에 헹궈 물기를 뺀다.', 1);
insert into processImg(rnum, links, description, iseq) values( 27, '/imageRecipe/doenjangnoodle2.png', '깻잎은 돌돌 말아 채썰고 홍고추는 송송 썬다.', 2);
insert into processImg(rnum, links, description, iseq) values( 27, '/imageRecipe/doenjangnoodle3.png', '분량의 양념 재료를 섞어 된장소스를 만든다.', 3);
insert into processImg(rnum, links, description, iseq) values( 27, '/imageRecipe/doenjangnoodle4.png', '삶은 면에 된장소스를 넣고 비빈다.', 4);
insert into processImg(rnum, links, description, iseq) values( 27, '/imageRecipe/doenjangnoodle5.png', '깻잎채, 홍고추, 통깨를 뿌려 완성한다.', 5);
-- 재료 : 소면 1인분, 깻잎 2장, 통깨 살짝, 홍고추 1개, 된장 1숟가락, 고추장 1/2숟가락, 간장 1숟가락, 물엿 1숟가락, 설탕 1/2숟가락, 참기름 1숟가락, 다진마늘 1숟가락

-- 28번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '마른미역자반', '10분도 안걸리는 초간단 밑반찬 맛있는 마른 미역자반 만드는 법', 10, 3 , '/imageThumb/driedseaweedThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 28, '/imageRecipe/driedseaweed1.jpg', '팬에 들기름, 식용유를 두르고 달군다.', 1);
insert into processImg(rnum, links, description, iseq) values( 28, '/imageRecipe/driedseaweed2.jpg', '기름이 달궈지면 마른 미역을 넣고 튀기듯 볶는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 28, '/imageRecipe/driedseaweed3.jpg', '미역이 바삭해지면 키친타월에 펼친다.', 3);
insert into processImg(rnum, links, description, iseq) values( 28, '/imageRecipe/driedseaweed4.jpg', '튀긴 미역에 설탕, 간 깨를 넣고 가볍게 섞는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 28, '/imageRecipe/driedseaweed5.jpg', '밥 위에 솔솔 뿌려먹으면 이게 바로 밥도둑!', 5);
-- 재료 : 마른 미역 40g, 들기름 2숟가락, 식용유 2숟가락, 설탕 1숟가락, 간 깨 1숟가락

-- 29번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '해물볶음짬뽕', '오늘은 흔하게 먹는 국물있는 짬뽕이 아닌 국물 없이 해물, 채소를 넣고 얼큰하게 볶은 볶음짬뽕 레시피 가져왔어요~', 30, 4 , '/imageThumb/chinesenoodleThumb.png');
insert into processImg(rnum, links, description, iseq) values( 29, '/imageRecipe/chinesenoodle1.jpg', '대파는 송송 썰고 홍고추는 반을 갈라 씨를 빼내고 어슷 썰어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 29, '/imageRecipe/chinesenoodle2.jpg', '끓는 물에 중화면을 삶고 찬물에 헹궈요.', 2);
insert into processImg(rnum, links, description, iseq) values( 29, '/imageRecipe/chinesenoodle3.jpg', '식용유를 두른 팬에 대파>양파>양배추, 표고버섯, 다진마늘 순으로 볶아요.', 3);
insert into processImg(rnum, links, description, iseq) values( 29, '/imageRecipe/chinesenoodle4.jpg', '간장, 고춧가루를 넣고 볶은 다음 해물믹스, 새우를 넣고 볶아요. 닭육수, 굴소스, 설탕, 후추를 넣고 볶아요.', 4);
insert into processImg(rnum, links, description, iseq) values( 29, '/imageRecipe/chinesenoodle5.jpg', '면, 청경채, 홍고추를 넣고 볶아요.', 5);
-- 재료 : 중화면 1인분, 해물믹스 1줌, 칵테일새우 4마리, 양배추 1/8통, 청경채 2개, 홍고추1개, 양파 1/4개, 건표고버섯 1개, 대파 1/2줄, 식용유 3숟가락, 닭육수 1종이컵

-- 30번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '참치미역국', '고기 없이 쉽고 맛있게 미역국 끓이는 방법', 30, 2 , '/imageThumb/tunaseaweedThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 30, '/imageRecipe/tunaseaweed1.jpg', '미역은 물에 불려 준비한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 30, '/imageRecipe/tunaseaweed2.jpg', '참치통조림은 체에 밭쳐 기름을 빼고 기름은 남겨둔다.', 2);
insert into processImg(rnum, links, description, iseq) values( 30, '/imageRecipe/tunaseaweed3.jpg', '달군 냄비에 참치통조림의 기름, 들기름을 두르고 불린 미역을 볶는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 30, '/imageRecipe/tunaseaweed4.jpg', '국간장을 넣고 볶다가 물을 넣어 끓인다.', 4);
insert into processImg(rnum, links, description, iseq) values( 30, '/imageRecipe/tunaseaweed5.jpg', '물이 끓으면 다진마늘, 참치 통조림을 넣고 끓인다. 액젓으로 간을 맞춰 완성한다.', 5);
-- 재료 : 자른미역 10g, 참치통조림 1캔, 다진마늘 1숟가락, 국간장 2숟가락, 액젓 1숟가락, 들기름 1숟가락, 물 6종이컵

-- 31번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '깐쇼달걀', '삶은 달걀에 라이스페이퍼를 감싸서 노릇하게 굽고 매콤 달달한 양념에 버무린 다음 치즈까지~~~', 30, 3 , '/imageThumb/ganshaoThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 31, '/imageRecipe/ganshao1.jpg', '물에 적신 라이스페이퍼에 삶은 달걀을 올려 감싼다.', 1);
insert into processImg(rnum, links, description, iseq) values( 31, '/imageRecipe/ganshao2.jpg', '팬에 기름을 두르고 달걀을 지진다.', 2);
insert into processImg(rnum, links, description, iseq) values( 31, '/imageRecipe/ganshao3.jpg', '팬에 양념재료를 모두 넣고 살짝 끓인다.', 3);
insert into processImg(rnum, links, description, iseq) values( 31, '/imageRecipe/ganshao4.jpg', '지진 달걀을 넣고 양념을 골고루 버무린다.', 4);
insert into processImg(rnum, links, description, iseq) values( 31, '/imageRecipe/ganshao5.jpg', '체다치즈를 올리고 파슬리가루를 뿌려 완성한다.', 5);
-- 재료 : 삶은 달걀 6개, 라이스페이퍼 6장, 체다치즈 1+1/2장, 파슬리가루 약간, 식용유 약간, 간장 1숟가락, 식초 1숟가락, 케첩 2숟가락, 고춧가루 1숟가락, 설탕 1숟가락, 물 4숟가락

-- 32번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '고구마치즈빵', '달달한 찐고구마와 치즈를 밀가루 반죽으로 싸고 밀어서 후라이팬에 굽기만 하면 완성!', 60, 6 , '/imageThumb/sweetpotatoThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 32, '/imageRecipe/sweetpotato1.jpg', '볼에 밀가루, 우유, 녹인 버터, 소금을 넣고 반죽한다.', 1);
insert into processImg(rnum, links, description, iseq) values( 32, '/imageRecipe/sweetpotato2.jpg', '반죽이 한 덩어리가 되면 손으로 치댄다.', 2);
insert into processImg(rnum, links, description, iseq) values( 32, '/imageRecipe/sweetpotato3.jpg', '볼에 고구마를 으깬 후 마요네즈, 꿀을 넣고 섞는다. 반죽을 밀대로 펼친 후 치즈, 고구마반죽을 올린다.', 3);
insert into processImg(rnum, links, description, iseq) values( 32, '/imageRecipe/sweetpotato4.png', '반죽의 가장자리를 모아서 붙이고 밀대로 펼친다.', 4);
insert into processImg(rnum, links, description, iseq) values( 32, '/imageRecipe/sweetpotato5.jpg', '팬에 버터를 녹인 후 반죽을 앞뒤로 노릇하게 굽는다.', 5);
-- 재료 : 찐 고구마 200g, 마요네즈 2숟가락, 꿀 1숟가락, 모짜렐라 치즈 120g, 버터 2숟가락, 밀가루 강력분 100g, 우유 1/3종이컵, 녹인 버터 10g, 소금 1꼬집

--33번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '동태감자 오픈샌드위치', '탄,단,지 골고루 들어가 있어 든든한 한끼로 정말 좋아요!', 30, 4 , '/imageThumb/pollacksandwichThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 33, '/imageRecipe/pollacksandwich1.jpg', '냄비에 우유, 생크림, 동태, 오레가노를 넣고 약불에서 끓여요.', 1);
insert into processImg(rnum, links, description, iseq) values( 33, '/imageRecipe/pollacksandwich2.jpg', '삶은 감자를 으깬 다음 1과 버터를 넣고 다시 으깨요.', 2);
insert into processImg(rnum, links, description, iseq) values( 33, '/imageRecipe/pollacksandwich3.jpg', '소금, 후추를 넣어 간을 맞춰요.', 3);
insert into processImg(rnum, links, description, iseq) values( 33, '/imageRecipe/pollacksandwich4.jpg', '구운 빵 위에 동태감자를 올리고 올리브유를 뿌려요.', 4);
insert into processImg(rnum, links, description, iseq) values( 33, '/imageRecipe/pollacksandwich5.jpg', '샐러드 채소를 올린 다음 그라나파다노 치즈를 갈아 올려서 완성해요.', 5);
-- 재료 : 삶은감자 3개, 동태살 200g, 무염버터 1T, 생크림 70ml, 우유 90ml, 소금 약간, 후추 약간, 오레가노 1꼬집, 올리브오일 약간, 샐러드채소 1줌, 그라나파다노 약간, 빵 적당량

-- 34번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '회오리 오이무침', '오이가 스프링처럼 쭉쭉 늘어나 먹는 재미도 있고 양념이 칼집 사이사이로 잘 배어져 더욱 맛있어요!', 15, 3 , '/imageThumb/tornadocucumberThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 34, '/imageRecipe/tornadocucumber1.jpg', '오이는 꼭지를 잘라낸 다음 바닥에 나무젓가락 대고 대각선으로 칼집 내고, 오이를 반대로 돌려 똑같이 칼집을 내고 먹기 좋은 크기로 썰어요.', 1);
insert into processImg(rnum, links, description, iseq) values( 34, '/imageRecipe/tornadocucumber2.jpg', '소금을 넣고 10분간 절인 다음 물에 헹구고 물기를 제거해요.', 2);
insert into processImg(rnum, links, description, iseq) values( 34, '/imageRecipe/tornadocucumber3.jpg', '양파, 대파는 굵게 다져요.', 3);
insert into processImg(rnum, links, description, iseq) values( 34, '/imageRecipe/tornadocucumber4.jpg', '다진 양파와 대파, 양념 재료를 함께 섞어요.', 4);
insert into processImg(rnum, links, description, iseq) values( 34, '/imageRecipe/tornadocucumber5.jpg', '오이를 넣고 양념과 버무린 다음 통깨를 넣고 무쳐 완성해요.', 5);
-- 재료 : 오이 2개, 소금 2/3숟가락, 양파 1/6개, 대파 1/4줄기, 통깨 약간, 고춧가루 3숟가락, 소금 1/4숟가락, 설탕 2/3숟가락, 매실액 1숟가락, 다진마늘 1숟가락, 식초 2숟가락, 참기름 1숟가락

-- 35번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '닭가슴살 시금치 스테이크', '단백질 가득 닭가슴살과 영양소 가득 시금치가 잘 어울리는 홈스토랑 메뉴', 30, 4 , '/imageThumb/chickensteakThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 35, '/imageRecipe/chickensteak1.jpg', '달군 팬에 올리브유를 두르고 시금치 숨이 살짝 죽을 정도로 볶는다.', 1);
insert into processImg(rnum, links, description, iseq) values( 35, '/imageRecipe/chickensteak2.jpg', '볼에 시금치, 크림치즈를 넣고 섞는다.', 2);
insert into processImg(rnum, links, description, iseq) values( 35, '/imageRecipe/chickensteak3.jpg', '닭가슴살에 칼집을 낸 후 사이사이 섞은 재료를 넣는다.', 3);
insert into processImg(rnum, links, description, iseq) values( 35, '/imageRecipe/chickensteak4.jpg', '소금, 후추, 슈레드체다치즈, 케이엔페퍼를 뿌린다.', 4);
insert into processImg(rnum, links, description, iseq) values( 35, '/imageRecipe/chickensteak5.jpg', '에어프라이어에 넣고 180도에서 10분, 170도에서 10분 굽는다.', 5);
-- 재료 : 닭가슴살 2덩이, 시금치 1/2단, 크림치즈 50g, 슈레드 체다치즈 2숟가락, 케이엔페퍼 약간, 소금 약간, 후추 약간, 올리브유 약간

-- 36번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '팽이버섯장조림', '꼬독꼬독 씹히는 맛이 너무 좋아요~ 청양고추를 빼면  달달하면서 짭짤해 아이들도 먹을 수 있어요!', 15, 3 , '/imageThumb/enokiThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 36, '/imageRecipe/enoki1.png', '세척한 팽이버섯은 밑동을 자른 후 먹기 좋게 찢는다.', 1);
insert into processImg(rnum, links, description, iseq) values( 36, '/imageRecipe/enoki2.png', '청양고추는 송송 썰고 양파는 다진다.', 2);
insert into processImg(rnum, links, description, iseq) values( 36, '/imageRecipe/enoki3.png', '팬에 간장, 설탕, 물을 1:1:1 비율로 넣고 끓인다.', 3);
insert into processImg(rnum, links, description, iseq) values( 36, '/imageRecipe/enoki4.png', '끓는 간장물에 팽이버섯과 양파, 청양고추를 넣고 끓인다.', 4);
insert into processImg(rnum, links, description, iseq) values( 36, '/imageRecipe/enoki5.png', '국물이 자작할 정도로 졸인다.', 5);
-- 재료 : 팽이버섯 600g, 양파 1/2개, 청양고추 2개, 간장 2/3종이컵, 설탕 2/3종이컵, 물 2/3종이컵

-- 37번 레시피
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'somi', '시나몬 큐브 프렌치 토스트', '시나몬 향이 진하게 나는 촉촉한 프렌치토스트를 커피랑 함께~', 20, 6 , '/imageThumb/frenchtoastThumb.jpg');
insert into processImg(rnum, links, description, iseq) values( 37, '/imageRecipe/frenchtoast1.png', '통식빵을 적당한 크기의 큐브모양으로 썬다.', 1);
insert into processImg(rnum, links, description, iseq) values( 37, '/imageRecipe/frenchtoast2.png', '계란, 우유, 설탕, 소금, 시나몬파우더를 섞어 체에 한번 내린다.', 2);
insert into processImg(rnum, links, description, iseq) values( 37, '/imageRecipe/frenchtoast3.png', '계란물에 식빵을 넣고 골고루 적신다.', 3);
insert into processImg(rnum, links, description, iseq) values( 37, '/imageRecipe/frenchtoast4.png', '팬에 버터를 녹여 토스트를 노릇하게 굽는다.', 4);
insert into processImg(rnum, links, description, iseq) values( 37, '/imageRecipe/frenchtoast5.png', '좋아하는 과일을 올리고 슈가파우더를 뿌려 완성한다.', 5);
-- 재료 : 큐브식빵 or 통식빵 1개, 우유 1종이컵, 계란 3개, 설탕 1숟가락, 시나몬파우더 1/2숟가락, 소금 1꼬집, 버터 2조각, 슈가파우더 약간






