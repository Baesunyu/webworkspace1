<%@ page import="java.util.ArrayList, java.util.Date" %> <!--  import는 따로 생성하는거 권장, 여러개는 ,로 연결해서 작성 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%--  errorPage="error500.jsp" 에러마다 추가할 수 없고 번거롭--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 </title>
</head>
<body>
	<h1>page 지시어</h1>
	
	<%
		//ArrayList 사용
		ArrayList<String> list = new ArrayList();
		list.add("servlet");
		list.add("jsp");
		
		// import하고자 하는 클래스들이 여러개일 경우 , 로 이어서 작성
		Date today = new Date();
	
	%>
	
	<p>
		리스트의 길이는 ? <%= list.size() %><br>
		0번 인덱스의 값은? <%= list.get(0) %><br> 
		1번 인덱스의 값은? <%= list.get(1) %><br>
		현재 날짜 및 시간은 : <%= today %>
	</p>
	
	<%-- <%= list.get(10) %> --%> <!-- 500에러 -->
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>