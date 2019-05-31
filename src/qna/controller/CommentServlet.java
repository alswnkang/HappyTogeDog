package qna.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.CommentService;
import qna.model.vo.CommentVO;

@WebServlet(name = "Comment", urlPatterns = { "/insertComment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CommentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		if(action.equals("insertComment")) {
			String boardRef = request.getParameter("boardRef");
			String boardCommentContent = request.getParameter("boardCommentContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
			CommentVO comment = new CommentVO(0, 3, boardCommentContent, Integer.parseInt(boardRef), null);
			
			try {
				int result = new CommentService().insertComment(comment);
				if(result>0) {
					System.out.println("등록 성공");
				}else {
					System.out.println("실패");
				}
				response.sendRedirect("/qnaView?boardNo="+boardRef);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
