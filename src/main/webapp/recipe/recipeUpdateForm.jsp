<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>

<article id="recipe-update-form">
	<h1>레시피 수정</h1>
	<c:choose>
		<c:when test="${loginUser.id.equals(recipeVO.id)}">
			<form name="frm" method="post"  enctype="multipart/form-data" >
				<input type="hidden" name="id" value="${loginUser.id}"/>
				<input type="hidden" name="nick" value="${loginUser.nick}"/>
				<input type="hidden" name="count" id="count" value=""/>
				<input type="hidden" name="rnum" value="${recipeVO.rnum}"/>
				<input type="hidden" name="oldThumbnail" value="${recipeVO.thumbnail}">
					<!-- 기존 파일을 oldThumbnail로 전송  -->
				<table id="list">
					<tr>
						<th>제목</th><td width="343" colspan="5">
				       	<input type="text" name="subject" size="47" maxlength="100" value="${recipeVO.subject}"></td>
				    </tr>
				    <tr>
						<th>작성자</th><td width="343" colspan="5">${loginUser.nick}(${loginUser.id})</td>
				    </tr>
				    <tr>
						<th>썸네일 이미지</th>
						<td width="343" colspan="5">
							<img src="${recipeVO.thumbnail}"/>
							<input type="file" name="thumbnail"><br>
							<b class="text-filter-red">( 주의사항 : 이미지를 변경하고자 할 때만 선택하세요 )</b>
						</td>
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
								<option value="7">직접 입력</option>
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
							<div id="ex">
							<label>기존 재료</label><br>
							<c:forEach items="${exIng}" var="ing" varStatus="status">
								<span id="ex${status.index}" class="ex">${ing}</span> <input type="button" value="x" onClick="deleteIng(${status.index}, this);" />
							</c:forEach>
							</div>
							<br><label>재료의 이름과 양을 입력한 후 엔터를 누르세요. </label><br>
							<input type="text" name="ingredient" placeholder="재료 이름 입력"  onPaste="return false" />
							<input type="text" name="quantity" placeholder="양 입력(예: 300g)" onkeypress="passString()"  onPaste="return false"/> 
							<!-- <input type="text" name="checkIng" placeholder="양 입력(예: 300g )" />위에서 입력한 내용 자동 전환 -->
							<br><input class="ingredients-area" type="text" name="checkIng" readonly/><input type="button" onClick="clearIng()" value="초기화"/>
						</td> 
					</tr> 
					<tr>
						<th>소요 시간</th><td width="70"><input type="text" name="cookingTime" size="11" value="${recipeVO.time}" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" onPaste="return false">&nbsp;&nbsp;<b class="little-dark-filter">분</b></td>
						<%-- <th>소요 시간</th><td width="70"><input type="text" name="cookingTime" size="11" value="${recipeVO.time}" onkeydown="return onlyNumberKey(event)"></td> --%>
					</tr>
					<tr>
						<th>상세설명</th><td colspan="5">
						<textarea name="content" rows="5" cols="40" >${recipeVO.content}</textarea></td>
					</tr>
					<tr class="recipe-update-form-process" >
						<th>요리과정</th>
						<td colspan="5"><!-- 기존 사용자가 입력한 사진과 설명 -->
							<c:forEach items="${pivoList}" var="processImageVO" varStatus="status">
								<div class="recipe-process" class="recipe-update-form-process"   name="${processImageVO.iseq}">
									<div><b>${status.count}번</b></div>
									<div><img src="${processImageVO.links}" name="oldImg${processImageVO.iseq}"/></div>
									<div>
										<input type="hidden" name = "oldProcessImg${processImageVO.iseq}" value="${processImageVO.links}"/>
										<input type="file" name="processImg${processImageVO.iseq}"/>
										<textarea name="processDetail${processImageVO.iseq}" rows="4" cols="50">${processImageVO.description}</textarea>
									</div>
								</div>
							</c:forEach>
							<div class="recipe-update-form-bottom-area">
								<input id="add-button" class="btn" type="button" value="추가" />
								<input id="delete-button" class="btn" type="button" value="삭제"/>
							</div> 
						</td>
					</tr> 
					
					
				</table>
				<div class="recipe-update-form-btn-area">
					<!-- <input type="submit" value="수정" onClick="noenter();"/> -->
					<input class="btn" type="button" value="수정" onClick="go_recipe('updateRecipe')" id="updateBtn" disabled="disabled">          
					<!-- <input class="btn" type="button" value="목록" onClick="go_recipe(list)"> --> 
					<input class="btn" type="button" value="목록" onClick="history.go(-1)">
				</div> 
			</form> 
		</c:when>
		<c:otherwise>
			<script type="text/javascript">alertLogin();</script>
		</c:otherwise>
	</c:choose>
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