package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.MembersVO;

public class FindAccByAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param = request.getParameter("param");
		String name = "";
		String phone = "";
		MemberDao mdao = MemberDao.getInstance();
		MembersVO mvo = null;
		if(param.equals("id")){  // id 찾기를 눌렀을 때
			name = request.getParameter("name");
			phone = request.getParameter("phone");
			mvo = mdao.findId(name, phone); // id 검색 메서드
		}else if(param.equals("pwd")) { // 비밀번호 찾기를 눌렀을 때
			name = request.getParameter("name");
			phone = request.getParameter("phone");
			String id = request.getParameter("id");
			mvo = mdao.findPwd(name, phone, id); // 비밀번호 검색 메서드
		}
		if(mvo != null) {
			request.setAttribute("Account", mvo);
		}else {
			request.setAttribute("message", "등록된 정보가 없습니다.");
		}
		
		request.getRequestDispatcher("recipe.do?command=findAccountBy").forward(request, response);

	}

}
