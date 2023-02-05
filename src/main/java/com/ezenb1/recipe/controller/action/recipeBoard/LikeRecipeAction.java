package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;

public class LikeRecipeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "recipe.do?command=recipeDetailWithoutView";
		
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO) session.getAttribute("loginUser");
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		
		if( avo == null && mvo == null) {
			url="recipe.do?command=loginForm";
			request.setAttribute("message", "로그인 후에 이용할 수 있는 기능입니다.");
		}
		/**else if( avo != null && mvo == null) {
			AdminDao adao = AdminDao.getInstance();
			int rnum = Integer.parseInt( request.getParameter("rnum") );
			String reply = request.getParameter("reply");
			adao.updateQna( rnum, reply);
			url = url + "&rnum=" + rnum;
			어드민으로는 덧글을 아직 지원하지 않습니다.
		}*/
		else if( avo == null && mvo != null) {
			
			if( !session.getAttribute("done").equals("likeDone!") ) {
				RecipeDao rdao = RecipeDao.getInstance();
				// 로그인한 아이디 값을 가져와서 세팅합니다.
				String id = mvo.getId();
				// 전달받은 rnum 값을 가져와서 세팅합니다.
				int rnum = Integer.parseInt( request.getParameter("rnum") );
				
				// 지금 좋아요를 누른 사람이 이 게시물에 좋아요를 누른 적이 있는지 없는지를 검사합니다.
				boolean wlt = rdao.isHeLikeThisRecipe( rnum, id );
				
				/** interest에 먼저 N, Y 값으로 바꾼 후에 전체 좋아요 갯수는 count(*) as cnt 로 얻은 값을 가져옵니다. */
				if(wlt) {	// 좋아요를 누른적이 있다면 
					rdao.addLikesRecipe( id , rnum );
				}else {		// 좋아요를 누른적이 없다면 interest 테이블에 먼저 삽입을 합니다.
					rdao.insertLikeRecipe( id , rnum );
					rdao.insertFavoriteRecipe(id, rnum);
				}
				
				// recipe-page에 총 좋아요 갯수를 업데이트 합니다.
				rdao.selectLikeRecipe( rnum );
				
				url = url + "&rnum=" + rnum;
				session.setAttribute("done", "likeDone!");
			}
			
		}else {
			System.out.println("오류 발생");
		}
		request.getRequestDispatcher(url).forward(request, response);
		//response.sendRedirect(url);
	}

}
