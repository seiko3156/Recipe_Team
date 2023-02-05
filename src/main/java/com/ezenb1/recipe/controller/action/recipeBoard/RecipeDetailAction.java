package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.ProcessimageVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.dto.ReplyVO;

public class RecipeDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String src = "recipe/recipeDetail.jsp";	// 디테일 액션 기본 목적지
		int rnum = Integer.parseInt(request.getParameter("rnum").trim()); 
		
		HttpSession session = request.getSession();
		RecipeDao rdao = RecipeDao.getInstance();
		
		// 이번 세션에서 레시피를 보지 않았을 경우에만 조회수를 증가시키는 구문입니다.(그냥 조회수 증가)
		/**
		 * if( !session.getAttribute("done").equals("recipeView!") ) {
		 * rdao.addRecipeView( rnum ); session.setAttribute("done", "recipeView!"); }
		 */
		rdao.addRecipeView( rnum );
		
		// 위에서 조회수 올림 처리를 한 정보를 받아와서 rvo에 저장합니다.		
		RecipeVO rvo = rdao.getDetailView(rnum);
		
		// 진행 중 이미지들은 따로 ArrayList로 작업(2개일지 5개일지 모르기 때문에)
		ArrayList<ProcessimageVO> processImgs = rdao.getDetailProcessImages(request.getParameter("rnum"));
		
		// recipeTag, ingTag 테이블에서 해당 레시피의 재료 정보를 받아옵니다.
		ArrayList<String> ingArray = rdao.getIngTag(rnum); 
		ArrayList<String> quanArray = rdao.getQuantity(rnum); 
		ArrayList<String> exArray = new ArrayList<String>();
		String str = "";
		for(int i=0; i<ingArray.size(); i++) { // tag + quantity를 하나의 문자열로
			str = (ingArray.get(i) + " " + quanArray.get(i) + " ");
			exArray.add(i, str);
		}
		
		// 레시피에 대한 덧글 정보를 불러옵니다.
		ArrayList<ReplyVO> replyvo = rdao.getRecipeReply(rnum);
		
		
		// 레시피에 대한 정보, 과정에 대한 정보, 작성자에 대한 정보를 담아서 보내줍니다.
		request.setAttribute("selectedRecipeInfo", rvo);
		request.setAttribute("processImgs", processImgs);
		request.setAttribute("replyList", replyvo);
		request.setAttribute("rnum", String.valueOf(rnum));
		request.setAttribute("Ings", exArray);
		
		request.getRequestDispatcher(src).forward(request, response);
	}

}
