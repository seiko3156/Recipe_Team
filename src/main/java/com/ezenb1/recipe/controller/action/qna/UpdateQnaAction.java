package com.ezenb1.recipe.controller.action.qna;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.QnaDao;
import com.ezenb1.recipe.dto.QnaVO;

public class UpdateQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 업뎃

		//qdao, qvo 생성 
		QnaDao qdao = QnaDao.getInstance();
		QnaVO qvo = new QnaVO();
		
		HttpSession session = request.getSession();
	
		qvo.setQsubject( request.getParameter("qsubject") );
		qvo.setQcontent( request.getParameter("qcontent") );
		//qvo.setSecret( request.getParameter("secret") );		
		System.out.println("qsubject"+request.getParameter("qsubject"));
		System.out.println("qcontent"+request.getParameter("qcontent"));
		
		qvo.setQseq(Integer.parseInt(request.getParameter("qseq")));
		System.out.println("qseq"+request.getParameter("qseq"));
		

		qdao.updateQna( qvo );
		request.setAttribute("qnaVO", qvo);
		RequestDispatcher dp = request.getRequestDispatcher("recipe.do?command=qnaDetail");
		dp.forward(request, response);
	}
}
