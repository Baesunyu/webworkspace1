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
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
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
		request.setCharacterEncoding("UTF-8");
		
		//실패시 => 마이페이지로 url 재요청, 
		// 메세지내용 : 비밀번호 변경 실패
		
		// 성공시 => 마이페이지로 url재요청,
		// 메세지내용 : 성공적으로 변경 완료
		// 변경된 회원정보 session에 등록
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updatePwdMember = new MemberService().updatePwdMember(userId, userPwd, updatePwd); // 반환받는 값은 멤버객체로 받아야함 ㅇ
		HttpSession session = request.getSession();
		
		if(updatePwdMember != null ) { // 성공
			// session 영역안에 업데이트된 사용자정보 담아주기
			
			session.setAttribute("alertMsg", "비밀번호 변경 성공");
			session.setAttribute("loginUser", updatePwdMember); // 같은 키값으로 존재할 수 없음 => 덮어씌워짐

		}else { // 실패
			session.setAttribute("alertMsg", "비밀번호 변경 실패");

		}
		
		response.sendRedirect(request.getContextPath()+"/update.me"); // 둘다 마이페이지니까 밖으로 빼기
		
	}
		
	
}		