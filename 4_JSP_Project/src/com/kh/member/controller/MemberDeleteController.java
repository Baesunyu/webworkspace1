package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		
		//로그인한 회원의 정보를 얻어내는 방법 
		// 방법 1. input type = "hidden"으로 요청시 숨겨서 전달하는 바법.
		// 방법 2. session 영역에 담겨있는 회우너객체로부터 뽑아오기. //// 이게 조음ㄴ ! 
		
		
		//삭제처리시 delete문 아닌 update문 이용.
		
		// 실패시=> 에러페이지로 포워딩
		// 에러메세지 : 회원탈퇴 실패
		
		// 성공=> 메인페이지 url재요청send
		// 성공메세지 : 성공적으로 회원탈퇴되었습니다. 그동안 이용해주셔서 ㄳ
		// session에 저장된 회원정보 제거
		
		// satus를 n으로 바꾸면 삭제하는거임 
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(); // 세션 가져와서 로그인유저 정보 끌어다 쓰쟈
	
		Member loginUser = (Member) session.getAttribute("loginUser"); // 로그인유저내에 있는 정보 가져오기
		String userId = loginUser.getUserId();
//		String userPwd = loginUser.getUserPwd();  --- 아니지 사용자가 입력한 값 받아오기  // 위에는 현재 세션에 남아있는 사용자 아이디 정보
		String userPwd = request.getParameter("userPwd");
		
//		Member deleteMember = new MemberService().deleteMember(userId, userPwd);
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		if(result >0 ) { //성공
			
			session.setAttribute("alertMsg", "회원탈퇴 ㅇㅇ 이용해주셔서 ㄱㅅ");
//			session.setAttribute("loginUser", null); // 회원정보 제거
			
			// invalidate()메소드를 사용시 세션이 만료되어서 alert이 되지 않는다
			// remoeAttribute를 이용하여 현재 로그인한 사용자의 정보를 지워주는 방식으로 로그아웃 시키기.
			session.removeAttribute("loginUser");
			
			response.sendRedirect(request.getContextPath()); // 메인페이지로 보내기
			
		} else { // 실패
			
			request.setAttribute("errorPage", "회원탈퇴 실패");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);

		}
		
		
		
		
		
		
	}

}
