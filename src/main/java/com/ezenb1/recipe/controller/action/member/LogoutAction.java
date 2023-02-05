package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO) session.getAttribute("loginUser");
		AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
		if(mvo != null) {  // 일반 유저로 로그인 되어 있을 때
			session.removeAttribute("loginUser");
		}else if( avo != null) { // 관리자로 로그인 되어 있을 때
			session.removeAttribute("loginAdmin");
		}
		request.getRequestDispatcher("recipe.do?command=index").forward(request, response);

	}

}
