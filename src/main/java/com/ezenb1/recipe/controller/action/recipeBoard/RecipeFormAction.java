package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;

public class RecipeFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) {
			url = "recipe.do?command=loginForm";
		}else {
			url = "recipe/recipeForm.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
