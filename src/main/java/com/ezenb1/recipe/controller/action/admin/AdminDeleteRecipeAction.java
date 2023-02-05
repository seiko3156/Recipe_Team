package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;

public class AdminDeleteRecipeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] rnum = request.getParameterValues("rnum");
		
		AdminDao adao = AdminDao.getInstance();
		for( String delrnum : rnum) {
		adao.deleteRecipe(delrnum);
		}
		
		
		
		response.sendRedirect("recipe.do?command=adminRecipeList");

	}

}
