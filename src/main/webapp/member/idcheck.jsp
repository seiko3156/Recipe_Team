<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idcheck.jsp</title>

<script src="script/mainAction.js"></script>
</head>
<body>
<h1>ID 중복 확인</h1>
<form method="post" name="idCheckForm" action="recipe.do">
	<input type="hidden" name="command" value="idCheckForm" />
	<!-- id 입력창 -->
	User ID :  <input type="text" name="id" value="${id}" >
	<!-- 재조회버튼 --> 
	<input type="submit" value="검색" class="submit"><br><br><br>
	
	<div>
		<c:if test="${result == 1}">
			<script type="text/javascript">
				opener.document.joinForm.id.value="";
				opener.document.joinForm.reid.value="";
			</script>
			${id}는 이미 사용중인 아이디입니다.
		</c:if>
		<c:if test="${result == -1}">
			${id}는 사용 가능한 ID입니다.    
			<input type="button" value="사용" class="cancel" onclick="idok('${id}');">
		</c:if>
		
	</div>
</form>
</body>
</html>