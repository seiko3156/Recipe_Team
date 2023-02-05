package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.util.Paging;

public class RecipeCategoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 인코딩 용 입니다.
		
		// 선언부
		HttpSession session = request.getSession();
		String src = "recipe/recipeCategory.jsp";
		String kind = request.getParameter("kind");
		RecipeDao rdao = RecipeDao.getInstance(); 
		
		// 페이징 관련
		int page = 1;
		if( request.getParameter("page")!=null) {
			page = Integer.parseInt( request.getParameter("page") );
			session.setAttribute("page", Integer.parseInt( request.getParameter("page")));
		}else if( session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		// 키값 관련
		String key = "";
		if( request.getParameter("key")!=null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		}else if( session.getAttribute("key")!=null) {
			key = (String)session.getAttribute("key");
		}else {
			session.removeAttribute("key");
		}
		
		String whatIsthis = request.getParameter("status");
		System.out.println(whatIsthis);
		
		Paging paging = new Paging();
		paging.setDisplayPage(5);
		paging.setDisplayRow(12);
		paging.setPage(page);
		
		int count = rdao.getAllCount(whatIsthis);
		paging.setTotalCount(count);
		
		// 게시물 관련
		ArrayList<RecipeVO> list = rdao.selectKindAll( paging, whatIsthis);
		
		// 덧글 관련
		ArrayList<Integer> replyCountList = rdao.countReply(list);
		
		request.setAttribute("whatIsthis", whatIsthis);
		request.setAttribute("RecipeAllList", list);
		request.setAttribute("replyCountList", replyCountList);
		request.setAttribute("paging", paging);
		request.setAttribute("key", key);
		request.setAttribute("total", count);
		
		request.getRequestDispatcher(src).forward(request, response);
	}

}
