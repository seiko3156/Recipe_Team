package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.ProcessimageVO;
import com.ezenb1.recipe.dto.RecipeVO;

public class RecipeUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String url = "recipe/recipeUpdateForm.jsp";
		// **** 로그인한 이용자가 작성한 게시물만 수정 가능하도록 조정 예정 (업데이트폼에서?)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			url = "recipe.do?command=loginForm";
		}else {
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			RecipeDao rdao = RecipeDao.getInstance();
			RecipeVO rvo = rdao.getDetailView(rnum);
			request.setAttribute("recipeVO", rvo);
			ArrayList<ProcessimageVO> pivoList = rdao.getDetailProcessImages(String.valueOf(rnum)); 
			// **** rdao 메서드에서 int 매개변수로 바꾸기
			request.setAttribute("pivoList", pivoList);
			ArrayList<String> ingArray = rdao.getIngTag(rnum); // recipeTag에서 tag_id -> ingTag에서 tag 받아오기
			ArrayList<String> quanArray = rdao.getQuantity(rnum); // recipeTag에서 quantity 받아오기
			for(int i=0; i<ingArray.size(); i++) { // tag 꺼내서 # 붙임
				String str = "#" + ingArray.get(i);
				ingArray.remove(i);
				ingArray.add(i, str);
			}
			String ex = "";
			ArrayList<String> exArray = new ArrayList<String>();
			for(int i=0; i<ingArray.size(); i++) { // tag + quantity를 하나의 문자열로
				ex = (ingArray.get(i) + " " + quanArray.get(i) + " ");
				exArray.add(i, ex);
			}
			request.setAttribute("exIng", exArray);
		}
				
		request.getRequestDispatcher(url).forward(request, response);

	}

}
