package com.ezenb1.recipe.controller.action.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.QnaDao;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.QnaVO;
import com.ezenb1.recipe.util.Paging;

public class QnaListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Qna
		
		// 최종 목적지
		String url = "qna/qnaList.jsp";
		
		// 로그인 체크
		HttpSession session = request.getSession();
//		MembersVO mvo = (MembersVO)session.getAttribute("loginUser");	
//		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		
//		if(mvo==null&&avo==null) {
//		url = "recipe.do?command=loginForm";
//		request.setAttribute("message", "로그인 후에 이용할 수 있는 기능입니다.");
//		}else{
		
		// 로그인한 아이디로 qna 목록을 조회하고 리턴받음  
		QnaDao qdao = QnaDao.getInstance();
		
		// 처음보는게시물이 1페이지로 설정
		int page=1;
		// 내가 보는값이 다른페이지면
		if( request.getParameter("page") !=null ) {
		// 리퀘스트에 파라미터로 page가 전달된다면 page변수값을 그값으로 대체
		//  1을 지우고 현재 보는값으로 저장	
			page = Integer.parseInt( request.getParameter("page") );
			session.setAttribute("page", page);
		// 2이상의 페이지에서 게시물을 보다가 다시 게시판으로 돌아갈때, 보던 페이지번호를 잃어 버리는 경우가 많아서
		// 현재 보고 있는 페이지번호를 세션에 저장했다가
		}else if ( session.getAttribute("page")!=null) {
		// request에 파라미터가 없다면 한번더 세션을 검사하여 페이지번호를 대체할지를 결정합니다
			page = (Integer) session.getAttribute("page");
		} else {
			session.removeAttribute("page"); 
		}
		Paging paging = new Paging();
		// 위에서 결정된 페이지번호 객체에 저장
		paging.setPage(page);
		
		
		int count = qdao.getAllCount(); 
	
		// 게시물 총갯수를 totalCount 변수에 저장. paging() 메서드로 호출
		paging.setTotalCount(count);
		// 모든 객체의 멤버변수 준비 완료
		
		// list에다가 조회한값을 넣는다
		ArrayList<QnaVO> list = qdao.selectQna( paging );
		
		// qnaList.jsp로 두개의 값을 담아서 보낸다
		request.setAttribute("qnaList", list);
		request.setAttribute("paging", paging);

//		}
		// 값을 가지고 url 인 qnaList.jsp로 값을 전달
		request.getRequestDispatcher(url).forward(request, response);
		}
	}



