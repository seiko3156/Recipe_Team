package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;

public class AdminSleepMemAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] id = request.getParameterValues("id");
		
		AdminDao mdao = AdminDao.getInstance();
		for( String sleep : id) {
			String useyn = mdao.selectUseyn(sleep);
			mdao.sleepMember(useyn,sleep);
			System.out.printf(useyn,sleep);
		}
		
		response.sendRedirect("recipe.do?command=adminMemList");

	}

}
