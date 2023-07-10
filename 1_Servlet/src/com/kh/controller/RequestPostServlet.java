package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do") // 이름 중복 x , 맨 앞에 / 꼭 있어야함
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 요청시 전달한 값들은 request의 parameter에 담겨있다.
		// 다만, POST방식 요청의 경우 값을 뽑기 전에 utf-8방식으로 인코딩 설정을 해야한다.
		//     post방식의 기본 인코딩 설정은 ISO-8859-1이기 때문
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name"); // "배선유", ""(텍스트 상자가 빈 경우 빈문자열) // 이거 기억하래
		String gender = request.getParameter("gender"); // "M"/"F"/null(라디오버튼 체크 안한경우)
		int age = Integer.parseInt(request.getParameter("age")); // "20",""(값이 없는경우, NumberformatException 발생)
		
		String[] foods = request.getParameterValues("food"); // ["한식","일식"]/ null(체크안하면 null값 반환)
		
		// 응답페이지 출력 
		
		// get방식에서 다뤄본 내용은 순수 Servlet만 이용한 방식 ==> java코드내에 html을 작성..
		// JSP (Java Server Page) : html내에 Java코드를 쓸 수 있는 기술
		
		// 응답페이지를 만드는 과정을 jsp에게 위임(떠넘기기)할 예정
		
		// 단, 그 응답화면(jsp)에서 필요로 하는 데이터들은 request객체에 담아서 전달해줘야한다.
		// => request의 attribute라는 영역에 데이터 담아서 전달해주기(키 - 밸류 세트로)
		// request.setAttribute("키", 밸류ㄴ);
		request.setAttribute("name", name);
		request.setAttribute("age",age);
		request.setAttribute("gender",gender);
		request.setAttribute("foods",foods); // 문자열 배열 자료형인 foods도 넣을수있당 죠탕
		
		// 위임 시 필요한 객체 : requestDispatcher객체
		// 1) 응답하고자하는 뷰 선택 후 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp"); // 매개변수에 jsp가 존재하는 경로
		
		// 2) 포워딩(위임처리)
		view.forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
