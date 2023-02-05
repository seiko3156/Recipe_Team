package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.util.Paging;

public class RecipeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// header에서 검색 시 검색 결과를 반영한 레시피 리스트로 이동하는 액션입니다. 
		String url = "recipe/recipeList.jsp";
		RecipeDao rdao = RecipeDao.getInstance();
		HttpSession session = request.getSession();
		
		if(request.getParameter("start")!=null) {
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			page = 1; // 없어도 됨
			session.removeAttribute("page");
		}
		
		String key = "";
		if( request.getParameter("key") != null) {
			key = request.getParameter("key");
			session.setAttribute("key", key); 
			// * 검색 후 결과창이 복수의 페이지일 때, 페이지 이동 시에도 검색값(key)가 유지되도록(=>paging이 유지되도록) 하는 작업
			// * request에서 key를 잃어버리더라도 session에 저장된 key를 이용
		}else if( session.getAttribute("key") != null) {
			key = (String) session.getAttribute("key");
		}else {
			session.removeAttribute("key");
		}
		
		Paging paging = new Paging();
		paging.setDisplayPage(10);
		paging.setDisplayRow(12);
		paging.setPage(page);
		
		System.out.println("key 확인용 : " + key); // 확인용
		
		ArrayList<RecipeVO> recipeList = new ArrayList<RecipeVO>();
		int count = 0;
		String condition = "";
		if( request.getParameter("condition") != null) {
			condition = request.getParameter("condition");
			session.setAttribute("condition", condition); 
		}else if( session.getAttribute("condition") != null) {
			condition = (String) session.getAttribute("condition");
		}else {
			session.removeAttribute("condition");
		}
		
		System.out.println("condition 확인용 : " + condition); // 확인용
		if(condition.equals("ing")) {
			count = rdao.getCountsByKey("ingTag i, recipeTag r", "i.tag_id = r.tag_id and i.tag", key); 
			// **** 서윤 : 현재 진행을 위해 새 메서드 만들고 코드 리뷰에서 메서드 통합 예정
			// **** 메서드 재활용 위해 테이블 이름을 매개변수로 넣기 가능 ex) int count = rdao.countAll("테이블이름", "필드이름", key); 
			paging.setTotalCount(count);
			recipeList = rdao.selectListByIng( paging , key);
			for(RecipeVO rvo: recipeList) {
				System.out.println("recipeList(재료별) 확인용 : " + rvo.getRnum());
			}
		}else if(condition.equals("title")) {
			count = rdao.getCountsByKey("recipe", "subject", key); 
			paging.setTotalCount(count);
			recipeList = rdao.selectListBySubCon("subject", paging, key);
			for(RecipeVO rvo: recipeList) {
				System.out.println("recipeList(제목별) 확인용 : " + rvo.getRnum());
			}
		}else {
			count = rdao.getCountsByKey("recipe", "content", key); 
			paging.setTotalCount(count);
			recipeList = rdao.selectListBySubCon("content", paging, key);
			
		}
		
		ArrayList<Integer> replyCountList = rdao.countReply(recipeList);
		System.out.println("count : " + count);
		
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("paging", paging);
		request.setAttribute("key", key);
		request.setAttribute("condition", condition);
		request.setAttribute("total", count);
		request.setAttribute("replyCountList", replyCountList);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
}
