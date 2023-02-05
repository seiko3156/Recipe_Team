package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;

public class DeleteRecipeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "recipe.do?command=recipeCategory&게시판=total"; 
		int rnum = Integer.parseInt(request.getParameter("rnum"));
		HttpSession session = request.getSession();
		RecipeDao rdao = RecipeDao.getInstance();
		// String writerId = rdao.getWriterID(rnum);
		/*
		if(session.getAttribute("loginUser")== null) {
			url = "recipe.do?command=loginForm";
		}else if(session.getAttribute("loginUser") == writerId ){
			rdao.deleteRecipeAtOnce(rnum);
			System.out.println("레시피 삭제 완료");
		}else {
			url = "recipe.do?command=recipeList"; // **** 로그인한 유저가 자신이 쓰지 않은 글을 삭제하려고 했을 때 어디로 이동?
		}
		*/
		if(session.getAttribute("loginUser")== null) {
			url = "recipe.do?command=loginForm";
		}else{
			rdao.deleteRecipeAtOnce(rnum);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
