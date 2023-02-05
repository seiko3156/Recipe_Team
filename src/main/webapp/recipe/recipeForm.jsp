<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>


<article id="recipe-update">
	<h1>레시피&nbsp;&nbsp;작성&nbsp;/&nbsp;등록</h1>
	<!-- <form name="frm" method="post" action="recipe.do?command=writeRecipe" enctype="multipart/form-data" > -->
	<form name="frm" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="id" value="${loginUser.id}"/>
		<input type="hidden" name="nick" value="${loginUser.nick}"/>
		<input type="hidden" name="count" id="count" value=""/>
		<table id="list">
			<tr>
				<th>제목</th><td width="343" colspan="5">
		       	<input type="text" name="subject" size="47" maxlength="100" placeholder="제목을 입력해주세요"></td>
		    </tr>
		    <tr>
				<th>썸네일 이미지</th>
				<td width="343" colspan="5"><input type="file" name="thumbnail" required></td>
			</tr>  

			<tr>
				<th>종류</th>
				<td colspan="5">
					<select name="type">
						<option value="0">선택</option>
						<option value="1">밥/죽</option>
						<option value="2">국/탕/찌개</option>
						<option value="3">반찬</option>
						<option value="4">원플레이트</option>
						<option value="5">음료</option>
						<option value="6">디저트</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>테마</th>
				<td colspan="5">
					<select name="theme">
						<option value="0">선택</option>
						<option value="1">건강</option>
						<option value="2">홈베이킹/홈파티/홈카페</option>
						<option value="3">편의점레시피</option>
						<option value="4">5분 레시피</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>재료</th>
				<td>
					<label>재료의 이름과 양을 입력한 후 엔터를 누르세요. </label><br>
					<input type="text" name="ingredient" placeholder="재료 이름 입력" onPaste="return false"/>
					<input type="text" name="quantity" placeholder="양 입력(예: 300g)" onkeypress="passString()" onPaste="return false"/> 
					<!-- <input type="text" name="checkIng" placeholder="양 입력(예: 300g )" />위에서 입력한 내용 자동 전환 -->
					<br><input class="ingredients-area" type="text" name="checkIng" readonly/><input type="button" onClick="clearIng()" value="초기화"/>
				</td>
				
			</tr> 
			<tr>
				<th>소요 시간</th>
				<td width="70"><input type="text" name="cookingTime" size="11" placeholder="예시) 30" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onPaste="return false"> 분</td> 
				<!-- <td width="70"><input type="text" name="cookingTime" size="11" placeholder="예시) 30" onkeypress="return onlyNumberKey(event)"> 분</td> -->
			</tr>
			<tr>
				<th>상세설명</th><td colspan="5">
				<textarea name="content" rows="5" cols="40" placeholder="요리에 대한 간단한 소개라던지 짧은 이야기를 들려주세요 :)"></textarea></td>
			</tr>
			<tr>
				<th>요리과정</th><td colspan="5">
				<div class="recipe-process" name="1">
					<input type="file" name="processImg1" required/>
					<textarea name="processDetail1" rows="3" cols="50" placeholder="요리 과정을 간략하게 설명해주세요."></textarea>
					<!-- <input type="text" name="processDetail1" size="20"/> -->
				</div>
				<input id="add-button" class="btn" type="button" value="추가" />
				<input id="delete-button" class="btn" type="button" value="삭제"/>
				</td>
				
			</tr> 
			
			
		</table>
		<div id="recipe-form-bottom-btn-area">
			<!-- <input type="submit" value="등록"/> -->
			<input class="btn" type="button" value="등록" onClick="go_recipe('writeRecipe')"  id="writeBtn" disabled="disabled" /> 
			<!-- <input class="btn" type="button" value="등록" id="writeBtn">   -->          
			<!-- <input class="btn" type="button" value="목록" onClick="go_recipe(list)"> --> 
			<input class="btn" type="button" value="목록" onClick="history.go(-1)"> 
		</div>
	</form>
</article>

<script type="text/javascript">
document.frm.querySelector('input[name="ingredient"]').addEventListener('keydown', function(e) {
  	console.log(e.code);
  if (e.code === 'Space') {
    	e.preventDefault();
  	}
});
document.frm.querySelector('input[name="quantity"]').addEventListener('keydown', function(e) {
  	console.log(e.code);
  if (e.code === 'Space') {
    	e.preventDefault();
  	}
});
</script>

<%@ include file="../footer.jsp" %>