package com.ezenb1.recipe.controller;

import com.ezenb1.recipe.controller.action.Action;
import com.ezenb1.recipe.controller.action.IndexAction;
import com.ezenb1.recipe.controller.action.admin.AdminAction;
import com.ezenb1.recipe.controller.action.admin.AdminChangeRecommendAction;
import com.ezenb1.recipe.controller.action.admin.AdminDeleteQnaAction;
import com.ezenb1.recipe.controller.action.admin.AdminDeleteRecipeAction;
import com.ezenb1.recipe.controller.action.admin.AdminDeleteReplyAction;
import com.ezenb1.recipe.controller.action.admin.AdminMemDetailAction;
import com.ezenb1.recipe.controller.action.admin.AdminMemListAction;
import com.ezenb1.recipe.controller.action.admin.AdminPickListAction;
import com.ezenb1.recipe.controller.action.admin.AdminQnaDetailAction;
import com.ezenb1.recipe.controller.action.admin.AdminQnaListAction;
import com.ezenb1.recipe.controller.action.admin.AdminRecipeListAction;
import com.ezenb1.recipe.controller.action.admin.AdminReplyListAction;
import com.ezenb1.recipe.controller.action.admin.AdminSaveReplyAction;
import com.ezenb1.recipe.controller.action.admin.AdminSleepMemAction;
import com.ezenb1.recipe.controller.action.homepage.AnnouncementAction;
import com.ezenb1.recipe.controller.action.homepage.OurstoryAction;
import com.ezenb1.recipe.controller.action.homepage.PrivatePolicyAction;
import com.ezenb1.recipe.controller.action.homepage.TermsAction;
import com.ezenb1.recipe.controller.action.member.ContractAction;
import com.ezenb1.recipe.controller.action.member.FindAccByAction;
import com.ezenb1.recipe.controller.action.member.FindAccountByAction;
import com.ezenb1.recipe.controller.action.member.FindAccountFormAction;
import com.ezenb1.recipe.controller.action.member.FindZipNumAction;
import com.ezenb1.recipe.controller.action.member.IdCheckFormAction;
import com.ezenb1.recipe.controller.action.member.JoinAction;
import com.ezenb1.recipe.controller.action.member.JoinFormAction;
import com.ezenb1.recipe.controller.action.member.LoginAction;
import com.ezenb1.recipe.controller.action.member.LoginFormAction;
import com.ezenb1.recipe.controller.action.member.LogoutAction;
import com.ezenb1.recipe.controller.action.member.MakeNewPassAction;
import com.ezenb1.recipe.controller.action.member.UpdateMemFormAction;
import com.ezenb1.recipe.controller.action.member.UpdateMemberAction;
import com.ezenb1.recipe.controller.action.member.WithDrawalAction;
import com.ezenb1.recipe.controller.action.mypage.ChangeFuseynAction;
import com.ezenb1.recipe.controller.action.mypage.InterestViewAction;
import com.ezenb1.recipe.controller.action.mypage.MyPageViewAction;
import com.ezenb1.recipe.controller.action.mypage.MyRecipeDeleteAction;
import com.ezenb1.recipe.controller.action.mypage.MyRecipeListAction;
import com.ezenb1.recipe.controller.action.mypage.RecentViewAction;
import com.ezenb1.recipe.controller.action.qna.DeleteQnaAction;
import com.ezenb1.recipe.controller.action.qna.MyqnaListAction;
import com.ezenb1.recipe.controller.action.qna.QnaDetailAction;
import com.ezenb1.recipe.controller.action.qna.QnaListAction;
import com.ezenb1.recipe.controller.action.qna.QnaUpdateFormAction;
import com.ezenb1.recipe.controller.action.qna.QnaWriteFormAction;
import com.ezenb1.recipe.controller.action.qna.UpdateQnaAction;
import com.ezenb1.recipe.controller.action.qna.WriteQnaAction;
import com.ezenb1.recipe.controller.action.recipeBoard.AddReplyAction;
import com.ezenb1.recipe.controller.action.recipeBoard.DeleteRecipeAction;
import com.ezenb1.recipe.controller.action.recipeBoard.DeleteReplyAction;
import com.ezenb1.recipe.controller.action.recipeBoard.FavoriteViewAction;
import com.ezenb1.recipe.controller.action.recipeBoard.LikeRecipeAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeCategoryAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeDetailAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeDetailWithoutViewAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeFavoriteAndRecAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeFormAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeListAction;
import com.ezenb1.recipe.controller.action.recipeBoard.RecipeUpdateFormAction;
import com.ezenb1.recipe.controller.action.recipeBoard.ReportRecipeAction;
import com.ezenb1.recipe.controller.action.recipeBoard.UpdateRecipeAction;
import com.ezenb1.recipe.controller.action.recipeBoard.WriteRecipeAction;

public class RecipeFactory {

