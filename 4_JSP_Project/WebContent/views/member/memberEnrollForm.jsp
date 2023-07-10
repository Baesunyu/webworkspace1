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
		color:black;
		width:1000px;
		margin:auto;
		margin-top:50px;
	}
	
	button {
		border-radius : 10px;
		background:#cb99c5;
		box-shadow: 0 0 0 1px #cb99c5 inset,
        0 0 0 2px rgba(255,255,255,0.15) inset,
        0 5px 0 0 rgba(189, 142, 183, .7),
        0 5px 0 1px rgba(0,0,0,.4),
        0 5px 8px 1px rgba(0,0,0,0.5);
		border : none;
		color : white;
	}

	#enroll-form table {margin:auto;}
	#enroll-form input {margin:5px;}
</style>
</head>

<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">회원가입</h2>
		
		<form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">
			<!--  아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미 -->
			
			<table>
                <!-- (tr>td*3)*8 -->
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" required></td>
                    <td><button type="button" onclick="idCheck();">중복확인</button></td>
                </tr>
                <tr>
                    <td>* 비밀번호</td>
                    <td><input type="password" name="userPwd" maxlength="15" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>* 비밀번호 확인</td>
                    <td><input type="password" maxlength="15" required></td> <!-- 단순 비교 확인 용도라 key 값을 부여 안해도 됨 -->
                    <td></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력"><td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="email"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <!-- (input[type=checkbox name=interest id= value=]+label)*6 -->

                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>

                        <input type="checkbox" name="interest" id="hiking" value="등산">
                        <label for="hiking">등산</label>

                        <input type="checkbox" name="interest" id="fishing" value="낚시">
                        <label for="fishing">낚시</label>

                        <br>

                        <input type="checkbox" name="interest" id="cooking" value="요리">
                        <label for="cooking">요리</label>

                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="game">게임</label>

                        <input type="checkbox" name="interest" id="movie" value="영화">
                        <label for="movie">영화</label>
                    </td>
                </tr>
            </table>
			
			<br><br>
			
			<div align="center">
				<button type="submit">회원가입</button>
				<button type="reset">초기화</button>
			</div>
			
		</form>
	</div>
	
	<!-- 아이디 중복확인 ajax이용 -->
	<script >
		function idCheck(){
			
			//아이디를 입력하는 input 요소 객체 조회.
			const $userId = $("#enroll-form input[name=userId]"); // 회원가입, 로그인와 겹치지 않게끔 선택
			
			$.ajax({
				url : "<%= contextPath%>/idCheck.me",
				data : {checkId : $userId.val()},
				success : function(result){
					if(result == "0"){ // 사용불가
						alert("이미 존재하거나, 회원탈퇴한 아이디입니다.");
					$userId.focus();
					}else { // 사용가능
						if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){ // 확인 누르면 사용(true)
							$userId.attr("readonly",true); // 아이디값 확정 
						}else{ // 사용안함
							$userId.focus();
							
						}
					}
				}
			})
		}
	
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>