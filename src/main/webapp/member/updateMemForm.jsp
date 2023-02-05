<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


 <article>
        <h2>Edit Profile</h2>
        <form method="post" name="joinForm"  enctype="multipart/form-data">
        <%-- <input type="hidden" name="code" value="${loginUser.code}"> --%>
         <input type="hidden" name="oldImg" value="${loginUser.img}">
            <!-- <input type="hidden" name="command" value="memberUpdate" /> -->
            <fieldset>
                <legend>Basic Info</legend>
                <label>Profile</label><input type="file" name="img" value="${loginUser.img }"><br>
                (주의사항 : 이미지를 변경하고자 할때만 선택하세요)<br>
                 <label>이     름</label><input type="text" name="name"  value="${loginUser.name}"><br> 
                <label>아 이 디</label><input type="text" name="id" value="${loginUser.id}" readonly><br>
                <label>닉 네 임</label><input type="text" name="nickname" value="${loginUser.nick }"><br>
                <label>비밀번호</label><input type="password"  name="pwd" id="userpwd"><br> 
                <label>비밀번호 확인</label><input type="password"  name="pwdCheck" id="userpwdchk" ><br>
                <p id="error1" style="color:red"> </p>
                <label>전화번호</label><input  type="text" name="phone"  value="${loginUser.phone}" onkeyup="chkPhoneCode(event)"><br>
                <label>이메일</label><input type="text" name="email" value="${loginUser.email}" id="useremail"><br>
                <label>우편번호</label><input type="text" name="zip_num" size="10" value="${loginUser.zip_num}">      
                    <input type="button" value="주소 찾기" class="dup" onclick="post_zip()"><br>
                <label>주    소</label><input type="text" name="address1"  size="50" value="${loginUser.address1}" onclick="post_zip()"><br>
                <label>상세주소</label><input type="text" name="address2"  size="25" value="${loginUser.address2}"><br>
              
                
                <input type="hidden" name="indate" value="${loginUser.indate }">
                <input type="hidden" name="useyn" value="${loginUser.useyn }">
            </fieldset>
            <div id="buttons"><input type="button" value="정보수정" class="submit" onclick="go_update();"> 
                <input type="reset" value="취소" class="cancel" onclick="location.href='recipe.do?command=myPageView'"></div>
        </form>
        </article>

<%@ include file="../footer.jsp" %>