package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;

public class MyRecipeDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String []rnumArr=request.getParameterValues("rnum");
		
		RecipeDao rdao=RecipeDao.getInstance();
		for(String rnum: rnumArr) {
			rdao.deleteMyRecipe(Integer.parseInt(rnum));
		}
		response.sendRedirect("recipe.do?command=myRecipeList");
		
	}
}
