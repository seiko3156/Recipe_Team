<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/admin_header.jsp"%>

<article class="admin-board container admin-mem-list">
<h1>회원리스트</h1>  
<form name="frm" method="post">
<table class="admin-search-area">
	<tr><td>회원 이름 <input type="text" name="key" value="${key2}">
	<input class="btn" type="button" value="검색" onclick="go_search('adminMemList')">
	<input class="btn" type="button" name="btn_total" value="전체보기 "	onClick="go_total('adminMemList')"></td> </tr>
</table><br>
<table id="membersList">

	<tr> <th><input type='checkbox'name='id' value='selectall' onclick='selectAll(this,name)'/>전체선택/해제</th>
	<th>아이디</th><th> 이름 </th><th>이메일</th><th>닉네임</th><th>가입일</th>
	</tr>
    <c:forEach items="${membersList}" var="membersVO">  
	    <tr>
	    <td><input type="checkbox" name="id" value="${membersVO.id}"></td>
	    <td>
	    <c:choose>
	    <c:when test='${membersVO.useyn=="Y"}'>	    
	    ${membersVO.id}<span style="font-weight:bold; color:blue;">정상</span>		    
	    </c:when>
	    <c:otherwise>${membersVO.id}<span style="font-weight:bold; color:red;">휴먼</span>		    		    
	    </c:otherwise>
	    </c:choose>
	    </td>
	    	<td><a href="recipe.do?command=adminMemDetail&id=${membersVO.id}">${membersVO.name}</a></td><td>${membersVO.email}</td><td>${membersVO.nick}</td>
	    	<td><fmt:formatDate value="${membersVO.indate}"/></td></tr>
	  </c:forEach>
	  <tr>
	  <th><a href="#" onClick="go_sleep_member();">휴면회원 전환하기</a></th>
	  <tr>
	</table>
	<div class="paging-area">
		<jsp:include page="/admin/paging/paging.jsp">   
		    <jsp:param value="recipe.do?command=adminMemList" name="command"/>
		</jsp:include>
	</div>
	</form>
</article>

<%@ include file="/admin/footer.jsp"%>