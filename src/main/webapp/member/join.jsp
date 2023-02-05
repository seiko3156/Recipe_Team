<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<article>
   <div id="joinForm">
      <form method="post" name="joinForm"  enctype="multipart/form-data">
         <!-- <input type="hidden" name="command"  value="join"> -->
         <fieldset id="join"><legend>Basic Info</legend><br>
            <div><label>프로필사진</label><input type="file" name="img"><br></div>
            <div><label>아 이 디</label><input type="text" name="id" size="12" onkeyup="chkIdCode(event)"><input type="hidden" name="reid">
             <input type="button" value="중복 체크" class="dup" onclick="idcheck(event)"><br></div>
             <div><label>이     름</label><input type="text"  name="name"><br> </div>
             <div><label>닉 네 임</label><input type="text" name="nickname" size="12"><br></div>
             <div><label>비밀번호</label><input type="password" name="pwd" id="userpwd"><br> </div>
             <div><label>비밀번호 확인</label><input type="password" name="pwdCheck" id="userpwdchk"><br> </div>
               <p id="error1" style="color:red"> </p>
               <div><label>전화번호</label><input  type="text" name="phone" onkeyup="chkPhoneCode(event)"><br></div>
              <div><label>이메일</label><input type="text"  name="email" id="useremail" placeholder="ex)abc@naver.com" required><br> </div>
               <p id="error2" style="color:red"> </p>
             <div><label>우편번호</label><input type="text" name="zip_num"   size="10" >     
             <input type="button" value="주소 찾기" class="dup" onclick="post_zip()"><br> </div>
            <div><label>주     소</label><input type="text" name="address1"   size="50"><br></div>
            <div><label>상세주소</label><input type="text" name="address2"   size="25"> <br></div>
            
               <input type="hidden" name="useyn" value="Y">
         </fieldset>
         
         <div class="clear"></div>
         <div id="buttons">
             <input type="button" value="회원가입" class="submit" onclick="go_save()"> 
             <input type="reset" value="취소" class="cancel" onclick="location.href='recipe.do?command=index'">
         </div>
      </form>
   </div>
</article>


<%@ include file="../footer.jsp" %>