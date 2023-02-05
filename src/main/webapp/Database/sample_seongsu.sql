------레시피 기본틀 ------------
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '제목', '내용', 소요시간, 종류(번호), '썸네일 경로');
insert into processImg values( iseq, '경로', '요리 과정 묘사', rnum);
insert into ingredient(iseq, iname, quantity) values( 순서 번호, '재료 이름', 재료 양);

select*from processImg;
----------------------------------------------------------------------------------------------------
alter table recipe add thumbnail varchar2(100) not null;
DROP TABLE image CASCADE CONSTRAINTS;
CREATE TABLE processImg
(
   iseq number(5) NOT NULL,
   links varchar2(1000) NOT NULL,
   description varchar2(1000) NOT NULL,
   rnum number(20) NOT NULL,
   PRIMARY KEY (iseq)
);
ALTER TABLE processImg
   ADD FOREIGN KEY (rnum)
   REFERENCES recipe (rnum)
;

----
insert into recipe(rnum, id, subject, content, time, type, thumbnail) 
values(recipe_seq.nextVal, 'scott', '단짠단짠의 대패덮밥', '뜨끈한 밥에 대패삼겹살 한점!
집밥백선생 강추레시피! 간단하고 빠르게 만드는 별미메뉴!
입맛없을때 만들먹으면 밥두공기도 거뜬해요.', 30, 1, '/imageThumb/pork_ricebowl.jpg');
insert into processImg values( 1, '/imageRecipe/pork_ricebowl1.jpg', '궁중팬에 대패삼겹살을 넣어줍니다.', rnum);
insert into processImg values( 2, '/imageRecipe/pork_ricebowl2.jpg', '대파는 큼직하게 썰어서 준비합니다..', rnum);
insert into processImg values( 3, '/imageRecipe/pork_ricebowl3.jpg', '대패삼겹살이 골고루 익도록 볶아줍니다.  고기가 적당히 익으면 준비한 대파를 넣고 좀더 볶아줍니다. 대패삼겹살이 노릇해질 정도로 충분히 익혀준 뒤', rnum);
insert into processImg values( 4, '/imageRecipe/pork_ricebowl4.jpg', '설탕 2스푼을 넣고 한번 더 볶아줍니다.  설탕을 넣고 볶아주면 고기에 윤기가 돌아요.  ', rnum);
insert into processImg values( 5, '/imageRecipe/pork_ricebowl5.jpg', '맛간장 또는 진간장을 4큰술 넣고 한번 더 볶아줍니다.  삼겹살에 간장 양념이 골고루 베이도록 볶아주면 완성이에요^^ ', rnum);
-- 대패 삼겹살 400g // 대파 2뿌리 // 설탕2스푼 // 맛간장(OR진간장) 4스푼

select*from processImg;


insert into product(pseq, name, kind,price1,price2,price3,content,image)
values(product_seq.nextval, '크로그다일부츠','2',40000,50000,10000,'오리지날 크로그다일부츠 입니다.','w2.jpg');