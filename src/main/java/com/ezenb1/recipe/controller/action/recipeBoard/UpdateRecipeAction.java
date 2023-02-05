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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateRecipeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// **** 파일 업로드 오류 처리 미정
		
		RecipeVO rvo = new RecipeVO();
		RecipeDao rdao = RecipeDao.getInstance();
		
		HttpSession session = request.getSession();
		String path = session.getServletContext().getRealPath("imageRecipe");
		MultipartRequest multi1 = new MultipartRequest( request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		int rnum = Integer.parseInt(multi1.getParameter("rnum"));
		rvo.setRnum(rnum);
		rvo.setId(multi1.getParameter("id"));
		rvo.setSubject(multi1.getParameter("subject"));
		rvo.setContent(multi1.getParameter("content"));
		rvo.setTime(Integer.parseInt(multi1.getParameter("cookingTime")));
		String thumbnail = multi1.getFilesystemName("thumbnail");
		if(thumbnail == null) {
			rvo.setThumbnail( multi1.getParameter("oldThumbnail"));
		}else {
			rvo.setThumbnail("imageRecipe/"+ thumbnail);
		}
		// rvo.setThumbnail("imageThumb/"+multi1.getOriginalFileName("thumbnail"));
		rvo.setType(Integer.parseInt(multi1.getParameter("type")));
		rvo.setTheme(Integer.parseInt(multi1.getParameter("theme")));
		
		// 파일 각자 name으로 받기 (폼에서 div 수 전송)
		
		if(request.getParameter("count") == null) { // 확인용 조건문
			System.out.println("count가 전송되지 않았어요");
		}else {
			// **** recipeUpdateForm에선 추가 클릭 x -> count 전송 x (해결)
			int count = Integer.parseInt(request.getParameter("count")); 
			System.out.println(count);
			
		ArrayList<ProcessimageVO> pivoList = new ArrayList<ProcessimageVO>();
		int i;
		for(i=0; i<count ; i++) {
			ProcessimageVO pivo = new ProcessimageVO();
			String fileName = multi1.getFilesystemName("processImg" + (i+1));
			String oldFileName = multi1.getParameter("oldProcessImg"+(i+1));
			if(fileName == null) // 수정하지 않을 경우(사진 선택 X) or 요리 단계 추가 후 사진 입력하지 않았을 경우
				if(oldFileName ==null) {
					System.out.println("oldFileName이 없고 새로 추가한 div예요");
					pivo.setLinks("imageRecipe/cookingTimer.png");
				}else {
					pivo.setLinks( oldFileName);
				}
			else
				pivo.setLinks("imageRecipe/" + fileName);
			
			// **** 사용자가 사진 추가한 경우에 대한 처리 미정 
			pivo.setIseq(i+1);
			String detail = multi1.getParameter("processDetail"+ (i+1));
			if(detail == null || detail.equals("")) {
				System.out.println("detail이 null인 경우 : " + detail);
				pivo.setDescription("요리 과정을 입력하지 않았어요.");
			}
			else {
				System.out.println(detail);
				pivo.setDescription(detail);
			}
			pivoList.add(pivo);
			if(fileName == null) System.out.println("fileName이 null");
			else System.out.println(fileName);
			if(detail == null) System.out.println("detail이 null");
			else System.out.println(detail);
		}
		
		// 재료 String으로 받아서 split
		String checkIng = multi1.getParameter("checkIng");
		System.out.println("checkIng : " + checkIng); // 확인용
		String [] ingredients = checkIng.split("\\s");
		for(String str : ingredients) { // 확인용
			System.out.println("ingredients 배열 : " + str);
		}
		ArrayList<String> ingArray = new ArrayList<String>();
		ArrayList<String> quanArray = new ArrayList<String>();
		for(i=0; i<ingredients.length; i++) {
			if(ingredients[i].startsWith("#")) {
				String substr = ingredients[i].substring(1);
				ingArray.add(substr);
			}else {
				quanArray.add(ingredients[i]);
			}
		}
		
		for(String str : ingArray) { // 확인용
			System.out.println("ingArray 내용 : " + str);
		}
		for(String str : quanArray) { // 확인용
			System.out.println("quanArray 내용 : " + str);
		}
		
		// recipe 수정
		rdao.updateRecipeAtOnce(rvo, pivoList, ingArray, quanArray);
		
		String url = "recipe.do?command=recipeDetailWithoutView&rnum=" + rnum;
		// request.getRequestDispatcher(url).forward(request, response);
		response.sendRedirect(url); // **** rdao에 insert sql 존재
		
		} // 확인용 else
	}

}
