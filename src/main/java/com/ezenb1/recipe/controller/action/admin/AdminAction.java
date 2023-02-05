package com.ezenb1.recipe.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.dao.AdminDao;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.dto.ReplyVO;

public class AdminAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      
      AdminDao adao = AdminDao.getInstance();
      
      //전체 조회수
      int viewcnt = adao.getViews();
      request.setAttribute("viewcnt", viewcnt);
      System.out.println("viewcnt :"+viewcnt);
      //전체 레시피수
      int countRecipe = adao.getCounts("*","countRecipe","recipe");
      request.setAttribute("countRecipe", countRecipe);
      System.out.println("countRecipe :"+countRecipe);
      //관리자 추천 게시물수
      int adminrec = adao.getadminRec();
      request.setAttribute("adminrec", adminrec);
      System.out.println("adminrec :"+adminrec);
      //전체 회원수
      int countMember = adao.getCounts("*","countReply","members");
      request.setAttribute("countMember", countMember);
      System.out.println("countMember :"+countMember);
      //휴먼 회원수
      int sleepMember = adao.getSleepcnt();
      request.setAttribute("sleepMember", sleepMember);
      System.out.println("sleepMember :"+sleepMember);   
      //전체 댓글수
      int countReply = adao.getCounts("*","countReply","reply");
      request.setAttribute("countReply", countReply);
      System.out.println("countReply :"+countReply);
      //전체 문의수
      int countQna = adao.getCounts("*","countQna","qna");
      request.setAttribute("countQna", countQna);
      System.out.println("countQna :"+countQna);
      //전체 답변수
      int countReplyQna = adao.getCounts("replyQna","countReplyQna","qna");
      request.setAttribute("countReplyQna", countReplyQna);
      System.out.println("countReplyQna :"+countReplyQna);
      
      double RepRate = (double)(countReplyQna/(double)countQna)*100.0;
      request.setAttribute("RepRate", RepRate);
      System.out.println("RepRate :"+RepRate);
      
      //상위 조회수 게시물 3개조회
      ArrayList<RecipeVO> bestViewList = adao.bestViewList();
      request.setAttribute("bestViewList", bestViewList);
      //최근 댓글 목록조회
      ArrayList<ReplyVO> recentReplyList = adao.recentReplyList();
      request.setAttribute("recentReplyList", recentReplyList);
      
      //가장많은 문의자
      String qnaUser = adao.getQnaUser("qna");
      request.setAttribute("qnaUser", qnaUser);
      System.out.println("qnaUser :"+qnaUser);
      
      //가장많은 레시피등록자
      String recipeUser = adao.getQnaUser("recipe");
      request.setAttribute("recipeUser", recipeUser);
      System.out.println("recipeUser :"+recipeUser);
      
      //가장많은 댓글등록자
      String replyUser = adao.getQnaUser("reply");
      request.setAttribute("replyUser", replyUser);
      System.out.println("replyUser :"+replyUser);
      
      //일별 회원가입 추이(배열)
      ArrayList<String> joinCountList = adao.getJoinCount();
      request.setAttribute("joinCountList", joinCountList);
      
      System.out.print("joinCountList :");
      for(String aa:joinCountList) {
         
         System.out.print(" "+aa+" ");
      }
      //회원가입 기록있는 일자 (배열)
      ArrayList<String> joindayList = adao.getJoinDayCount();
      request.setAttribute("joindayList", joindayList);
      
      System.out.print("joindayList :");
      for(String bb:joindayList) {
         
         System.out.print(" "+bb+" ");
      }

      String url = "admin/adminMain.jsp"; 
       request.getRequestDispatcher(url).forward(request, response);

   }

}