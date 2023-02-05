package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dto.RecipeVO;

public class AdminChangeRecommendAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] rnum =request.getParameterValues("rnum");
		String rnum1 = request.getParameter("rnum");
		String gesi = request.getParameter("gesi");
		System.out.println(gesi);
		
		AdminDao adao = AdminDao.getInstance();
		RecipeVO rvo = new RecipeVO();
		if(rnum!=null) {
			for( String recipeNum : rnum) {
				int rec= adao.getRec(recipeNum);
				adao.ChangeRecommend(recipeNum,rec);				
			}			
		}
		else {
			int rec= adao.getRec(rnum1);
			adao.ChangeRecommend(rnum1,rec);
			System.out.println(rvo.getRec());
		}
		String url="";
		
		if(gesi.equals("all")) {
		url = "recipe.do?command=adminRecipeList";
		}else if(gesi.equals("pick")) {
		url = "recipe.do?command=adminPickList";	
		}
		response.sendRedirect(url);

	}

}
