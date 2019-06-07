package siAdoptionBoardComment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoardComment.model.adoptionBoardCommentService.AdoptionBoardCommentService;
import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;

/**
 * Servlet implementation class SiAdoptionBoardReCommentInsertServlet
 */
@WebServlet(name = "SiAdoptionBoardReCommentInsert", urlPatterns = { "/siAdoptionBoardReCommentInsert" })
public class SiAdoptionBoardReCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiAdoptionBoardReCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int adoptionBoardNo = Integer.parseInt(request.getParameter("adoptionBoardNo"));
		int adoptionBoardCommentType = Integer.parseInt(request.getParameter("adoptionBoardType"));
		int adoptionBoardRef = Integer.parseInt(request.getParameter("adoptionBoardRef"));
		int adoptionBoardCommentRef = Integer.parseInt(request.getParameter("adoptionBoardCommentRef"));
		String adoptionBoardCommentContent = request.getParameter("adoptionBoardCommentContent");
		String adoptionBoardCommentId = request.getParameter("memberId");
		String adoptionBoardCommentName = request.getParameter("memberName");
		AdoptionBoardComment ac = new AdoptionBoardComment();
		ac.setAdoptionBoardRef(adoptionBoardRef);
		ac.setAdoptionBoardCommentRef(adoptionBoardCommentRef);
		ac.setAdoptionBoardCommentType(adoptionBoardCommentType);
		ac.setAdoptionBoardCommentContent(adoptionBoardCommentContent);
		ac.setAdoptionBoardCommentId(adoptionBoardCommentId);
		ac.setAdoptionBoardCommentName(adoptionBoardCommentName);
		int result = new AdoptionBoardCommentService().reCommentInsert(ac);
		if(result>0) {
			request.setAttribute("msg", "대댓글등록성공");
		}else {
			request.setAttribute("msg", "대댓글등록실패");
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
