<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
	<!-- id / 비밀번호 찾기 선택 후 별도 팝업 폼 -->
	<!-- 서윤 : 인증 방식 수정 예정  -->
	<div id="find-acc-form-btn" class="container">
		<div><input type="button" value="ID 찾기" onClick="findAccountBy('id')"/></div>
		<div><input type="button" value="비밀번호 찾기" onClick="findAccountBy('pwd')"/></div>
	</div>
	
	
<%@ include file="../footer.jsp" %>