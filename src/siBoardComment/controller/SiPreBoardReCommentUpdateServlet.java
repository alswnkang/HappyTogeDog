package siBoardComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoardComment.model.boardCommentService.BoardCommentService;

/**
 * Servlet implementation class SiPreBoardReCommentUpdateServlet
 */
@WebServlet(name = "SiPreBoardReCommentUpdate", urlPatterns = { "/siPreBoardReCommentUpdate" })
public class SiPreBoardReCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiPreBoardReCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCommentContent = request.getParameter("boardCommentContent");
		System.out.println(boardCommentContent);
		int boardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));	
		System.out.println(boardCommentNo);
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));	
		System.out.println(boardCommentRef);	
		int result = new BoardCommentService().reCommentUpdate(boardCommentContent,boardCommentNo,boardCommentRef);
		String view = "";
		if(result>0) {
			request.setAttribute("msg", "대댓글 수정 성공");
			view = "/WEB-INF/siViews/common/siMsg.jsp";
		}else {
			request.setAttribute("msg", "대댓글 수정 실패");
			view = "/WEB-INF/siViews/common/siMsg.jsp";
		}
		request.setAttribute("loc", "/siPreBoardView?boardNo="+boardNo);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
