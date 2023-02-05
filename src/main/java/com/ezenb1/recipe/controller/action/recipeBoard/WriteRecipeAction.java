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
import com.ezenb1.recipe.dto.RecipeTagVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteRecipeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 레시피를 등록한 후 today's table(전체 레시피 리스트)로 이동하는 액션입니다.
		
		String url = "recipe.do?command=recipeCategory&게시판=total"; 
		HttpSession session = request.getSession();
		
		RecipeVO rvo = new RecipeVO();
		RecipeDao rdao = RecipeDao.getInstance();
		
		
		String path = session.getServletContext().getRealPath("imageRecipe");
		MultipartRequest multi1 = new MultipartRequest( request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		rvo.setId(multi1.getParameter("id"));
		rvo.setSubject(multi1.getParameter("subject"));
		rvo.setContent(multi1.getParameter("content"));
		rvo.setTime(Integer.parseInt(multi1.getParameter("cookingTime")));
		rvo.setThumbnail("imageRecipe/"+multi1.getFilesystemName("thumbnail"));
		// rvo.setThumbnail("imageThumb/"+multi1.getOriginalFileName("thumbnail"));
		rvo.setType(Integer.parseInt(multi1.getParameter("type")));
		rvo.setTheme(Integer.parseInt(multi1.getParameter("theme")));
		
		// 파일 각자 name으로 받기 (폼에서 div 수 전송)
		int count = Integer.parseInt(request.getParameter("count"));
		System.out.println("count : " + count);
		ArrayList<ProcessimageVO> pivoList = new ArrayList<ProcessimageVO>();
		int i;
		for(i=0; i<count ; i++) {
			ProcessimageVO pivo = new ProcessimageVO();
			String fileName = multi1.getFilesystemName("processImg" + (i+1));
			// String fileName = multi1.getOriginalFileName("processImg" + (i+1));
			if(fileName == null) {
				System.out.println("fileName" + (i+1) + "이 null");
				pivo.setLinks("imageRecipe/cookingTimer.png");
			}
			else {
				pivo.setLinks("imageRecipe/" + fileName);
				System.out.println(fileName);
			}
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
		
		// recipe 한번에 입력
		rdao.insertRecipeAtOnce(rvo, pivoList, ingArray, quanArray);
		
		response.sendRedirect(url);

	}

}
