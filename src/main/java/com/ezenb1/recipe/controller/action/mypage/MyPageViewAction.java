package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dto.MembersVO;

public class MyPageViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url ="mypage/mypage.jsp";
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO)session.getAttribute("loginUser");
		
		if(mvo==null) {
			url="recipe.do?command=loginForm";
		}else {
			request.getRequestDispatcher(url).forward(request, response);
		}
		

	}

}
