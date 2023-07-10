<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<!--  
		* 회원서비스  |    C(Insert)    |   R(Select)    |   U(Update)    |   D(Delete)
		===========================================================================
		  로그인     |                 |      O         |                | 
		  회원가입    |         O       |                |                |
	   [아이디중복체크] |                 |       O        |                |
	     마이페이지    |                 |       O        |                | 
		  정보변경    |                 |                |       O        |
	      회원탈퇴    |                 |                |       O        |     O	
	
		* 공지사항서비스 - 공지사항리스트 조회(R)/공지사항 상세조회서비스(R)/공지사항 작성(C)/수정(U)/삭제(U/D)
		
		* 일반게시판서비스 - 게시판리스트조회(R) - 페이징처리(R)/게시판상세조회(R)/게시판작성(C) - 첨부파일 1개 업로드
		              /게시판수정(U)/삭제(U/D)/[댓글리스트조회(R)/댓글작성(C)]
		              
		* 사진게시판서비스 - 게시판리스트조회(R)-썸네일/상세조회(R)/작성(C)-다중첨부파일업로드/사진미리보기 
	
	-->
	<%@ include file="views/common/menubar.jsp" %>
</body>
</html>