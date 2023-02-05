package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;

public class RecentViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "mypage/recentView.jsp";

		// 쿠키로 저장해서 최근 뷰리스트 보기
		
		String rnum = request.getParameter("rnum");
		
		/*
		  //1. Cookie 객체 생성 Cookie c = new Cookie("rnum", rnum);
		  
		  //2. 쿠키의 유효기간 설정 c.setMaxAge(1*60*60); // 1시간 쿠키 수명 유지
		  
		  // 3. 클라이언트에 쿠키 전송 response.addCookie( c );
		  
		  //4. new 쿠키를 생성하여 클라이언트에 바로 전송 
		  response.addCookie( new Cookie("pwd","test1234") ); 
		  response.addCookie( new Cookie("age", "20") );
		  
		  // 배열
		  Cookie[] cookies = request.getCookies(); // 쿠키내용 한번에 다 얻어오는 명령-쿠키객체 배열로 얻어옵니다
		  
		  for (Cookie c : cookies) { out.println( "<h2>" + c.getName() + " : " +
		  c.getValue() + "</h2>"); }
		  
		 */


	}

}
