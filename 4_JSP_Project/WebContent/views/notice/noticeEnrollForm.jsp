<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글등록</title>
<style>
	#enroll-form>table{border: 1px solid white;}
	#enroll-form input, #enroll-form textarea {
		width : 100%;
		box-sizing:border-box;
	}
		
</style>

</head>
<body>

	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
	
		<br>
		<h2 align="center">공지사항 작성하기</h2>
		<br>
	
		<form id="enroll-form" action="<%= contextPath %>/insert.no" method="post"> <!-- 이동ㅇ할때는 get방식 등록할때는 post 방식으로 -->
			<input type="hidden" name = "userNo" value="<%= loginUser.getUserNo() %>">
			<!-- 현재 로그인한 사용자가 누구인지 알아내는 방법
				1. hidden타입의 input태그로 현재 세션에 있는 loginUser를 통해 알아내는 방법
				2. 서블릿단에서 session.getAttribute를 사용해서 알아내는 방법
			 -->
			<table align="center">
				<tr>
					<th width="50">제목</th>
					<td width="350"><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td></td>
				</tr>		
				<tr>
					<td colspan="2">
						<textarea name="content" rows="10" style="resize:none;" required></textarea>
					</td>
				</tr>	
			
			</table>
			<br><br>
			
			<div align="center">
				<button type="submit">등록</button>
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>
	</div>




</body>
</html>