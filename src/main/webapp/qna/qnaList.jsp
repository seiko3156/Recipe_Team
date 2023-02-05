<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>
<%@ include file="qna_sub_menu_left.jsp" %>

<script>

function checkPass( qseq ){
	var url = "qtest.do?command=passForm&qseq=" + qseq;
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300";
	window.open(    url,   'checkPassword' ,   opt);
}

function chgChk(checkbox){
	
	  const tbox = document.getElementById('pass');
	  tbox.disabled = checkbox.checked ? false : true;
	  
	  if(tbox.disabled) {
	    tbox.value = null;
	    
	  }else {
	    tbox.focus();
	  }
	  }

</script>


<article>
	<h2 class="pt-3 pb-3 mt-3 mb-3" align="center"> 고객 게시판</h2>
	<h5 class="pt-3 pb-3 mt-3 mb-3"> 궁금하신 사항은 언제든지 문의하세요 </h5>
	<form name="formm" method="post" >
		<table id="qnaListTable" style="width:100%;">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>답변 여부</th>
			</tr>
			<c:forEach items="${qnaList}" var="qnaVO">
				<tr>
					<td>${qnaVO.qseq}</td>				
					<c:choose>
						<c:when test="${qnaVO.secret=='0'}">
							<td><a href="recipe.do?command=qnaDetail&qseq=${qnaVO.qseq}">${qnaVO.qsubject}</a></td>
						</c:when>
						<c:otherwise>
							<td><a href="#" onclick="pwdcheck(${qnaVO.qnapass},${qnaVO.qseq})">${qnaVO.qsubject}
							<img src="image/key1.png" style="width:13px vertical-align:middle;"></a></td>
						</c:otherwise>					
					</c:choose>
						
					<td><fmt:formatDate value="${qnaVO.qnadate}" type="date" /></td>
					<td>
						<c:choose>
						 	<c:when test="${qnaVO.rep==1}">no</c:when>
						 	<c:when test="${qnaVO.rep==2}">yes</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
<jsp:include page="/paging/paging.jsp">
   <jsp:param name="command" value="recipe.do?command=qnaList" />
</jsp:include>
</form>
</article>	


<%@ include file="../footer.jsp" %>