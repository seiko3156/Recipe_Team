package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.MembersVO;

public class ChangeFuseynAction implements Action {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      HttpSession session = request.getSession();
	      MembersVO mvo = (MembersVO)session.getAttribute("loginUser");
	      String userId = mvo.getId();
	      
	      String [] rnum = request.getParameterValues("rnum");
	      
	      RecipeDao rdao = RecipeDao.getInstance();
	      
	      // fyn을 y>n n>y 로 바꿔주는 메서드
	      for(int i = 0; i<rnum.length; i++) {
	         rdao.changeFYN( rnum[i],  userId );
	      }

	      // 가져온 rnum에 유저가 favorite한적이 있는지를 검사하는 메서드
	      rdao.checkYourFYN(rnum, userId);

	      // Arraylist.get(index) << 특정 인덱스에 있는 값을 불러오는 메서드
	      // favoriteListRnumbers 는 fnum 값 만을 담아두는 ArrayList 객체입니다.
	      /**
	      ArrayList<Integer> favoriteListRnumbers = new ArrayList<>();
	      favoriteListRnumbers = rdao.getfavoriteListRnumbers(rnum);
	      */
	      response.sendRedirect("recipe.do?command=interestView");
	      }

	   
	}
