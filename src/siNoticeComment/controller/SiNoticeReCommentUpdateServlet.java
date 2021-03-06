package siNoticeComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siNoticeComment.model.noticeCommentService.NoticeCommentService;

/**
 * Servlet implementation class SiNoticeReCommentUpdateServlet
 */
@WebServlet(name = "SiNoticeReCommentUpdate", urlPatterns = { "/siNoticeReCommentUpdate" })
public class SiNoticeReCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiNoticeReCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeCommentContent = request.getParameter("noticeCommentContent");
		int noticeCommentNo = Integer.parseInt(request.getParameter("noticeCommentNo"));	
		int noticeCommentRef = Integer.parseInt(request.getParameter("noticeCommentRef"));	
		int result = new NoticeCommentService().reCommentUpdate(noticeCommentContent,noticeCommentNo,noticeCommentRef);
		String view = "";
		if(result>0) {
			view = "/siNoticeView?noticeNo="+noticeNo;
		}else {
			view = "/siNoticeView?noticeNo="+noticeNo;
		}
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
