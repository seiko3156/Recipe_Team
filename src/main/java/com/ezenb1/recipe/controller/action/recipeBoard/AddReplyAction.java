package com.ezenb1.recipe.controller.action.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;

public class AddReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "recipe.do?command=recipeDetailWithoutView";
		
		HttpSession session = request.getSession();
		MembersVO mvo = (MembersVO) session.getAttribute("loginUser");
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		
		if( avo == null && mvo == null) url="recipe.do?command=loginForm";
		/**else if( avo != null && mvo == null) {
			AdminDao adao = AdminDao.getInstance();
			int rnum = Integer.parseInt( request.getParameter("rnum") );
			String reply = request.getParameter("reply");
			adao.updateQna( rnum, reply);
			url = url + "&rnum=" + rnum;
			어드민으로는 덧글을 아직 지원하지 않습니다.
		}*/
		else if( avo == null && mvo != null) {
			
			if( !session.getAttribute("done").equals("replyDone!") ) {
				MemberDao mdao = MemberDao.getInstance();
				String id = mvo.getId();
				System.out.println("저장된 아이디는 "+ id);
				int rnum = Integer.parseInt( request.getParameter("rnum") );
				String reply = request.getParameter("reply");
				mdao.insertRecipeReply( id , rnum, reply );
				url = url + "&rnum=" + rnum;
				session.setAttribute("done", "replyDone!");
			}
			
			
		}else {
			System.out.println("오류 발생");
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
