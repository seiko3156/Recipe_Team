package com.ezenb1.recipe.controller.action.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.QnaDao;
import com.ezenb1.recipe.dto.QnaVO;

public class QnaUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// QnaUpdateForm 

		// qseq 파라미터값을 받아서 qseq 넣기
		String qseq = request.getParameter("qseq");
		System.out.println(qseq);
		// qdao 도 생성
		QnaDao qdao = QnaDao.getInstance();
		// qseq를 통해 값을 가져와서 qvo에 담기
		QnaVO qvo = qdao.getQna(qseq);
		
		// qvo값을 qnaVO에 담아서 보낸다
		request.setAttribute("qnaVO", qvo);
		
		// 값을 포함해서 보낸다
		RequestDispatcher dp = request.getRequestDispatcher("qna/qnaUpdateForm.jsp");
		dp.forward(request, response);
	}

}
