package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.AddressVO;

public class FindZipNumAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dong=request.getParameter("dong");
		
		if(dong !=null) {
			if(dong.equals("")==false) {
				MemberDao mdao=MemberDao.getInstance();
				ArrayList<AddressVO> list = mdao.selectAddress(dong);
				
				request.setAttribute("addressList", list);
			}
			
		}
		
		
		
		
		RequestDispatcher dp = request.getRequestDispatcher("member/findZipNum.jsp");
		dp.forward(request,response);

	}

}
