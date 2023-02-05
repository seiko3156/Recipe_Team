<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>

<article class="container pb-5 mb-5">
<div class="recipe-top-area">
	<h1>${selectedRecipeInfo.subject}</h1>
	<div class="img_thumb_view">
		<img src="${selectedRecipeInfo.thumbnail}"/>
		<div>view : ${selectedRecipeInfo.views}</div> 
	</div>
	<div class="writer-Info">
		<div class="writer-profile">
			<img src="${selectedRecipeInfo.img}"/>
		</div>
		<h4><b>${selectedRecipeInfo.nick}님(${selectedRecipeInfo.id})</b></h4>
	</div>
	<div class="recipe-content">
		<div>${selectedRecipeInfo.content}</div>
	</div>
</div>

<div class="recipe-ingredients">
	<span><b>재료&nbsp;</b>ingredients :&nbsp;</span> 
	<c:forEach items="${Ings}" var="ing">
		<pre>${ing}</pre>
	</c:forEach>
</div>

<div class="recipe-info container">
	<div id="cooking-time" class="grayscale">
		<h2>조리 시간</h2>
		<img src="image/cookingTimer.png" width="300px" height="200px"/>
		<h2>${selectedRecipeInfo.time}분</h2>
	</div>
	 
	<%-- <br>
	${selectedRecipeInfo.type}
	<br>
	${selectedRecipeInfo.ing}
	<br>
	${selectedRecipeInfo.theme}
	<br> --%>
	<table class="recipe-process">
		<tr><td colspan="3"><h2>조리 순서</h2></td></tr>
	<c:forEach items="${processImgs}" var="pImgs">
		<tr><td class="col-1">
			<h1><b>${pImgs.iseq}</b></h1></td>
			<td  class="col-5"><div class="processImgs"><img src="${pImgs.links}"/></div></td>
			<td  class="col-6"><div class="processimgs-description">${pImgs.description}</div></td></tr>
	</c:forEach>
	</table>
	
	<div id="like-btn">
		<a href="#" onClick="ILikeThis(${selectedRecipeInfo.rnum});"><img src="image/likeBtn1.png"/></a>
		좋아요 : ${selectedRecipeInfo.likes}
	</div>
</div>

</article>

<!-- 덧글 영역 -->
<div id="recipe-reply-area" class="container">
	<div class="recipe-reply-view">
		<c:choose>
			<c:when test="${replyList.size()==0}">
				<h5>작성된 덧글이 없습니다.</h5>
			</c:when>
			<c:otherwise>
				<table>
					<c:forEach items="${replyList}" var="replyVO">
						<tr><td>${replyVO.id}</td>
						<td>${replyVO.content}</td>
						<td><fmt:formatDate value="${replyVO.replydate}"/></td>
						<c:if test="${loginUser.id.equals(replyVO.id)}">
							<td><input type="button" value="삭제" onclick="deleteThisReply('${replyVO.replyseq}')"/></td>
						</c:if></tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="recipe-reply-input">
		<form method="post" name="recipeReplyAddForm">
			<div>
				<div>
					<textarea name="reply" rows="3" cols="70"></textarea>
					<input type="hidden" name="rnum" value="${selectedRecipeInfo.rnum}"/>
				   	<!-- <input type="submit" value="저장" name="submit">onClick="recipeSaveReply()" -->
			   	</div>
			   	<div class="save-rep-btn"><a href="#" onClick="recipeSaveReply()">댓글 저장</a></div>
		   	</div>
	   	</form>
	</div>
</div>

<br>
<div id="recipe-button-area" class="container">
	<c:if test="${selectedRecipeInfo.id.equals(loginUser.id)}">
		<input type="button" name="modify" value="수정하기" 
			onclick="location.href='recipe.do?command=recipeUpdateForm&rnum=${selectedRecipeInfo.rnum}'"/>
		<input type="button" name="modify" value="삭제하기" 
			onclick="location.href='recipe.do?command=deleteRecipe&rnum=${selectedRecipeInfo.rnum}'"/>
	</c:if>
	<!-- 2차 때 구현할 부분
	<input type="button" name="modify" value="신고하기" id="report-btn"
			onclick="location.href='recipe.do?command=reportRecipe&rnum=${selectedRecipeInfo.rnum}'"/>
 	-->			
	<input type="button" name="modify" value="메인으로" 
			onclick="location.href='recipe.do?command=index'"/>
	<input type="button" name="modify" value="목록으로" 
			onclick="history.go(-1);"/>
	
</div>

<%@ include file="../footer.jsp" %>