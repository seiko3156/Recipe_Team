package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;

public class AdminDeleteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] delrp = request.getParameterValues("delrp");
		
		AdminDao mdao = AdminDao.getInstance();
		for( String replyseq : delrp ) {
		mdao.deleteReply(Integer.parseInt(replyseq));
		}
		System.out.println(delrp);
		response.sendRedirect("recipe.do?command=adminReplyList");

	}

}
