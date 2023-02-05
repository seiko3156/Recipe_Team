package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.util.Paging;

public class MyRecipeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url ="mypage/myRecipeList.jsp";
		
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO) session.getAttribute("loginUser");
		
		if(mvo==null) {
			url="recipe.do?command=loginForm";
		}else {
			
			RecipeDao rdao = RecipeDao.getInstance();
			
			int page=1;	// 페이지 설정 : request 나 session 에 page 값이 있다면 그 값으로, 없다면 1로 현재페이지를 설정합니다
			if( request.getParameter("page") != null ) {    // 리퀘스트에 파라미터로 page가 전달된다면 page변수값을 그값으로 대체
				page = Integer.parseInt( request.getParameter("page") );
				session.setAttribute("page", page);
				// 2이상의 페이지에서 게시물을 보다가 다시 게시판으로 돌아갈때, 보던 페이지번호를 잃어 버리는 경우가 많아서
				// 현재 보고 있는 페이지번호를 세션에 저장했다가
			} else if( session.getAttribute("page")!=null) {  
				//  request에 파라미터가 없다면 한번 더 세션을 검사하여 페이지번호를 대체할지를 결정합니다
				page = (Integer) session.getAttribute("page");
			} else {
				session.removeAttribute("page"); 
			}
			
			Paging paging = new Paging();
			paging.setPage(page);  // 위에서 결정된 페이지번호 객체에 저장
			
			int count=rdao.getMyRecipeAllCount(mvo.getId() );
			paging.setTotalCount(count);
			
			ArrayList<RecipeVO> rlist = rdao.selectMyRecipe(mvo.getId(),paging);
			ArrayList<Integer> replyCountList = rdao.countReply(rlist);
			
			request.setAttribute("rlist", rlist);
			request.setAttribute("paging", paging);
			request.setAttribute("replyCountList", replyCountList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}