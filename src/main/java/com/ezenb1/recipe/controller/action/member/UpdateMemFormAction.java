package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;

public class UpdateMemFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="member/updateMemForm.jsp";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) {
			url="member/loginForm.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}
