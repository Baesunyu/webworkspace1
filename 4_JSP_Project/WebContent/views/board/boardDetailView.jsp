<%@ page import="com.kh.board.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("b");
	// 게시글번호, 카테고리이름, 게시글제목, 내용, 작성자아이디, 작성일, 

	Attachment at = (Attachment)request.getAttribute("at");
	// 파일번호, 원본이름, 수정된이름, 저장경로
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer table {boder-color:white;}

</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 상세보기</h2>
		<br>
		
		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">카테고리</th>
				<td width="70"><%= b.getCategory() %></td>
				<th width="70">제목</th>
				<td width="350"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:200px;">
					<%= b.getBoardContent() %>
					</p>
				</td>
			</tr>	
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<% if(at ==null) { %>
					첨부파일이 없습니다.
					<%} else {%>
						<a href="<%= contextPath %>/<%= at.getFilePath() + at.getChangeName() %>" download="<%= at.getOriginName() %>"><%= at.getOriginName() %></a>
					<%} %>
				</td>
			</tr>
		
		</table>
		<br>
		
		<div align="center">
			<a href ="<%= contextPath %>/list.bo?" class="btn btn-secondary btn-sm">목록</a>
			<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) { %>
			<a href="<%=contextPath %>/update.bo?bno=<%=b.getBoardNo() %>" class="btn btn-warning btn-sm">수정</a>
			<a href="<%=contextPath %>/delete.bo?bno=<%=b.getBoardNo() %>" class="btn btn-danger btn-sm">삭제</a>
			<% } %>
		</div>
		
		<br>
		
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<%if(loginUser != null){ %>
					<tr>
						<th>댓글작성</th>
						<td>
							<textarea cols="50" rows="3" style="resize:none;" id="replyContent"></textarea>
						</td>
						<td>
							<button onclick="insertReply();">댓글등록</button>
						</td>
					</tr>
					<%} else { %>
					
					<tr>
						<th>댓글작성</th>
						<td>
							<textarea cols="50" rows="3" style="resize:none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
						</td>
						<td>
							<button disabled>댓글등록</button>
						</td>
					</tr>
				
					<%} %>
				</thead>
				<tbody></tbody>
			
			</table>
		
		</div>
	
	</div>
	
	<script>
		function insertReply(){
			$.ajax({
				url : "<%=contextPath%>/rinsert.bo",
				data : {
						replyContent : $("#replyContent").val() , 
						bno : "<%= b.getBoardNo() %>"
				},
				type : "post",
				success : function(result){
					if(result >0){ //댓글등록 성공 => 갱신된 댓글리스트 조회
						$("#replyContent").val("");
						//댓글리스트 조회.
						selectReplyList();
					}
				}
			});
		}
	
		function selectReplyList(){
			$.ajax({
				url : "rlist.bo",
				data : {
					bno : <%= b.getBoardNo() %>
					
				},
				success : function(list){
					let result = "";
					for(let reply of list){
						result += "<tr>"
								+ "<td>"+reply.boardWriter+"</td>"
								+ "<td>"+reply.replyContent+"</td>"
								+ "<td>"+reply.createDate+"</td>"
								+"</tr>"
					}
					$("#reply-area tbody").html(result);
				}
			});
		}
		
		selectReplyList();
	</script>
	

</body>
</html>