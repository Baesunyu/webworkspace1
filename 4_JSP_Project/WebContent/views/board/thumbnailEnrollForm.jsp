<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진게시판</title>
<style>
	.outer{
		height:700px;
	}
	#enroll-form table{border:1px solid white;}
	#enroll-form input, #enroll-form textarea{width:100%; box-sizing:border-box;}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">사진게시판 작성하기</h2>
		<br>
		
		<form action="<%= contextPath %>/insert.th" id="enroll-form" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">
			<table align="center">
				<tr>
					<th width="100px">제목</th>
					<td colspan="3"><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" style="resize:none;" rows="5" required></textarea></td>
				</tr>
				<tr>
					<th>대표이미지</th><!--  미리보기 영역  -->
					<td colspan="3">
						<img id="titleImg" width="250" height="170">
					</td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<td><img id="contentImg0" width="150" height="120"></td>
					<td><img id="contentImg1" width="150" height="120"></td>
					<td><img id="contentImg2" width="150" height="120"></td>
				</tr>
			</table>
		
			<div id="file-area">
				<input type="file" id="file1" name="file1" onchange="loadImg(this, 1)" required><!-- 대표이미지 -->
				<input type="file" id="file2" name="file2" onchange="loadImg(this, 2)">
				<input type="file" id="file3" name="file3" onchange="loadImg(this, 3)">
				<input type="file" id="file4" name="file4" onchange="loadImg(this, 4)">
			</div>
		
			<div align="center">
				<button type="submit">등록</button>
			</div>
			
		</form>
		
		<script>
		
			$(function(){
				 $("#file-area").hide();
				
				$("#titleImg").click(function(){
					$("#file1").click();
				});
				
				$("#contentImg0").click(function(){
					$("#file2").click();
				});
				
				$("#contentImg1").click(function(){
					$("#file3").click();
				});
				
				$("#contentImg2").click(function(){
					$("#file4").click();
				});
			});
			
			function loadImg(inputFile, num){
				// inputFile : 현재 변화가 생긴 input type="file"요소 객체
				// num : 몇번째 input요소인지 확인 후 해당 영역에 미리보기 하기 위한 매개변수
				
				console.log(inputFile.files.length);
				/*
					파일 선택시 길이는1, 파일선택 취소시 길이는 0
					legth속성을 활용해서 input태그에 파일 존재 유무를 확인 할 수 있다.
				
				*/
				if(inputFile.files.length == 1){
					// 파일을 읽어들일 수 있는 자바스크립트내장객체 FileReader 객체 생성
					const reader = new FileReader();

					// 파일을 읽어들이는 메소드 => 매개변수로 어느파일을 읽어들일건지 선택해줘야함
					reader.readAsDataURL(inputFile.files[0]); // 0번 인덱스에 담긴 파일정보를 제시
					// 해당파일을 읽어들이는 순간 그 파일만의 고유한 url이 부여됨
					// 해당 url을 src속성으로 부여
					
					// 파일 읽기가 완료되었을 경우 실행할 함수를 정의
					reader.onload = function(e){ // event의 target.result에 각 파일의 url정보가 담김
						// 각 영역에 맞춰서 이미지 미리보기 설정
						switch(num){
						case 1: $("#titleImg").attr("src", e.target.result); break;
						case 2: $("#contentImg0").attr("src", e.target.result); break;
						case 3: $("#contentImg1").attr("src", e.target.result); break;
						case 4: $("#contentImg2").attr("src", e.target.result); break;
						}
					}
				} else{
					// 선택된 파일이 없는 경우
					// 미리보기 사라지게 하기
					switch(num){
					case 1: $("#titleImg").attr("src", null); break;
					case 2: $("#contentImg0").attr("src", null); break;
					case 3: $("#contentImg1").attr("src", null); break;
					case 4: $("#contentImg2").attr("src", null); break; // 혹은 removeAttr
					}
				}				
				
			}
		
		</script>
		
	
	
	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>