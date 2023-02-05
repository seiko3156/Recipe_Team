<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>


<div class="categoryListTitle">
총 <b>${total}</b>개의 레시피가 있습니다.

<article>
	<form name="formm" method="post">
		<h2> 단골 레시피</h2>  
		<c:choose>
			<c:when test="${fflist.size()==0 }">
				<h3 style="color:red; text-align:center;">단골레시피가 비었습니다</h3>
			</c:when>
			<c:otherwise>
				<table id="favoriteRecipeList" style="text-align:center;" >  <!-- 동일한 css 적용을 위한 id사용 -->
					<tr><th width="200"><input type='checkbox' name='rnum' value='selectall' onclick='selectMyRecipeAll(this,name)'/>전체선택/해제</th>
					<th width="100">게시물 번호</th><th width="300" >제  목</th><th width="200">작 성 일 </th><th width="200">조 회</th></tr>
					<c:forEach items="${fflist}" var="favoriteRecipeVO" varStatus="status">
						<tr>
							<td>
								<div class="recipe-card">
							   		<div class="item">
								   		<c:choose>
							      		<c:when test="${loginUser.id == fflist.id}">
							      			<a href="recipe.do?command=recipeDetailWithoutView&rnum=${fflist.rnum}">
									            	<img src="${fflist.thumbnail}" width=300 height=200>
									         </a>
								         </c:when>
							      		<c:otherwise>
								      		<a href="recipe.do?command=recipeDetailView&rnum=${fflist.rnum}">
								            		<img src="${fflist.thumbnail}" width=300 height=200>
								          	</a>
								          </c:otherwise>
						        	</c:choose>	
							   		</div>				
							   		<div><h4>${fflist.subject}</h4></div>
							   		<div class="recipe-card-nick-area"><img src="${fflist.img}" width=20 height=20>${fflist.nick} <b>(${replyCountList[status.index]})</b></div>
							   		<div><h5><img src="image/likeBtn1.png" class="recipe-card-likes"/> ${fflist.likes} &nbsp;&nbsp;<img src="image/viewIcon.png" class="recipe-card-likes"/> ${recipeVO.views}</h5></div>
							   		<div><h6>조리시간 : ${fflist.time}분</h6></div>
							   	</div>
							</td>
							<td><input  type="checkbox" name="rnum" value="${favoriteRecipeVO.rnum }"></td>
							<td width="100">${favoriteRecipeVO.rnum}</td>
							<td width="300">
								<a href="recipe.do?command=recipeDetailView&rnum=${favoriteRecipeVO.rnum}"> ${favoriteRecipeVO.subject}</a>
							</td>
							<td width="200">
								<fmt:formatDate value="${favoriteRecipeVO.indate}" type="date"/>
							</td>
							<td width="200"> ${favoriteRecipeVO.views}</td>
					</c:forEach>	
				</table>
				<div id="myrecipe-list-paging">
					<jsp:include page="/paging/paging.jsp">
						<jsp:param name="command" value="recipe.do?command=favoriteView"/>
					</jsp:include>
				</div>
				</c:otherwise>
		</c:choose>
	</form>	
</article>
	<!-- 2차때 작업할 영역
	<ul class="nav nav-tabs2 pull-right">
		<li><a href="" onclick="sortBy()">정확순</a></li>
		<li><a href="" onclick="sortBy()">최신순</a></li>
		<li><a href="" onclick="sortBy()">추천순</a></li>
	</ul> 
	-->
	
</div>
	

<%@ include file="../footer.jsp" %>