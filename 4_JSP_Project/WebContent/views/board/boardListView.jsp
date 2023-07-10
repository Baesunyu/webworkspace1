<%@ page import = "java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.common.model.vo.PageInfo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>) request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();

%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		height : 550px;
	}
	.list-area{
		border: 1px solid white;
		text-align:center;
	}
	.list-area>tbody>tr:hover{
		background:pink;
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판</h2>
		<br>
		
		<% if(loginUser != null) { %>
			<div align="right" style="width:850px;">
				<a href = "<%= contextPath %>/insert.bo" class="btn btn-secondary">글작성</a>
				<br>
				<br>
			</div>
		<%} %>
		
		<table align="center" class="list-area">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<% if(list.isEmpty()) {%>
					<tr>
						<td colspan="6">조회된 리스트가 없습니다.</td>
					</tr>
				<%} else { %>
					<%for(Board b : list) { %>
						<tr>
							<td><%= b.getBoardNo() %></td>
							<td><%= b.getCategory() %></td>
							<td><%= b.getBoardTitle() %></td>
							<td><%= b.getBoardWriter() %></td>
							<td><%= b.getCount() %></td>
							<td><%= b.getCreateDate() %></td>
						</tr>
					<%} %>
				<%} %>
			
<!-- 				<tr>
					<td>10</td>
					<td>집</td>
					<td>집에갑시당</td>
					<td>배롱</td>
					<td>14</td>
					<td>2023-05-30</td>
				</tr>
				<tr>
					<td>9</td>
					<td>치킨</td>
					<td>알싸한마늘치킨</td>
					<td>배롱이</td>
					<td>6</td>
					<td>2023-05-19</td>
				</tr>
				<tr>
					<td>8</td>
					<td>학원</td>
					<td>학원에 불났으면 좋겠다</td>
					<td>배롱</td>
					<td>14</td>
					<td>2023-05-17</td>
				</tr>
				<tr>
					<td>7</td>
					<td>치킨</td>
					<td>굽네치킨 최고의 메뉴 추천</td>
					<td>배롱</td>
					<td>14</td>
					<td>2023-05-11</td>
				</tr>
				<tr>
					<td>6</td>
					<td>역사</td>
					<td>고구려의 흥망성쇠</td>
					<td>배롱</td>
					<td>14</td>
					<td>2023-04-28</td>
				</tr>
				<tr>
					<td>5</td>
					<td>역사</td>
					<td>김춘추 일생에 대한 고찰</td>
					<td>배롱</td>
					<td>14</td>
					<td>2023-04-13</td>
				</tr>
				<tr>
					<td>4</td>
					<td>역사</td>
					<td>1차세계대전: 경제학적 관점</td>
					<td>배롱</td>
					<td>0</td>
					<td>2023-04-01</td>
				</tr>
				<tr>
					<td>3</td>
					<td>집</td>
					<td>침대와 중력</td>
					<td>배롱배롱</td>
					<td>13</td>
					<td>2023-03-29</td>
				</tr>
				<tr>
					<td>2</td>
					<td>집</td>
					<td>꼬비 귀여워</td>
					<td>배롱</td>
					<td>10</td>
					<td>2023-02-10</td>
				</tr>
				<tr>
					<td>1</td>
					<td>집</td>
					<td>집에갑시당</td>
					<td>배롱</td>
					<td>6</td>
					<td>2023-01-01</td>
				</tr>
 -->			</tbody>
		</table>
	
		<br><br>
		
		<!--  페이징바 -->
		<div align="center" class="paging-area">
			<% if(currentPage != 1) { %>
				<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%=currentPage - 1%>'">&lt;</button>
			<%} %>
			
			<% for(int p = startPage; p<= endPage; p++){ %>
				
				<% if(p != currentPage) { %>
				<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%=p%>'"><%= p %></button>
				<%} else {%>
				<!-- 현재 내가 보고있는 페이지일 경우 클릭이 안되게끔 -->
					<button disabled><%= p %></button>
				<%} %>
				
			<%} %>
			
			<!-- <button>&lt;</button> -->
			
			<!-- <button>1</button>
			<button>2</button>
			<button>3</button>
			<button>4</button>
			<button>5</button>
			<button>6</button>
			<button>7</button>
			<button>8</button>
			<button>9</button>
			<button>10</button> -->
			
			<% if(currentPage != maxPage) { %>
				<button onclick="location.href = '<%=contextPath%>/list.bo?currentPage=<%=currentPage+1%>'">&gt;</button>
			<%} %>
		</div>
	
	
	
	
	
	</div>

	<script>
		$(function(){
			$(".list-area>tbody>tr").click(function(){
				location.href="<%=contextPath%>/detail.bo?bno="+$(this).children().eq(0).text();
			})
		});
	
	</script>




















</body>
</html>