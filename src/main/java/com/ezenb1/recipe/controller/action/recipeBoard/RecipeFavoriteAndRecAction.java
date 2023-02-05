package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.InterestVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.util.Paging;

public class RecipeFavoriteAndRecAction implements Action {

	@Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	     String url ="recipe/recipeFavorite.jsp";
	     
	      /**
	     HttpSession session = request.getSession();
	         
         RecipeDao rdao = RecipeDao.getInstance();
         
         int page=1;   // 페이지 설정 : request 나 session 에 page 값이 있다면 그 값으로, 없다면 1로 현재페이지를 설정합니다
         if( request.getParameter("page") != null ) {    // 리퀘스트에 파라미터로 page가 전달된다면 page변수값을 그값으로 대체
            page = Integer.parseInt( request.getParameter("page") );
            session.setAttribute("page", page);
            // 2이상의 페이지에서 게시물을 보다가 다시 게시판으로 돌아갈때, 보던 페이지번호를 잃어 버리는 경우가 많아서
            // 현재 보고 있는 페이지번호를 세션에 저장했다가
         } else if( session.getAttribute("page")!=null) {  
            //  request에 파라미터가 없다면 한번 더 세션을 검사하여 페이지번호를 대체할지를 결정합니다
            page = (Integer) session.getAttribute("page");
         } else {
            session.removeAttribute("page"); 
         }
         
         Paging paging = new Paging();
         paging.setPage(page);  // 위에서 결정된 페이지번호 객체에 저장
         
         int count=rdao.getFavoriteAllCount( );
         paging.setTotalCount(count);
         
         // 사람들이 가장 많이 좋아한 단골 레시피들의 Rnum 값들을 가져옵니다.
         ArrayList<Integer> mostFavoriteRnum = rdao.selectFavoriteRecipeAll();
         
         // 그 Rnum의 ArrayList를 기반으로 페이보릿 레시피를 rvo의 형태로 가져옵니다.
         ArrayList<RecipeVO> mostFavoriteRecipe = rdao.selectMostFavoriteRecipe();
         
         //덧글 역시 가져옵니다.
         ArrayList<Integer> replyCountList = rdao.countReplyByInteger( mostFavoriteRnum );
         
         request.setAttribute("fflist", mostFavoriteRecipe);
         request.setAttribute("paging", paging);
         request.setAttribute("replyCountList", replyCountList);
         //request.setAttribute("key", key);
         request.setAttribute("total", count);
	      
	     */
         request.getRequestDispatcher(url).forward(request, response);
	   }
	}
