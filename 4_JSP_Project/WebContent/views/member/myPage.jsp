<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		background:pink;
		color:white;
		width:1000px;
		margin:auto;
		margin-top:50px;
	}
	
	#mypage-form table {margin:auto;}
	#mypage-form input {margin:5px;}

</style>
<!-- <script>
	function test(){
		$("input[name=interest]").each(frunction(){
			var thisVal=$(this).val();
		if(thisval.length>0){
			$(this).arrt("checked",true);
		}
		});
	}


</script> -->

</head>
<body onload="javascript:test();">

	
	
	<%@ include file = "/views/common/menubar.jsp" %>
	
	<%
		String userId = loginUser.getUserId();
		String userName = loginUser.getUserName();
		
		//아래는 필수 입력 아님
		// value값들에 null값이 들어갈 수 있으므로, 각 필드에 null값이 담긴 경우 ""로 대체할 예정
		String phone = loginUser.getPhone() == null? "" : loginUser.getPhone();
		String email = loginUser.getEmail() == null? "" : loginUser.getEmail();
		String address = loginUser.getAddress() == null? "" : loginUser.getAddress();
		String interest = loginUser.getInterst() == null? "" : loginUser.getInterst();
	%>
	
	<div class="outer">
	
		<br>
		<h2 align="center">마이페이지</h2>
		
		<form id="mypage-form" action="<%= contextPath %>/update.me" method="post">
			<table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" readonly value="<%= userId %>"></td> <!-- 수정을 막기 위해 readonly 속성 부여 -->
                    <td></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" required value="<%= userName %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력" value="<%= phone %>"><td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="email" value="<%= email %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address" value="<%= address %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <!-- (input[type=checkbox name=interest id= value=]+label)*6 -->

                        <input type="checkbox" name="interest" id="sports" value="운동"
                        <%-- 	<%= interest.indexOf("운동") > -1 ? "checked":""%> --%>
                        >
                        <label for="sports">운동</label>

                        <input type="checkbox" name="interest" id="hiking" value="등산"
                        <%-- 	<%= interest.indexOf("등산") > -1 ? "checked":""%> --%>
                        >
                        <label for="hiking">등산</label>

                        <input type="checkbox" name="interest" id="fishing" value="낚시"
                        <%-- 	<%= interest.indexOf("낚시") > -1 ? "checked":""%> --%>
                        >
                        <label for="fishing">낚시</label>

                        <br>

                        <input type="checkbox" name="interest" id="cooking" value="요리"
                        	<%-- <%= interest.indexOf("요리") > -1 ? "checked":""%> --%>
                        >
                        <label for="cooking">요리</label>

                        <input type="checkbox" name="interest" id="game" value="게임"
                        	<%-- <%= interest.indexOf("게임") > -1 ? "checked":""%> --%>
                        >
                        <label for="game">게임</label>

                        <input type="checkbox" name="interest" id="movie" value="영화"
                        	<%-- <%= interest.indexOf("영화") > -1 ? "checked":""%> --%>
                        >
                        <label for="movie">영화</label>
                    </td>
                </tr>
            </table>
		<br>
		
 		<script>
			$(function(){
				const interest = "<%= interest %>";
				
				$("input[type=checkbox][name=interest]").each(function(){
					// 순차적으로 접근한 input요소의 value값이 interest에 포함되어있을 경우
					// 해당 input요소에 checked속성 부여
					
					// interest 문자열로부터 현재 체크박스의 value가 포함되어있지 않다면
					// -1을 반환
						if(interest.search($(this).val)) != -1){
							%(this).attr("checked",true);
					}
				})
			})
			
		</script>
		
		
		<div align="center">
			<button type="submit" class="btn btn-secondary btn-sm">정보변경</button>
			<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#updatePwdForm">비밀번호 변경</button>
			<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button> <!-- 아래 두개는 모달창 형태로 따로 만들어줄 예정 -->
		</div>
		<br>
		
		</form>
		
	</div>

	<!-- The Modal 회원탈퇴 -->
   <div class="modal" id="deleteForm">
     <div class="modal-dialog">
       <div class="modal-content">
   
         <!-- Modal Header -->
         <div class="modal-header">
           <h4 class="modal-title">회원탈퇴</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
         </div>
   
         <!-- Modal body -->
         <div class="modal-body" align="center">

         <b>탈퇴 후 복구가 불가능합니다. <br>정말로 탈퇴하시겠습니까?</b><br><br>
         
         <form action="<%= contextPath %>/delete.me" method="post">
            <table>
               <tr>
                  <td>비밀번호</td>
                  <td><input type="password" name="userPwd" required></td>
               </tr>
            </table>
            <br>
            
            <button type="submit" class="btn btn-danger btn-sm">탈퇴하기</button>
         </form>
         
         </div>
   
       </div>
     </div>
   </div>
   
   
   <!-- The Modal 비밀번호 변경-->
   <div class="modal" id="updatePwdForm">
     <div class="modal-dialog">
       <div class="modal-content">
   
         <!-- Modal Header -->
         <div class="modal-header">
           <h4 class="modal-title">비밀번호 변경</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
         </div>
   
         <!-- Modal body -->
         <div class="modal-body" align="center">

         <form action="<%= contextPath %>/updatePwd.me" method="post">
            <!-- 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 재입력 -->
            <input type="hidden" name="userId" value="<%= userId %>">
            <table>
               <tr>
                  <td>현재 비밀번호</td>
                  <td><input type="password" name="userPwd" required></td>
               </tr>
               <tr>
                  <td>변경할 비밀번호</td>
                  <td><input type="password" name="updatePwd" required></td>
               </tr>
               <tr>
                  <td>변경할 비밀번호 재입력</td>
                  <td><input type="password" name="checkPwd" required></td>
               </tr>
            </table>
            
            <br>
            
            <button type="submit" class="btn btn-secondary btn-sm" onclick="return validatePwd();">비밀번호 변경</button>
         </form>
         
         <script>
            
            function validatePwd() {

               if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()) {
                  
                  alert("비밀번호가 일치하지 않습니다.");
                  
                  return false;
               }
            }
         
         </script>

         </div>
   
       </div>
     </div>
   </div>






</body>
</html>