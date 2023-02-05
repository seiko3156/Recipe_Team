package com.ezenb1.recipe.controller.action.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.QnaDao;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.QnaVO;

public class QnaDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// QnaDetail
		// 최종목적지 qnaDetial.jsp
		String url = "qna/qnaDetail.jsp";

		String refer=request.getHeader("referer");
		System.out.println("referer :"+refer);
		request.setAttribute("refer", refer);
		//qnaList.jsp에서 href 파라미터 확인
		int qseq = Integer.parseInt( request.getParameter("qseq") );

		// 로그인 체크
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO)session.getAttribute("loginUser");
		if(mvo == null) {
			url = "recipe.do?command=loginForm";
		}else { 
			QnaDao qdao = QnaDao.getInstance();
			QnaVO qvo = qdao.getQna( qseq );
			// jsp에 전달하는것 키, 값 qvo를 qnaVO에 담아서 전달
			request.setAttribute("qnaVO", qvo);
		}
		// 값을 가지고 이동 
		request.getRequestDispatcher(url).forward(request, response);
	}
}