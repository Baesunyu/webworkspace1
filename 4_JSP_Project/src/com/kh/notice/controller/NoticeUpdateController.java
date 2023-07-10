package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		// Notice 자료형 변수 n 넣어주기 이걸 리퀘스트 스코프에 담아서
		Notice n = new NoticeService().selectNotice(noticeNo); // 실제 db에서 공지사항 정보를 조회후 담아주기

		request.setAttribute("n", n);
		
		//forward해주기
		request.getRequestDispatcher("views/notice/noticeUpdateForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(title);
		n.setNoticeContent(content);
		
		int result = new NoticeService().updateNotice(n);
		
		if(result > 0) { // 성공시 => detail.no?nno=글번호 로 url재요청 보내기(수정된 내용이 잘 반영되었나 확인)
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항 수정 완료");
			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+noticeNo); // 이부분 다시 보기 위에서 구한 noticeNo를 이 위치에 넣어준다
		}else {
			request.setAttribute("errorMsg", "공지사항 수정 실실패패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	
	
	
	}

}
