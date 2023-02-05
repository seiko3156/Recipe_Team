package com.ezenb1.recipe.controller.action.member;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.MemberDao;
import com.ezenb1.recipe.dto.MembersVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession();
		 ServletContext context = session.getServletContext();
		 
		 String path = context.getRealPath("imageProfile");
		 
		
		   
		 try {
			 MultipartRequest multi = new MultipartRequest(
		     request,path,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy()
		   );
		
			 MemberDao mdao = MemberDao.getInstance();
			 MembersVO mvo = new MembersVO(); 
		
			 mvo.setId(multi.getParameter("id"));
			 mvo.setPwd(multi.getParameter("pwd"));
			 mvo.setName(multi.getParameter("name"));
			 mvo.setNick(multi.getParameter("nickname"));
			 mvo.setEmail(multi.getParameter("email"));
			 mvo.setZip_num(multi.getParameter("zip_num"));
			 mvo.setAddress1(multi.getParameter("address1"));
			 mvo.setAddress2(multi.getParameter("address2"));
			 mvo.setPhone(multi.getParameter("phone"));
			 mvo.setIndate(Timestamp.valueOf(multi.getParameter("indate")));
			 mvo.setUseyn(multi.getParameter("useyn"));
		
			 if(multi.getFilesystemName("img")==null) {
				 mvo.setImg(multi.getParameter("oldImg")); //???? 
			 } else {
				 mvo.setImg("imageProfile/" + multi.getFilesystemName("img"));
			 }
			 mdao.updateMember(mvo);
		
			 session =request.getSession();
			 session.setAttribute("loginUser", mvo);
			 
			 String url = "recipe.do?command=myPageView";
			 request.getRequestDispatcher(url).forward(request, response);
		
		
		 }catch(Exception e) {
			//객체생성 실패에 대한 예외처리 or 파일 업로드 실패에 대한 예외처리
			System.out.println("파일 업로드 실패"+e);
		 }
			   
	}

}
