package com.kh.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;


/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardService bService = new BoardService();
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		ArrayList<Category> list = bService.selectCategoryList();
		Board b = bService.selectBoard(boardNo); // 상세보기 조회할때 사용했던
		Attachment at = bService.selectAttachment(boardNo);
		
		request.setAttribute("list", list);
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)){
			
			// 1-1. 전송파일 용량제한
			int maxSize = 10*1024*1024;
			
			// 1-2. 전달받은 파일을 저장시킬 서버의 물리적인 경로 얻어오기(getRealPath 사용)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			// 2. 전달된 파일명 수정작업후 서버에 업로드
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",new MyFileRenamePolicy());
			
			// 3. 본격적으로 sQL문 실행시 필요한 값들 뽑아오기
			// - Board테이블에 update시 필요한 데이터 수정하기 눌럿을때 무조건 실행되야하는
			int boardNo = Integer.parseInt(multi.getParameter("bno"));
			String category = multi.getParameter("category");
			String boardTitle = multi.getParameter("title");
			String boardContent = multi.getParameter("content");
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			// Attachment는 새로이 전달된 첨부파일이 있을 경우에만 필요한 값 뽑기.
			Attachment at = null;
			
			if(multi.getOriginalFileName("reUpfile") != null ) {
				
				// 2개의 쿼리문중 공통적으로 들어가는 항목들 먼저 담아주기/
				at = new Attachment();
				at.setOriginName(multi.getOriginalFileName("reUpfile"));
				at.setChangeName(multi.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfile/");
				
				// 기존에 첨부파일이 등록되어 있을 경우, UPDATE문이 실행되고 , 이 때 file_no가 필요
				// file_no는 originFilNo라는 키값으로 넘겨줬었음.
				
				if(multi.getParameter("originFileNo") != null) { 
					//새로운 첨부파일이 있고 기존에도 첨부파일이 등록되어있던 케이스
					// => update 실행. 실행시 조건문으로 기존의 파일 고유번호가 필요했음
					at.setFileNo(Integer.parseInt(multi.getParameter("originFileNo")));
					
					// 기존에 파일은 쓸모가 없어지게 되므로 웹서버상에서 삭제처리 시켜줘야함.
					// 삭제처리시 파일의 저장경로 + 웹서버상에 저장된 파일의 이름이 필요함.
					// 웹서버상에 저장된 파일의 이름은 originfileName이라는 키값으로 넘겨줬음.
					new File(savePath+multi.getParameter("originFileName")).delete();
					
					
				}else {
					// 기존에는 첨부파일이 없었지만 이번에 새롭게 등록하는 케이스
					// REF_BNO에 저장할 게시글 번호가 필요하다.
					at.setRefNo(boardNo);
				}
			}
			
			//모두 하나의 커넥션객체로 묶어서 비지니스 로직 처리
			int result = new BoardService().updateBoard(b,at);
			// case1 : 새로운 첨부파일 x => b, null => board에 update실행 후 종료
			// case2 : 새로운 첨부파일 o, 기존 첨부파일 o => b, fileNo가 담긴 at => board, attachment 둘다 update
			// case3 : 새로운 첨부파일 o, 기존 첨부파일 x => b, redNo가 담긴 at => board에는 update, attachment에는 insert 
			
			if(result > 0) { //수정성공 => 상세조회페이지 재요청
				request.getSession().setAttribute("alertMsg", "성공성공 오예르");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+boardNo);
				
			} else { // 수정실패 => 에러페이지로 포워딩
				request.setAttribute("errorMsg", "게시글 수정 실실실실패패ㅠㅠㅠㅠㅠ");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
			
		}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
