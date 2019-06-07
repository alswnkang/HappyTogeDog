package siAdoptionBoardComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoardComment.model.adoptionBoardCommentService.AdoptionBoardCommentService;

/**
 * Servlet implementation class SiAdoptionBoardReCommentDeleteServlet
 */
@WebServlet(name = "SiAdoptionBoardReCommentDelete", urlPatterns = { "/siAdoptionBoardReCommentDelete" })
public class SiAdoptionBoardReCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardReCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int adoptionBoardCommentNo = Integer.parseInt(request.getParameter("adoptionBoardCommentNo"));
		int adoptionBoardNo = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		int adoptionBoardCommentRef = Integer.parseInt(request.getParameter("adoptionBoardCommentRef"));
		int result = new AdoptionBoardCommentService().reCommentDelete(adoptionBoardCommentNo,adoptionBoardCommentRef);
		if(result>0) {
			request.setAttribute("msg", "대댓글삭제성공");
		}else {
			request.setAttribute("msg", "대댓글삭제실패");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/siViews/common/siMsg.jsp");
		request.setAttribute("loc", "/siAdoptionBoardView?adoptionBoardNo="+adoptionBoardNo);
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
