<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립팅 원소</title>
<%! String name; %>
<!-- 선언문 (안쓴다) 변수랑 같음  -->
</head>
<body>
	
	<h1>스크립팅 원소</h1>
	<%
		// 스크립틀릿 : 이 안에 자바소스코드 작성 가능(변수선언, 메소드 호출 등)
		int sum = 0;
		for(int i = 1; i<=100; i++){
			sum += i;
		}
		System.out.println("덧셈끝! 결과 : "+sum);	
	%>
	
	<p>
		화면으로 출력하고자 한다면 <br>
		스크립틀릿을 이용해서 출력 가능 : <% out.println(sum); %> <br>
		표현식(출력식)으로 출력 가능 : <%= sum %>
	</p>
	
	<%
		// 배열 사용
		String[] names = {"배선유", "원동연", "이은영","이찬우"};
	%>
	
	<h5>배열의 길이 : <%=names.length %></h5>
	
	<%= names %> <br> <!--  주소값 출력 -->
	
	<!-- names 안에 모든걸 뽑고싶다 반복문도 가능, join도 가능-->
	<%= String.join(",",names) %>
	
	 <h2>반복문을 통해 html요소들을 반복적으로 화면에 출력</h2>
	 
	 <ul>
	 	<% for(int i = 0; i<names.length; i++){ %>
	 	<li><%= names[i] %></li>
	 	<% } %>
	 </ul>
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>