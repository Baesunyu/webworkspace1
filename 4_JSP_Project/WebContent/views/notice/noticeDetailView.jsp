<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.kh.notice.model.vo.Notice" %>
<% Notice n = (Notice) request.getAttribute("n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>

<style>
	.outer{
		height:550px;
	}
	#detail-area {boder:1px solid white;}

</style>
</head>
<body>

	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align = "center">공지사항 상세보기</h2>
		<br>
		
		<table id="detail=area" align="center" border="1">
			<tr>
				<th width="70">제목</th>
				<!-- <td width="350" colspan="3">공지사항 제목입니다. </td> -->
				<td width="350" colspan ="3"><%= n.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<!-- <td>admin</td> -->
				<td><%= n.getNoticeWriter() %></td>
				<td>작성일</td>
				<!-- <td>2023-05-29<td> -->
				<td><%= n.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<!-- <p style="height:150px;">xxxxxxxxxxxxxxxxxxxxxx</p> -->
					<p style="height:150px;"><%= n.getNoticeContent() %></p>
				</td>
			</tr>
		</table>
		<br><br>
		
		<div align="center">
			<a href = "<%= contextPath %>/list.no" class="btn btn-secondary btn-sm">목록으로</a>
			
			<% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) { %> <!--  작성자만 변경 가능하게 == admin-->
				<a href="<%=contextPath %>/update.no?nno=<%=n.getNoticeNo() %>" class="btn btn-warning btn-sm">수정하기</a>
				<a href="<%=contextPath %>/delete.no?nno=<%=n.getNoticeNo() %>" class="btn btn-danger btn-sm">삭제하기</a>
			<%} %>
			
		</div>
		
	
	</div>




</body>
</html>