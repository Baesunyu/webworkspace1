<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 자바주석
	// <% %\> 이 구문을 스클리틀릿이라고 해서 html문서 내에 자바코드를 쓸 수 있는 영역
	// 현재 이 jsp에서 필요로 하는 데이터들 => request의 attribute에 담겨있음(setAttribute("키", 밸류))
	// request.getAttribute("키값") : Object(그에 해당하는 밸류값의 자료형)
	// Object 형식에서 내가 받고자 하는 자료형으로 강제형변환(다운캐스팅)해서 담아주면 된다. 
	
	String name = (String) request.getAttribute("name"); // request는 jsp내에 내장되어있기 때문에 바로 사용 가능
	int age = (int) request.getAttribute("age");
	String gender = (String) request.getAttribute("gender");
	String[] foods = (String[]) request.getAttribute("foods");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{color:red;}
	span{font-weight:bold;}
	#name{color:orange;}
	#age{color:yellow;}
	#gender{color:navy;}
	li{color:purple;}
</style>
</head>
<body>
	<!-- html주석 -->
	<%--jsp주석 --%>
	<h2>개인정보 응답화면</h2>
	
	<%-- <span id ='name'><% out.print(name); %></span>  --%> <!-- ctrl+shift+/ -->
	<span id='name'><%= name %></span> 님은
	<span id='age'><%= age %></span> 살이며
	성별은
	<% if(gender == null){ %>
		선택을 안했습니다. <br>
	<% } else {
		if( gender.equals("M")){
			out.print("<span id='gender'>남자</span>입니다.<br>");
		}else{
			out.print("<span id='gender'>여자</span>입니다.<br>");
		}
	}%>
	
	좋아하는 음식은
	<% if(foods == null){ %>
		없습니다.
	<%} else { %>
		<ul>
			<% for(String food : foods) { %>
			<li><%= food %></li>
			<%} %>
		</ul>
	<% } %>
	
	
</body>
</html>