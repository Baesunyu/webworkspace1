package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/delete.no")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 성공시
		// 성공적으로 공지사항이 삭제되었습니다. => 리스트페이지로 url 재요청보내기
		
		// 삭제 실패시 
		// 공지사항 삭제 실패 => 에러페이지로 포워딩
		
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		int result = new NoticeService().deleteNotice(noticeNo); // 보안에 취약함. 사용자 아이디가 관리자 아이디와 일치하는지 확인하는 과정 추가하면 좋음
		
		if(result >0) {
			request.getSession().setAttribute("alretMsg", "공지사항 삭제 완료");
			response.sendRedirect(request.getContextPath()+"/list.no");
		} else {
			request.setAttribute("errorMsg", "공지사항 삭제 실실패패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
