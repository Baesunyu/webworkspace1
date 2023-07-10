package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ---------------------------페이징 처리-----------------------------------
		int listCount; // 현재 총 게시글 갯수(1000개)
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수(10개씩 할 예정)
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수(10개씩)
		
		int maxPage; // 가장 마지막 페이지가 몇번째 페이지인지(총 페이지)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 현재페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage") == null? "1" : request.getParameter("currentPage"));
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수(페이지 목록들을 몇 개 단위로 출력할것인지)
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수(게시글 몇개 단위로 출력할건지)
		boardLimit = 10;
		
		// * maxPage : 가장 마지막 페이지가 몇번 페이지인지(총 페이지 수)
		/*
		 * listCount, boardLimit에 영향을 받음
		 * 
		 * - 공식 구하기(boardLimit가 10이라는 가정하에 규칙을 세울것)
		 * 
		 *  총 갯수					boardLimit				maxPage
		 *  1000개			/		10 => 100.0		   100번 페이지가 마지막
		 *  1001개			/		10 => 100.1		   101번 페이지가 마지막
		 *  1005개			/		10 => 100.5		   101번 페이지가 마지막
		 *  1010개			/		10 => 101.0		   101번 페이지가 마지막
		 *  1011개			/		10 => 101.1		   102번 페이지가 마지막이 됨.
		 *  
		 *  => 나눗셈 연산한 결과를 올림처리하면 maxPage가 나온다.
		 * 
		 * 	1) listCount를 double로 형변환
		 *  2) listCount / boardLimit를 실행
		 *  3) 결과값에 올림처리해주는 Math.ceil()메소드 호출
		 *  4) 결과값을 다시 int로 강제형변환
		 */
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 페이지 하단에 보여질 페이징바의 시작수
		/*
		 * pageLimit , currentPage에 영향을 받음
		 * 
		 *  - 공식구하기(pageLimit가 10이라는 가정을 세울것
		 *  
		 *  startPage : 1 , 11, 21, 31, ... =>  n * 10 + 1 => 10의 배수 + 1 // n은 0으로 시작
		 *  
		 *  즉 n * pageLimit + 1
		 *  
		 *  currentPage					startPage
		 *  	1							1		=> n * pageLimit + 1 => 1 => n = 0
		 *      10							1		=> n * pageLimit + 1 => 1 => n = 0
		 *  	11							11		=> n * pageLimit + 1 => 11 => n = 1
		 *  	20							11		=> n * pageLimit + 1 => 11 => n = 1
		 *   => 1~10 : n = 0
		 *   => 11~20 : n = 1
		 *   => 21~30 : n = 2
		 *		... 	n = (currentPage-1) / pageLimit
		 *				  => 0~9 / 10 => 0
		 *				  => 10~19 / 10 => 1
		 *				  => 20~29 / 10 => 2
		 *	startPage = n*pageLimit + 1
		 *
		 *			  => (currentPage-1) / pageLimit * pageLimit + 1
		 *	이해안가도 이렇게 쓴다고 정해져잇으니까 외워라
		 */
		startPage = (currentPage-1)/pageLimit*pageLimit + 1;
		// * endPage : 페이지 하단에 보여질 페이징바의 끝 수
		/*
		 * startPage, pageLimit에 영향을 받음 + maxPage에도 영향을 받음
		 * 
		 * - 공식 구하기
		 * 
		 * 	startPage : 1 => endPage : 10
		 * 	startPage : 11 => endPage : 20
		 *  startPage : 21 => endPage : 30
		 *  
		 *  endPage = startPage + pageLimit -1;
		 */
		endPage = startPage + pageLimit -1 ;
		// startPage 21이어서 endPage가 30으로 설정됐지만, maxPage가 25페이지까지만 나온다면?
		// => endPage는 30이 아니라 25로 설정해야한다.
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이지 정보들을 하나로 모아서 vo클래스에 담기
		// 1. 페이징바를 만들때 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		request.setAttribute("pi", pi);
		
		// 2. 현재 사용자가 요청한 페이지(currentPage)에 맞는 게시글 리스트 가져오기
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("list", list);
	
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	
	}

}
