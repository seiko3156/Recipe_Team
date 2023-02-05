package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dto.QnaVO;

public class AdminQnaDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qseq = request.getParameter("qseq");
		AdminDao adao = AdminDao.getInstance();
		QnaVO qvo = adao.getAdminQna(qseq);		
		ArrayList<QnaVO> qnaReplyList = adao.getQnaReplyList(qseq); 
		request.setAttribute("qnaVO", qvo);
		request.setAttribute("qnaReplyList", qnaReplyList);
		
		request.getRequestDispatcher("admin/qna/adminQnaDetail.jsp").forward(request, response);

	}

}
