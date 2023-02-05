package com.ezenb1.recipe.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dto.MembersVO;

public class QnaWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 최종 목적지 
		String url = "qna/qnaWriteForm.jsp"; 
		
		// loginUser 세션 유지
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO)session.getAttribute("loginUser");
		
		// 아닐경우 index 이동
		if(mvo == null) 
		url = "recipe.do?command=index";
		  
		String secret = request.getParameter("secrett");
		request.setAttribute("secret", secret);
		System.out.println(secret);
		// 값을 가지고 이동
		request.getRequestDispatcher(url).forward(request, response);

	}

}