	private RecipeFactory() {}
	private static RecipeFactory rf = new RecipeFactory();
	public static RecipeFactory getInstance() { return rf; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		else if( command.equals("admin") ) ac = new AdminAction();
		else if( command.equals("adminRecipeList") ) ac = new AdminRecipeListAction();
		else if( command.equals("adminDeleteRecipe") ) ac = new AdminDeleteRecipeAction();
		else if( command.equals("adminMemList") ) ac = new AdminMemListAction();
		else if( command.equals("adminReplyList") ) ac = new AdminReplyListAction();
		else if( command.equals("adminQnaList") ) ac = new AdminQnaListAction();
		else if( command.equals("adminDeleteReply") ) ac = new AdminDeleteReplyAction();
		else if( command.equals("adminMemDetail") ) ac = new AdminMemDetailAction();
		else if( command.equals("adminDeleteQna") ) ac = new AdminDeleteQnaAction();
		else if( command.equals("adminQnaDetail") ) ac = new AdminQnaDetailAction();
		else if( command.equals("adminSaveReply") ) ac = new AdminSaveReplyAction();
		else if( command.equals("adminchangeRecommend") ) ac = new AdminChangeRecommendAction();
		else if( command.equals("adminPickList") ) ac = new AdminPickListAction();
		else if( command.equals("adminSleepMem") ) ac = new AdminSleepMemAction();
		
		else if( command.equals("loginForm") ) ac = new LoginFormAction();
		else if( command.equals("login") ) ac = new LoginAction();
		else if( command.equals("findAccountForm") ) ac = new FindAccountFormAction();
		else if( command.equals("findAccountBy") ) ac = new FindAccountByAction();
		else if( command.equals("findAccBy") ) ac = new FindAccByAction();
		else if( command.equals("makeNewPass") ) ac = new MakeNewPassAction();
		else if( command.equals("logout") ) ac = new LogoutAction();
		
		else if( command.equals("contract")) ac = new ContractAction();
		else if( command.equals("findZipNum")) ac = new FindZipNumAction();
		else if( command.equals("joinForm") ) ac = new JoinFormAction();
		else if( command.equals("join") ) ac = new JoinAction();
		else if( command.equals("idCheckForm") ) ac = new IdCheckFormAction();
		else if( command.equals("updateMemForm") ) ac = new UpdateMemFormAction();
		else if( command.equals("updateMember") ) ac = new UpdateMemberAction();
		else if( command.equals("withDrawal") ) ac = new WithDrawalAction();
		else if( command.equals("ourstory") ) ac = new OurstoryAction();
		else if( command.equals("announcement") ) ac = new AnnouncementAction();
		else if( command.equals("terms") ) ac = new TermsAction();
		else if( command.equals("privatePolicy") ) ac = new PrivatePolicyAction();
		
		
		
		else if( command.equals("qnaList") ) ac = new QnaListAction();
		else if( command.equals("myqnaList") ) ac = new MyqnaListAction();
		else if( command.equals("qnaDetail") ) ac = new QnaDetailAction();
		else if( command.equals("qnaWriteForm") ) ac = new QnaWriteFormAction();
		else if( command.equals("writeQna") ) ac = new WriteQnaAction();
		else if( command.equals("qnaUpdateForm") ) ac = new QnaUpdateFormAction();
		else if( command.equals("updateQna") ) ac = new UpdateQnaAction();
		else if( command.equals("deleteQna") ) ac = new DeleteQnaAction();
		
		
		// 레시피 관련 액션들 
		else if( command.equals("recipeForm") ) ac = new RecipeFormAction();
		else if( command.equals("writeRecipe") ) ac = new WriteRecipeAction();
		else if( command.equals("recipeCategory") ) ac = new RecipeCategoryAction();
		else if( command.equals("recipeList") ) ac = new RecipeListAction();
		else if( command.equals("recipeDetailView") ) ac = new RecipeDetailAction();
		else if( command.equals("recipeDetailWithoutView") ) ac = new RecipeDetailWithoutViewAction();
		else if( command.equals("recipeFavoriteAndRec") ) ac = new RecipeFavoriteAndRecAction();
		else if( command.equals("deleteRecipe") ) ac = new DeleteRecipeAction();
		else if( command.equals("likeRecipe") ) ac = new LikeRecipeAction();
		else if( command.equals("recipeUpdateForm") ) ac = new RecipeUpdateFormAction();
		else if( command.equals("updateRecipe") ) ac = new UpdateRecipeAction();
		else if( command.equals("reportRecipe") ) ac = new ReportRecipeAction();
		
		// 덧글 관련 액션들
		else if( command.equals("addReply") ) ac = new AddReplyAction();
		else if( command.equals("deleteReply") ) ac = new DeleteReplyAction();
		
		// 마이 페이지 액션들
		else if( command.equals("myPageView") ) ac = new MyPageViewAction();
		else if( command.equals("interestView") ) ac = new InterestViewAction();
		else if( command.equals("myRecipeList") ) ac = new MyRecipeListAction();
		else if( command.equals("recentView") ) ac = new RecentViewAction();
		else if( command.equals("favoriteView") ) ac = new FavoriteViewAction();
		else if( command.equals("myRecipeDelete")) ac = new MyRecipeDeleteAction();
		else if( command.equals("changeFuseyn") ) ac = new ChangeFuseynAction();
		
		
		
		
		return ac;
	}
	
	
}