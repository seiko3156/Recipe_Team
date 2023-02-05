package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;

public class MakeNewPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/checkSuccess.jsp";
		
		// 기존 아이디와 새 비밀번호 값을 받아서 회원 정보 비밀번호 변경
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		MemberDao mdao = MemberDao.getInstance();
		int result = mdao.updatePwd(id, pwd);
		if(result == 0) {
			request.setAttribute("message", "비밀번호 변경이 실패했습니다. 다시 시도하세요");
		}else {
			request.setAttribute("message", "비밀번호 설정이 성공했습니다. 로그인하세요");
		}
		System.out.println(result); // 확인용
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
