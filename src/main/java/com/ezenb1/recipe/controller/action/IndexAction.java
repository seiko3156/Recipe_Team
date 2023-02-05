package com.ezenb1.recipe.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.RecipeVO;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RecipeDao rdao = RecipeDao.getInstance();
		
		/**	각각의 타입별 리스트를 메인 페이지에 표시하기 위한 코드들입니다.
					
			ArrayList<RecipeVO> themeList =  rdao.getThemeList();
			ArrayList<RecipeVO> ingList =  rdao.getIngList();
			
			
			request.setAttribute("themeList", themeList);
			request.setAttribute("ingList", ingList);
		 * */
		
		ArrayList<RecipeVO> allList = rdao.selectKindAll();
		ArrayList<RecipeVO> typeList =  rdao.getTypeList();
		
		System.out.println("typeList는 null 입니까?" + (typeList == null));
		request.setAttribute("allList", allList);
		request.setAttribute("typeList", typeList);
		session.setAttribute("done", "not yet");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);

	}

}
