-- 기본 틀
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '제목', '내용', 소요시간, 종류(번호), '썸네일 경로');
insert into processImg values( rnum, '경로', '요리 과정 묘사', 1);
insert into ingredient(iseq, iname, quantity) values( 순서 번호, '재료 이름', 재료 양);


--1
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','도시락에 빠질 수 없는 유부초밥 레시피 모음','달달하면서 짭짤한 베스트 도시락 메뉴!어떤 재료를 추가해도 맛있는 유부초밥
더 맛있게 즐길 수 있는 특별한 레시피를 소개할게요~',10,1,'/imageThumb/thumb1.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p1_1.jpg','냉동 (or 훈제)닭가슴살을 삶아서 찢어준다',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p1_2.jpg','두부를 물에 데쳐서',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p1_3.jpg','버물버물/ 잘 두부랑 닭가슴살이 섞이도록 무쳐주세요',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p1_4.jpg','다시 버물려줍니다',rnum)
insert into processImg(iseq, link, description, rnum) values (5,'/imageRecipe/p1_5.jpg','완성!',rnum)
--닭가슴살 300g, 두부 1/2개, 유부 1개(2~3인용), 소금 1t, 후추 1/2t

--2
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','전참시 유병재가 만든 찜닭! 꽈리고추닭볶음','맛남의 광장 백종원 선생님의 레시피를
유병재 스타일로 간단하게 만든 닭볶음! 닭볶음탕보다 만들기도 쉽고,단짠단짠 양념도 잘 배어있어서 밥반찬으로도 너무 좋아요 :)',5,1,'/imageThumb/thumb2.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p2_1.jpg','팬에 기름을 두르고 닭을 굽듯이 볶고 송송 썬 대파를 넣고 함께 볶는다.',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p2_2.jpg','가운데에 자리를 만들어 간장을 넣고 볶는다.',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p2_3.jpg','물, 다진마늘을 넣고 국물이 자작할 때까지 졸인다.',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p2_4.jpg','꽈리고추를 넣고 빠르게 볶은 후 불을 끈다.',rnum)
insert into processImg(iseq, link, description, rnum) values (5,'/imageRecipe/p2_5.jpg','완성!',rnum)
--닭볶음탕용 닭 1kg, 꽈리고추 150g, 대파 1/2대, 물 2종이컵, 간장 1/4종이컵, 설탕 2숟가락, 다진마늘 1숟가락, 후추 약간, 통깨 약간, 식용유 적당량

--3
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','다른 반찬 필요 없음! 맛남의광장 백종원의 다시마쌈장','작년 이맘 때쯤 맛남의광장에서 소개한 다시마쌈장!
더운 여름 입맛 없을 때 다시마쌈장을 탁! 밥에 쓱싹~ 비벼 먹으면 한 그릇 뚝딱 가능 다른 반찬이 필요 없어요~',5,1,'/imageThumb/thumb3.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p3_1.jpg','쌈 다시마는 찬물에 약 10분 이상 담가 소금기를 빼고 다시마, 양파, 애호박, 청양고추는 다지고 대파는 송송 썰어 준비해요.',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p3_2.jpg','예열된 팬에 기름을 두르고 다시마와 다진 채소를 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p3_3.jpg','재료가 볶아지면 고춧가루, 설탕, 다진 마늘, 홍고추를 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p3_4.jpg','재래식 된장, 고추장을 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (5,'/imageRecipe/p3_5.jpg','완성!',rnum)
--쌈 다시마 140g, 애호박 1/4개, 양파 1/2개, 대파 1/2대, 청양고추 1개, 홍고추 1개, 고춧가루 1/2숟가락, 설탕 1/2숟가락, 다진 마늘 1숟가락, 
--재래식 된장 4숟가락, 고추장 1숟가락, 참기름 1숟가락


