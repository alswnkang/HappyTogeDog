package qna.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.QnaListVO;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.SearchVO;

@WebServlet(name = "Qna", urlPatterns = { "/qnaList", "/qnaView", "/checkPw" })
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		if(action.equals("qnaList")){
			
			int reqPage;
			try {
				reqPage = Integer.parseInt(request.getParameter("reqPage"));
			}catch (Exception e) {
				reqPage = 1;
			}
			String searchType = request.getParameter("searchType");
			String searchVal = request.getParameter("searchVal");
			SearchVO search = new SearchVO(reqPage, null, null, null, null, searchType, searchVal);
			
			try {
				QnaListVO qnaList = new QnaService().selectQna(search);
				request.setAttribute("qnaList", qnaList);
				request.setAttribute("search", search);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/qnaList.jsp");
				rd.forward(request, response);
				
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
			
		}else if(action.equals("qnaView")){
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			try {
				QnaVO qna = new QnaService().selectQna(boardNo);
				if(qna.getBoardSecret()==1) {
					response.sendRedirect("/checkPw?boardNo="+boardNo);
					return;
				}
				request.setAttribute("qna", qna);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/qnaView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}

		}else if(action.equals("checkPw")){
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String boardPw = request.getParameter("boardPw");
			
			if(boardPw == null) {
				request.setAttribute("boardNo", boardNo);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp");
				rd.forward(request, response);
				
			}else {
				//비밀번호 체크해....
				

				try {
					QnaVO qna = new QnaService().selectQna(boardNo);
					request.setAttribute("qna", qna);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/qnaView.jsp");
					rd.forward(request, response);
				} catch (SQLException e) {
					System.out.println("SQL에러 ㅠ");
				}
				
			}

			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
