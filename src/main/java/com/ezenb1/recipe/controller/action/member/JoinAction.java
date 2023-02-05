package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.MembersVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// request 에서 세션을 추출한 후 session.getServletContext()를 사용합니다.
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("imageProfile");

		MultipartRequest multi = new MultipartRequest(request, path, // 저장경로
				5 * 1024 * 1024, // 파일 제한용량
				"UTF-8", new DefaultFileRenamePolicy());
		/**
		System.out.println(multi.getParameter("id"));
		System.out.println(multi.getParameter("pwd"));
		System.out.println(multi.getParameter("name"));
		System.out.println(multi.getParameter("phone"));
		System.out.println(multi.getParameter("email"));
		System.out.println(multi.getParameter("nickname"));
		System.out.println(multi.getParameter("address1"));
		System.out.println(multi.getParameter("address2"));
		System.out.println(multi.getParameter("zip_num"));
		System.out.println(multi.getParameter("useyn"));
		System.out.println(multi.getFilesystemName("img"));
		System.out.println(path);
		*/

		MembersVO mvo = new MembersVO();
		mvo.setId(multi.getParameter("id"));
		mvo.setPwd(multi.getParameter("pwd"));
		mvo.setName(multi.getParameter("name"));
		mvo.setPhone(multi.getParameter("phone"));
		mvo.setEmail(multi.getParameter("email"));
		mvo.setNick(multi.getParameter("nickname"));
		mvo.setAddress1(multi.getParameter("address1"));
		mvo.setAddress2(multi.getParameter("address2"));
		mvo.setZip_num(multi.getParameter("zip_num"));
		mvo.setUseyn(multi.getParameter("useyn"));
		mvo.setImg("imageProfile/" + multi.getFilesystemName("img"));

		MemberDao mdao = MemberDao.getInstance();
		int result =mdao.insertMember(mvo);
		
		if(result==1) {
	         request.setAttribute("message", "회원가입 완료~! 로그인하세요!");
	      }else {
	         request.setAttribute("message", "회원가입 실패~! 다음에 다시 시도하세요! 계속 실패하면 관리자에게 문의하세요");
	      }
	      request.getRequestDispatcher("recipe.do?command=loginForm").forward(request, response);

	}

}