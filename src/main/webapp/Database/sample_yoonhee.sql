-- 시퀀스 자동 생성
create sequence recipe_seq increment by 1 start with 1;

-- 레시피 섬네일 추가
alter table recipe add thumbnail varchar2(100) not null;

-- 기본 틀
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '제목', '내용', 소요시간, 종류(번호), '썸네일 경로');
insert into processImg(rnum, links, description, iseq) values ( rnum, '경로', '요리 과정 묘사', 1);

-- 221228 재료는 아직 보류
insert into ingredient(iseq, iname, quantity) values( 순서 번호, '재료 이름', 재료 양);

-- 레시피 1 '감자조림'
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '감자조림', '요리 초보도 따라 할 수 있는 요리 바이블 만개백과 그대로만 따라하면 실패할일 없어요',10, 1, 'imageThumb/potatoes thumb.jpg');
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/potatoes1.jpg', '감자는 껍질을 벗겨 한입 크기로 썰어서 준비해요', 1);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/potatoes2.jpg', '팬에 식용유를 두르고 감자를 약 2~3분간 볶아요', 2);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/potatoes3.jpg', '감자가 익으면 물을 2/3종이컵 붓고 간장,설탕, 다진마늘을 넣어 약10분간 조려요. ', 3);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/potatoes4.jpg', '불을 끄고 참기름, 참깨를 넣어 버무려 완성해요', 4);
-- 재료에 대한 내용 간단히 쓰기
-- 간장 4숟가락, 설탕 1숟가락, 물엿 2숟가락, 다진 마늘 1/2숟가락, 참깨 1/2숟가락, 참기름 1숟가락

-- 레시피 2 '배추말이지짐'
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '배추말이지짐' '고소한 알배기 배추를 돌돌 말아 양념장이랑 노릇하게 지져먹어요~ 배추의 달달한 맛이 더해져 밥도둑이 따로 없답니다!',20,2, 'imageThumb/cabbage rolls thumb.jpg');
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/cabbage rolls1.jpg','찜기에 김이 오르면 배춧잎을 올려 약 3~5분 찐다',1);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/cabbage rolls2.jpg','대파, 홍고추, 청양고추 볼에 양념재료를 모두 넣고 섞는다',2);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/cabbage rolls4.jpg','찐 배추잎을 돌돌 말아준다',3);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/cabbage rolls5.jpg','팬에 기름을 살짝 두르고 돌돌 만 배추를 노릇하게 지진다.',4);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/cabbage rolls6.jpg','양념을 배추 위에 뿌리고 조금 더 익힌다.',5);
-- 재료: 알배기배추 1/2통, 식용유 약간
-- 홍고추 1/2개, 청양고추 1개, 대파 1/2대, 다진마늘 1숟가락, 간장 2숟가락, 설탕 1/2숟가락, 물 2숟가락, 참기름 1숟가락, 통깨 1숟가락


-- 레시피 3 '달걀장조림'
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '달걀장조림', '고기 장조림이 어렵다고 생각되신다면 달걀 장조림 부터 만들어보세요! 요린이들의 길잡이 백파더가 알려주는 달걀 장조림 레시피입니다. 밥 한공기 뚝딱! 하는 밥반찬으로 만들어보세요~',30,3, 'imageThumb/cabbage rolls thumb.png');
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/eggs in soy broth1.png','달걀은 냄비에 담아 달걀이 잠길 정도로 물을 넣고 식초와 소금을 넣어 약 14분간 삶아요',1);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/eggs in soy broth2.png','달걀이 익는 동안 꽈리고추, 청양고추는 먹기 좋게 썰어요',2);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/eggs in soy broth3.png','달걀은 껍질을 벗겨 준비해요',3);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/eggs in soy broth4.png','냄비에 물, 간장, 설탕, 달걀, 마늘을 넣어요. 끓어오르면 다시마와 꽈리고추, 청양고추를 넣어 섞어주세요',4);
insert into processImg(rnum, links, description, iseq) values( rnum, '/imageRecipe/eggs in soy broth5.png','끓어오르면 불을 꺼서 완성해요',5);
-- 재료
-- 달걀 7개, 청양고추 2개, 꽈리고추 10개, 통마늘 5개, 간장 1/2종이컵, 물 2종이컵, 설탕 1/3종이컵, 다시마, 식초 1숟가락, 소금 1/5숟가락

-- 레시피 4 '온묵밥'
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '온묵밥', '여름에는 얼음 동동 띄운 육수로 냉묵밥을 즐겼다면 따끈한 육수로 온몸 따뜻하게 데워주는 온묵밥 만들어보세요! 탱글~쫀득한 도토리묵과 조물조물 무친김치를 더해 따뜻한 육수를 부어주면 따뜻하고 든든한 한그릇 음식이 완성돼요~',20,4, 'imageThumb/wram muk rice thumb.jpg');
insert into processImg(rnum, links, description, iseq) values( rnum,'/imageRecipe/wram muk rice1.jpg','대파는 송송 썰고 도토리묵은 길게 썬다',1);
insert into processImg(rnum, links, description, iseq) values( rnum,'/imageRecipe/wram muk rice2.jpg','잘게 썬 김치에 양념 재료를 넣고 섞는다',2);
insert into processImg(rnum, links, description, iseq) values( rnum,'/imageRecipe/wram muk rice3.jpg','냄비에 멸치다시마육수를 끓인뒤 국간장을 넣는다',3);
insert into processImg(rnum, links, description, iseq) values( rnum,'/imageRecipe/wram muk rice4.jpg','그릇에 밥, 도토리묵, 김치, 대파, 김가루를 올려 완성한다',4);
-- 재료 
-- 도토리묵 1모, 대파 1대, 김치 1종이컵, 국간장 1/2숟가락, 김가루 약간, 설탕 1/2숟가락, 참기름 1/2숟가락, 깨소금 약간
-- 멸치 1/2줌, 다시마 2장, 물 5종이컵




