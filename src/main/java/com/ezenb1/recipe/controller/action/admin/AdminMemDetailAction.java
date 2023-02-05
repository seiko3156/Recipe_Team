package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dto.MembersVO;

public class AdminMemDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		AdminDao adao = AdminDao.getInstance();
		MembersVO mvo = adao.getMember(id);
		if(mvo.getImg() ==null || mvo.getImg() == "") {
			mvo.setImg("basic.jpg");
		}
			
		request.setAttribute("memberVO", mvo);
		
		request.getRequestDispatcher("admin/member/adminMemDetail.jsp").forward(request, response);
	}

}