--3
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','다른 반찬 필요 없음! 맛남의광장 백종원의 다시마쌈장','작년 이맘 때쯤 맛남의광장에서 소개한 다시마쌈장!
더운 여름 입맛 없을 때 다시마쌈장을 탁! 밥에 쓱싹~ 비벼 먹으면 한 그릇 뚝딱 가능 다른 반찬이 필요 없어요~',15,1,'/imageThumb/thumb3.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p3_1.jpg','쌈 다시마는 찬물에 약 10분 이상 담가 소금기를 빼고 다시마, 양파, 애호박, 청양고추는 다지고 대파는 송송 썰어 준비해요.',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p3_2.jpg','예열된 팬에 기름을 두르고 다시마와 다진 채소를 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p3_3.jpg','재료가 볶아지면 고춧가루, 설탕, 다진 마늘, 홍고추를 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p3_4.jpg','재래식 된장, 고추장을 넣어 볶아요.',rnum)
insert into processImg(iseq, link, description, rnum) values (5,'/imageRecipe/p3_5.jpg','완성!',rnum)
--쌈 다시마 140g, 애호박 1/4개, 양파 1/2개, 대파 1/2대, 청양고추 1개, 홍고추 1개, 고춧가루 1/2숟가락, 설탕 1/2숟가락, 다진 마늘 1숟가락, 
--재래식 된장 4숟가락, 고추장 1숟가락, 참기름 1숟가락

--4
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','콩국수가 싫다면 잣국수','시원한 여름국수요리! 콩국수의 콩비린내 때문에 싫어하는 분들도 있죠?!
잣을 갈아 만들면 고소하지만, 비린내는 Nope 더위 쫒는 시원한 여름국수 만들고 시원한 여름 보내세요!',20,1,'/imageThumb/thumb4.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p4_1.jpg','오이는 채 썬다.',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p4_2.jpg','믹서에 잣, 닭 육수, 소금을 넣고 곱게 간 후 체에 내린다.',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p4_3.jpg','끓는 물에 소금을 넣고 소면을 넣어 삶는다..',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p4_4.jpg','그릇에 삶은 소면을 담고 잣 국물을 넉넉히 부은 후 채 썬 오이를 올려 완성한다.',rnum)
--잣 150g, 닭 육수 2+1/2종이컵, 소면 1인분, 오이 1/3개, 소금 약간


--3
insert into recipe(rnum, id, subject, content, time, type,thumbnail) 
values(recipe_seq.nextVal,'scott','콩국수가 싫다면 잣국수','시원한 여름국수요리! 콩국수의 콩비린내 때문에 싫어하는 분들도 있죠?!
잣을 갈아 만들면 고소하지만, 비린내는 Nope 더위 쫒는 시원한 여름국수 만들고 시원한 여름 보내세요!',20,1,'/imageThumb/thumb4.jpg');

insert into processImg(iseq, link, description, rnum) values (1,'/imageRecipe/p4_1.jpg','오이는 채 썬다.',rnum)
insert into processImg(iseq, link, description, rnum) values (2,'/imageRecipe/p4_2.jpg','믹서에 잣, 닭 육수, 소금을 넣고 곱게 간 후 체에 내린다.',rnum)
insert into processImg(iseq, link, description, rnum) values (3,'/imageRecipe/p4_3.jpg','끓는 물에 소금을 넣고 소면을 넣어 삶는다..',rnum)
insert into processImg(iseq, link, description, rnum) values (4,'/imageRecipe/p4_4.jpg','그릇에 삶은 소면을 담고 잣 국물을 넉넉히 부은 후 채 썬 오이를 올려 완성한다.',rnum)
--잣 150g, 닭 육수 2+1/2종이컵, 소면 1인분, 오이 1/3개, 소금 약간.















insert into ingredient(rnum, iname, quantity) values(rnum, '닭가슴살', '300g');
insert into ingredient(rnum, iname, quantity) values(rnum, '두부', '1/2개');
insert into ingredient(rnum, iname, quantity) values(rnum, '유부', '1개(2~3인용)');
insert into ingredient(rnum, iname, quantity) values(rnum, '소금', '1t');
insert into ingredient(rnum, iname, quantity) values(rnum, '후추', '1/2t');
