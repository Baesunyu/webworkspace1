package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.BoardService;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class boardDeletController
 */
@WebServlet("/delete.bo")
public class boardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		int result = new BoardService().deleteBoard(boardNo);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "게시글 삭제 완료");
			response.sendRedirect(request.getContextPath()+"/list.bo");
		} else {
			request.setAttribute("errorMsg", "게시글 삭제 실실패패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
