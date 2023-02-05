package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		AdminDao adao = AdminDao.getInstance();
		MembersVO mvo = mdao.getMember( id );
		AdminVO avo = adao.getAdmin(id); // admin 테이블에서 관리자 계정을 불러오는 메서드
		
		String url = "member/loginForm.jsp";
		HttpSession session = request.getSession();
		
		if(mvo == null) { // 멤버 테이블에 해당 계정이 없을 때
			if(avo != null) { // 관리자 테이블에 해당 계정이 있다면
				if(avo.getAdminPwd() == null) {  // 관리자 비밀번호가 null (시스템 오류)
					request.setAttribute("message", "관리자 계정 탐색 중 오류 발생.");
				}else if(!avo.getAdminPwd().equals(pwd)) { // 입력한 비밀번호와 관리자 계정의 비번 불일치
					request.setAttribute("message", "관리자 비밀번호가 올바르지 않습니다.");
				}else if(avo.getAdminPwd().equals(pwd)) { // 입력한 비밀번호와 관리자 계정의 비번 일치
					session.setAttribute("loginAdmin", avo); // loginAdmin으로 해당 계정을 저장
					url = "recipe.do?command=index"; // 메인으로 이동
					request.setAttribute("message", "관리자로 로그인하셨습니다.");
				}else {
					request.setAttribute("message", "무슨 문제인지 모르겠어요 로그인에 실패했어요.");
				}
			}else { // 해당 계정이 멤버, 관리자 테이블에 모두 없을 때
				request.setAttribute("message", "해당 계정이 존재하지 않습니다.");
			}
		} // ↓ mvo(멤버 계정)이 존재할 때
		else if(mvo.getPwd() == null)
			request.setAttribute("message", "시스템 오류. 관리자에게 문의하세요.");
		else if( !mvo.getPwd().equals(pwd))
			request.setAttribute("message", "비밀번호가 올바르지 않습니다.");
		else if(mvo.getUseyn().equals("N"))
			request.setAttribute("message", "휴면계정입니다. 사이트를 이용하려면 관리자에게 문의하세요.");
		else if(mvo.getPwd().equals(pwd)) {
			session.setAttribute("loginUser", mvo);
			url = "recipe.do?command=index";
		}
		else
			request.setAttribute("message", "기타의 이유로 로그인이 실패했어요. 관리자에게 문의하세요.");
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
