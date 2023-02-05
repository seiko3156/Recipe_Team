package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;

public class FindAccountFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// recipe.do에서 findAccountForm.jsp로 이동
		String url = "member/findAccountForm.jsp";
		request.getRequestDispatcher(url).forward(request, response);

	}

}
