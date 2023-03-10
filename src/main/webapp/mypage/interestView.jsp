<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="/mypage/sub_menu_left.jsp"%>

<article id="interest-list-view">
   <form name="formm" method="post">
      <h2>관심 레시피</h2>

      <%-- <c:forEach items="${ilist}" var="interestRecipeVO" varStatus="status">
         ${interestRecipeVO.rnum}
         ${flist[status.index].fuseyn}
      </c:forEach> --%>

      <c:choose>
         <c:when test="${ylist.size()==0 }">
            <h3 style="color: red; text-align: center;">관심 레시피가 비어있습니다.</h3>
         </c:when>
         <c:otherwise>
            <table id="interestRecipeList" style="text-align: center;">
               <!-- 동일한 css 적용을 위한 id사용 -->
               <tr>
                  <th width="200"><input type='checkbox' name='selectall'
                     value='selectall' onclick='selectMyRecipeAll(this,name)' />전체선택/해제</th>
                  <th width="100">게시물 번호</th>
                  <th width="300">제 목</th>
                  <th width="200">작 성 일</th>
                  <th width="200">조 회</th>
               </tr>
               <c:forEach items="${ylist}" var="interestRecipeVO"
                  varStatus="status">
                  <tr>
                     <td><input type="checkbox" name="rnum"
                        value="${interestRecipeVO.rnum}"></td>
                     <td width="100">${interestRecipeVO.rnum}</td>
                     <td width="300"><c:choose>
                           <c:when
                              test="${interestRecipeVO.id == interestRecipeVO.interestid }">
                              <a
                                 href="recipe.do?command=recipeDetailWithoutView&rnum=${interestRecipeVO.rnum}">${interestRecipeVO.subject}
                                 <c:if test="${replyCountList[status.index] !=0}">
                                    <span style="color: #445EDD; font-size: bold;">[${replyCountList[status.index]}]</span>
                                 </c:if> <c:if test="${interestRecipeVO.fuseyn=='Y'}">
                                    <span style="color: red; font-size: bold;">(단골)</span>
                                 </c:if>
                              </a>
                           </c:when>
                           <c:otherwise>
                              <a
                                 href="recipe.do?command=recipeDetailView&rnum=${interestRecipeVO.rnum}">${interestRecipeVO.subject}
                                 <c:if test="${replyCountList[status.index] !=0}">
                                    <span style="color: #445EDD; font-size: bold;">[${replyCountList[status.index]}]</span>
                                 </c:if> <c:if test="${interestRecipeVO.fuseyn=='Y'}">
                                    <span style="color: red; font-size: bold;">(단골)</span>
                                 </c:if>
                              </a>
                           </c:otherwise>
                        </c:choose></td>
                     <td width="200"><fmt:formatDate
                           value="${interestRecipeVO.indate}" type="date" /></td>
                     <td width="200">${interestRecipeVO.views}</td>
               </c:forEach>
               <tr>
                  <th colspan="4"><a href="#"
                     onClick="go_favoriteindel('changeFuseyn');">단골레시피 변환/해제</a></th>
            </table>
            <div id="myrecipe-list-paging">
               <jsp:include page="/paging/paging.jsp">
                  <jsp:param name="command" value="recipe.do?command=interestView" />
               </jsp:include>
            </div>

         </c:otherwise>
      </c:choose>
   </form>
</article>

<%@ include file="../footer.jsp"%>