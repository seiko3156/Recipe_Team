package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.ReplyVO;
import com.ezenb1.recipe.util.Paging;

public class AdminReplyListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/reply/adminReplyList.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		
		if( avo == null) { 
			url = "recipe.do?command=admin"; 
		}else {
			
			int page=1;
			
			if( request.getParameter("page")!= null ) {   
				page = Integer.parseInt( request.getParameter("page") );
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!=null ) {   
				page = (int) session.getAttribute("page") ; 
			} else {   
				session.removeAttribute("page");
			}
			String key="";
			if( request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!=null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			AdminDao adao = AdminDao.getInstance();
			int count = adao.getAllCount("reply", "id", key);
			paging.setTotalCount(count);
			ArrayList<ReplyVO> replyList = adao.selectReply( paging, key );
			request.setAttribute("replyList", replyList);
			request.setAttribute("paging", paging);
			session.setAttribute("key3", key);	
		}
	    request.getRequestDispatcher(url).forward(request, response);

	}

}
