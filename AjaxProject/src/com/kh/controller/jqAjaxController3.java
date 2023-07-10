package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class jqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class jqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member m = new MemberService().loginMember(userId, userPwd);
		
//		response.getWriter().print(m); //멤버 클래스에 있는 toString 값이 나옴. 이걸로 json 형태로 만들어주야함
//		JSONObject jobj = new JSONObject();
//		jobj.put("userNo", m.getUserNo());
//		jobj.put("userId", m.getUserId());
//		jobj.put("userPwd", m.getUserPwd());
		// 출력하고자 하는 내용이 많을수록 길어짐
		
		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().print(jobj);
		
		// 위 내용을 간편하게
		// GSON : Google JSON
		// GSON 라이브러리 연동
		//https://mvnrepository.com/artifact/com.google.code.gson/gson/2.8.2
//		Gson gson = new Gson();
		//response.getWriter().print(gson.toJson(m)); // 알아서 필드값 가지고 해당 필드값을 키값으로 거기에 담겨있는 밸류값 꺼내온다 
		new Gson().toJson(m , response.getWriter());
		// toJson(응답할 객체, 응답할 스트릠) : 응답할 스트림으로 응답할 객체를 자동으로 JSON 형태로 변환하여 전달시킴.
		// 응답할 객체가 일반 VO객체라면 JSONObject로 만들어져서 응답
		// 응답할 객체가 ArrayList라면 JSONArray로 만들어져서 응답.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
