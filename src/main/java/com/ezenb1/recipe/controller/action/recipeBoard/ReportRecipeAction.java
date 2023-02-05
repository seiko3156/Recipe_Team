package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;

public class ReportRecipeAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "recipe.do?command=index";
		/**
		 * int rnum = Integer.parseInt(request.getParameter("rnum"));
		 * String url = "recipe.do?command=recipeDetailWithoutView&rnum=" + rnum;
	 	2차 때 하기로 변경
	
		HttpSession session = request.getSession();
		RecipeDao rdao = RecipeDao.getInstance();
		
		
		
		if(session.getAttribute("loginUser")== null) {
			url = "recipe.do?command=loginForm";
		}else{
			rdao.reportRecipe(rnum);
		}
		*/
		request.getRequestDispatcher(url).forward(request, response);

	}

}
