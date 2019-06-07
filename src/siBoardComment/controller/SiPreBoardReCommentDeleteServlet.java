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
 * Servlet implementation class SiPreBoardReCommentDeleteServlet
 */
@WebServlet(name = "SiPreBoardReCommentDelete", urlPatterns = { "/siPreBoardReCommentDelete" })
public class SiPreBoardReCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiPreBoardReCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		int result = new BoardCommentService().reCommentDelete(boardCommentNo,boardCommentRef);
		if(result>0) {
			request.setAttribute("msg", "대댓글삭제성공");
		}else {
			request.setAttribute("msg", "대댓글삭제실패");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/common/siMsg.jsp");
		request.setAttribute("loc", "/siPreBoardView?boardNo="+boardNo);
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
