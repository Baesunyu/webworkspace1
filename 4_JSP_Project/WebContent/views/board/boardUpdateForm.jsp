<%@ page import="java.util.ArrayList, com.kh.board.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 게시판(Board)정보, 첨부파일(Attachment)정보, 카테고리 목록정보 필요
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		height:550px;
	}
	#update-form>table {
		border:1px solid white;
	}
	#update-form input, #update-form textarea{
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>

<body>

	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 수정하기</h2>
		<br>
		
		<form action="<%= contextPath %>/update.bo" id="update-form" method="post" enctype="multipart/form-data">
			
			<!-- 게시글 번호 넘겨줘야함 -->
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">
			
			<table align="center">
				<tr>
					<th width="100">카테고리</th>
					<td width="500">
						<select name="category">

							<%for( Category c   :  list  ) {%>
								<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
							<% } %>
							
							<script>
								$(function(){
									$("#update-form option").each(function(){
										$(this).text() == "<%= b.getCategory() %>"){
											$(this).attr("selected",true);
										}
									});
								})
							</script>
							
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%= b.getBoardTitle() %>" required></td>
					
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" rows="10" required><%= b.getBoardContent() %></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<% if(at != null){%>
							<%= at.getOriginName() %>
							<!-- 원본파일의 파일번호(DB수정용), 수정된 이름(웹서버 파일 삭제용 hidden으로 넘길 예정 -->
							<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
							<input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">
						<%} %>
						<input type="file" name="reUpfile"> 
						<!-- file이 들어가야하기 때문에 value로 못넣음 -->
					</td>
				</tr>
			</table>
			<br>
			
			<div align="center">
				<button type="submit">작성하기</button>
			</div>
		
		</form>
	</div>








</body>
</html>