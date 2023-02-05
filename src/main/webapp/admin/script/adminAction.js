/**
function go_admin( comm ){
	document.frm.key.value="";
	document.frm.action = "shop.do?command=" + comm + "&page=1";
	document.frm.submit();
}
*/


function go_search( comm ){
	/* if( document.frm.key.value == "" ){
		alert("검색버튼 사용시에는 검색어 입력이 필수입니다");
	 	return;
	} */
	var url = "recipe.do?command=" + comm + "&page=1";   // 검색어로 검색한 결과의 1페이지로 이동
	document.frm.action = url;
	document.frm.submit();
}

function go_total( comm ){
	document.frm.key.value="";
	document.frm.action = "recipe.do?command=" + comm + "&page=1";
	document.frm.submit();
}


function go_reportgesi( comm ){
	document.frm.key.value="";
	document.frm.action = "recipe.do?command=" + comm + "&page=1";
	document.frm.submit();
}
function go_recommendgesi( comm ){
	document.frm.key.value="";
	document.frm.action = "recipe.do?command=" + comm + "&page=1";
	document.frm.submit();
}






function go_1recommend(rnum,gesi){

	document.frm.action = "recipe.do?command=adminchangeRecommend&rnum="+rnum+"&gesi="+gesi;
	document.frm.submit();
}

function go_recommendlist(gesi){
	var count = 0;
	if( document.frm.rnum.length == undefined ){
		if( document.frm.rnum.checked==true ) count++;
		
	}else{
		for( var i=0; i<document.frm.rnum.length; i++){
			if( document.frm.rnum[i].checked==true){
				count++;
			}
		}
	}	
	if(count == 0) {
		alert("추천할 게시물을 선택하세요");
	}	
	else{	
		document.frm.action = "recipe.do?command=adminchangeRecommend&gesi="+gesi;
	    document.frm.submit();    
	}    
}

function go_adminRecipe_delete(){
	var count = 0;
	if( document.frm.rnum.length == undefined ){
		if( document.frm.rnum.checked==true ) count++;
		
	}else{
		for( var i=0; i<document.frm.rnum.length; i++){
			if( document.frm.rnum[i].checked==true){
				count++;
			}
		}
	}	
	if(count == 0) {
		alert("삭제할 게시물을 선택하세요");
	}	
	else{	
		document.frm.action = "recipe.do?command=adminDeleteRecipe";
	    document.frm.submit();    
	}    
}






function go_sleep_member(){
	var count = 0;
	if( document.frm.id.length == undefined ){
		if( document.frm.id.checked==true ) count++;
		
	}else{
		for( var i=0; i<document.frm.id.length; i++){
			if( document.frm.id[i].checked==true){
				count++;
			}
		}
	}	
	if(count == 0) {
		alert("휴먼전환할 회원을 선택해주세요");
	}	
	else{	
		document.frm.action = "recipe.do?command=adminSleepMem";
	    document.frm.submit();    
	}    
}


function go_reply_delete(){
	var count = 0;
	if( document.frmm.delrp.length == undefined ){
		if( document.frmm.delrp.checked==true ) count++;
		
	}else{
		for( var i=0; i<document.frmm.delrp.length; i++){
			if( document.frmm.delrp[i].checked==true){
				count++;
			}
		}
	}	
	if(count == 0) {
		alert("삭제할 항목을 선택하세요");
	}	
	else{	
		document.frmm.action = "recipe.do?command=adminDeleteReply";
	    document.frmm.submit();    
	}    
}

function go_adminQna_delete(){
	var count = 0;
	if( document.frm.qseq.length == undefined ){
		if( document.frm.qseq.checked==true ) count++;
		
	}else{
		for( var i=0; i<document.frm.qseq.length; i++){
			if( document.frm.qseq[i].checked==true){
				count++;
			}
		}
	}	
	if(count == 0) {
		alert("삭제할 항목을 선택하세요");
	}	
	else{	
		document.frm.action = "recipe.do?command=adminDeleteQna";
	    document.frm.submit();    
	}    
}


function selectAll(selectAll,name)  {
  const checkboxes 
       = document.getElementsByName(name);
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}


function go_view(command,column,value){
	location.href="recipe.do?command="+command+"&"+column+"="+value;
}

function go_rep(){
	document.frm.action="recipe.do?command=adminSaveReply";
	document.frm.submit();
}

/*-------------차트 디자인------------- */
//차트 사이즈 지정
const pieW = 360, pieH = 360;

/* 바깥 원형이 각 20% 나뉨 - 개수에 따라 사이즈 변수에 담아줌 */
const deg2 = Number(20/2);
const deg3 = Number(20/3);
const deg4 = Number(20/4);

/* 
	chart data 
	=> size만 우선 사용, 다른 데이터는 임의로 넣어둠
*/
const pieData = [
{cate: 'A', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'B', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'C', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'A', name: 'Clarity', score: 30, avg: 20, size: deg4},
{cate: 'B', name: 'Clarity', score: 30, avg: 20, size: deg4},
{cate: 'C', name: 'Clarity', score: 30, avg: 20, size: deg4},
{cate: 'D', name: 'Clarity', score: 30, avg: 20, size: deg4},
{cate: 'A', name: 'Clarity', score: 30, avg: 20, size: deg2},
{cate: 'B', name: 'Clarity', score: 30, avg: 20, size: deg2},
{cate: 'A', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'B', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'C', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'A', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'B', name: 'Clarity', score: 30, avg: 20, size: deg3},
{cate: 'C', name: 'Clarity', score: 30, avg: 20, size: deg3},
];

//color data 
const colorData = [
'#26BDE2', '#0D97D9', '#05689D', 
'#FD9D26', '#DDA83A', '#BF8B2E', '#916517', 
'#FE3A21', '#FD6925',
'#DD1367', '#B41055', '#9C0142',
'#56C02B', '#4C9F38', '#3F7E44'
];

//pie 생성
const pie = d3.pie().value(d=> d.size).sort(null);

//호 생성
const arc = d3.arc().innerRadius(0).outerRadius(Math.min(pieW, pieH)/2)
const arcs = pie(pieData);

const colors = d3.scaleOrdinal(colorData);

//요소 추가
const svg = d3.select('.chart_sub')
.append('svg')
.attr('width', pieW)
.attr('height', pieH);

const g = svg.append('g')
.attr('transform', `translate(${pieW/2}, ${pieH/2})`);

g.selectAll('path')
.data(arcs)
.enter().append('path')
.attr('fill', (d, i) => colors(i) )
.attr('d', arc);


