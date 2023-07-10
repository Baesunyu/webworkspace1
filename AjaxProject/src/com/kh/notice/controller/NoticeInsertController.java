package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("views/notice/noticeEnrollForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userNo = request.getParameter("userNo"); // 세션에서 꺼내오는게 조은 방법이긴 함 왜냐믄 개발자도구에서 id 보여서 보안에 취약하다
			   userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo()+""; // import Member
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
	
		Notice n = new Notice();
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		n.setNoticeWriter(userNo);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result >0 ) { // 성공시 => /jsp2/list.no 리스트페이지가 보여지도록 url 재요청 sendRedirect
			request.getSession().setAttribute("alertMsg", "공지사항 등록 성공");
			response.sendRedirect(request.getContextPath()+"/list.no");
			
		} else {
			request.setAttribute("errorMsg", "공지사항 등록 실해");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	
	
	
	
	}

}
