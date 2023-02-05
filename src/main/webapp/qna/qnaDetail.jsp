<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file= "../header.jsp"%>

<article id="qna-detail-view" class="container">
   <h2> 1:1 고객 게시판</h2>
   <h5> 고객님의 질문에 대해서 운영자가 1:1답변을 드립니다.</h5>
   <form name="frm" method="post">
      <input type="hidden" name="qseq" value="${qnaVO.qseq}">
      
      <table>
         <tr><th>제목</th><td width="500" style="text-align:left;">${qnaVO.qsubject}</td></tr>
         <tr><th>등록일</th><td align="left" style="text-align:left">
            <fmt:formatDate value="${qnaVO.qnadate}" type="date"/></td></tr>
         <tr><th>질문내용</th><td align="left" style="text-align:left;font-size:115%">
            <pre>${qnaVO.qcontent}</pre></td></tr>
         <tr><th>답변 내용</th><td align="left" style="text-align:left;color:white;">${qnaVO.replyQna}</tr>
      </table><div class="clear"></div>
      <div id="buttons" style="float:right">
      <c:choose>
      <c:when test="${loginUser.id==qnaVO.id}">
      <input type="button" value="수정하기" class="submit" onclick="location.href='recipe.do?command=qnaUpdateForm&qseq=${qnaVO.qseq}'">
      <input type="button" value="삭제하기" class="submit" onclick="deleteQna('${qnaVO.qseq}','${refer}')">
      <input type="button" value="목록보기" class="submit" onclick="location.href='recipe.do?command=qnaList'">
      <input type="button" value="레시피 계속보기" class="cancel" onclick="location.href='recipe.do?command=index'">
      </c:when>
      <c:otherwise>
      <input type="button" value="목록보기" class="submit" onclick="location.href='recipe.do?command=qnaList'">
      <input type="button" value="레시피 계속보기" class="cancel" onclick="location.href='recipe.do?command=index'">
      </c:otherwise>
      </c:choose>
      
      
      </div>
   </form>
</article>

<%@ include file="../footer.jsp" %>