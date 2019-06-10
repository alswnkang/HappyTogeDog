package siNoticeComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoardComment.model.boardCommentService.BoardCommentService;
import siNoticeComment.model.noticeCommentService.NoticeCommentService;

/**
 * Servlet implementation class SiNoticeReCommentDeleteServlet
 */
@WebServlet(name = "SiNoticeReCommentDelete", urlPatterns = { "/siNoticeReCommentDelete" })
public class SiNoticeReCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeReCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeCommentNo = Integer.parseInt(request.getParameter("noticeCommentNo"));
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int noticeCommentRef = Integer.parseInt(request.getParameter("noticeCommentRef"));
		int result = new NoticeCommentService().reCommentDelete(noticeCommentNo,noticeCommentRef);
		if(result>0) {
			request.setAttribute("msg", "대댓글삭제성공");
		}else {
			request.setAttribute("msg", "대댓글삭제실패");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/common/siMsg.jsp");
		request.setAttribute("loc", "/siNoticeView?noticeNo="+noticeNo);
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
