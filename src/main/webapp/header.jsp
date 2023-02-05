<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>The Recipe</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
		crossorigin="anonymous">
	<link href="css/style.css" rel="stylesheet">
	<script src="https://kit.fontawesome.com/74c64a7de1.js" crossorigin="anonymous"></script>
	<script src="script/jquery-3.6.3.js" type="text/javascript"></script>
	<script src="script/mainAction.js" type="text/javascript"></script>
</head>

<body>
	<div id="wrap">
		<header>
			<div class="container">
				<div class="boxFlexTop">
					<div class="col-md-1 col-sm-12 header-title">
						<a class="navbar-brand" href="recipe.do?command=index">
							<!-- 우리의 식탁 -->
							<img src="image/todaytabletitle.jpg">
						</a>
					</div>
					<div class="col-md-6 col-sm-12 header-mid-area">
						<select name="condition" id="condition">
							<option value="title" <c:if test="${condition=='title'}">${'selected'}</c:if>>제목</option>
							<option value="con" <c:if test="${condition=='con'}">${'selected'}</c:if>>내용</option>
							<option value="ing" <c:if test="${condition=='ing'}">${'selected'}</c:if>>재료</option>
						</select>
						<input class="col-9 nav-search" type="search" placeholder="원하는 재료나 음식 이름을 입력해주세요."
							aria-label="Search" name="key" id="key">
						<button class="btn btn-dark" type="button" id="searchByKey" >검색</button>
					</div>

					<c:if test="${loginUser.id==null && loginAdmin.adminId==null}">
						<div id="loginOrJoin" class="col-md-5 col-sm-12">
							<input type="button" value="로그인" class="header-btn"
							onClick="location.href='recipe.do?command=loginForm'" />
							<input type="button" value="회원가입" class="header-btn"
							onClick="location.href='recipe.do?command=contract'" />
						</div>
					</c:if>
					<c:if test="${loginUser.id!=null}">
						<div id="loginOrJoin" class="col-5">
							<b>${loginUser.name}(${loginUser.id})님<br>안녕하세요!</b>
							<input type="button" value="마이페이지" class="header-btn" 
								onClick="location.href='recipe.do?command=myPageView'"/>
							<input type="button" value="로그아웃" class="header-btn"
								onClick="location.href='recipe.do?command=logout'" />
							<div class="top_Icon"><a href="recipe.do?command=recipeForm"><img class="top_Icon"
										src="image/pensil1.png"></a></div>
						</div>
					</c:if>
					<c:if test="${loginAdmin.adminId!=null}">
						<div id="loginOrJoin" class="col-5">
							<b>${loginAdmin.adminId}님<br>안녕하세요!</b>
							<input type="button" value="관리자페이지" class="header-btn"
								onClick="location.href='recipe.do?command=admin'" />
							<input type="button" value="로그아웃" class="header-btn"
								onClick="location.href='recipe.do?command=logout'" />
							<div class="top_Icon"><a href="recipe.do?command=recipeForm"><img class="top_Icon"
										src="image/pensil1.png"></a></div>
						</div>
					</c:if>
				</div>
			</div>

			
		</header>
		<main class="">