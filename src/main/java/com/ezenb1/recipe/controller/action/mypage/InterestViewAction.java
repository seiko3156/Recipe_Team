package com.ezenb1.recipe.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.RecipeDao;
import com.ezenb1.recipe.dto.FavoriteVO;
import com.ezenb1.recipe.dto.InterestVO;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.util.Paging;

public class InterestViewAction implements Action {

	   @Override
	   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	      String url="mypage/interestView.jsp";
	      
	      HttpSession session = request.getSession();
	      MembersVO mvo = (MembersVO)session.getAttribute("loginUser");
	      
	      if(mvo==null) {
	         url="recipe.do?command=loginForm";
	      }else {
	         
	         // 주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다.(cdao 생성 필요)
	         RecipeDao rdao =RecipeDao.getInstance();
	         
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
	         
	         
	         
	         int count=rdao.getInterestAllCount(mvo.getId() );
	         System.out.println("count" + count);
	         paging.setTotalCount(count);
	         
	         //interest는 좋아요를 누를 때 접속해 있던 사람의 id
	         // 접속한 아이디로 좋아요 누른 게시물들의 갯수를 가져옵니다. (rnum(게시물번호)들의 어레이리스트)
	         ArrayList<Integer> rnum = rdao.selectInterestRnums( mvo.getId() );
	         
	         // 좋아요를 눌렀던 게시물들의 rnum을 기반으로 그 rnum에 해당하는 게시물을
	         // rvo의 그릇의 형태로 담아낸 어레이리스트
	         ArrayList<RecipeVO> interestRecipe = rdao.selectInterestRecipe(rnum);
	         
	         ArrayList<FavoriteVO> flist = rdao.selectFavorite(mvo.getId());
	         ArrayList<Integer> replyCountList = rdao.countReply(interestRecipe);
	         ArrayList<InterestVO> ylist = rdao.selectIView(mvo.getId(), paging);
	         
	         //ArrayList<RecipeVO> plist = rdao.pagingRecipe(mvo.getId(), paging);
	         
	         
//	         request.setAttribute("ilist", ilist);
	         request.setAttribute("flist", flist);
	         request.setAttribute("ylist", ylist);
	         //request.setAttribute("plist", plist);
	         
	         request.setAttribute("paging", paging);
	         request.setAttribute("replyCountList", replyCountList);
	      }
	      request.getRequestDispatcher(url).forward(request, response);
	   }

	}