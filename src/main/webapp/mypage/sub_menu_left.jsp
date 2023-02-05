<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sub_menu_mypage">
<!-- <div >
	<input type="button" value="마이페이지" class="cancel" onclick="location.href='recipe.do?command=myPageView'">
</div>
<div>
	<input type="button" value="내가만든레시피" class="cancel" onclick="location.href='recipe.do?command=myRecipeList'">
</div>
<div>
	<input type="button" value="관심레시피" class="cancel" onclick="location.href='recipe.do?command=interestView'">
</div>
<div>
	<input type="button" value="단골레시피" class="cancel" onclick="location.href='recipe.do?command=favoriteView'">
</div>
 -->
<nav id="sub_menu" >
    <ul>
		<li><a href="recipe.do?command=myPageView&page=1">마이페이지</a></li>
		<li><a href="recipe.do?command=myRecipeList&page=1">내가만든레시피 </a></li>
		<li><a href="recipe.do?command=interestView&page=1">관심레시피</a></li> 
		<li><a href="recipe.do?command=favoriteView&page=1" >단골레시피</a></li> 
    </ul>
</nav>
</div>
