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
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
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
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		
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
			int boardNo;
			try {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}catch(Exception e){
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/qnaList");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp");
				rd.forward(request, response);
				return;
			}
			
			try {
				QnaVO qna = new QnaService().selectQna(boardNo);
				
				if(qna.getBoardSecret()==1) {//비밀글이면 비밀번호 확인 페이지로 보내버리기
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
			int boardNo;
			try {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}catch(Exception e){
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/qnaList");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp");
				rd.forward(request, response);
				return;
			}
			String boardPw = request.getParameter("boardPw");
			
			if(boardPw == null) {//비밀번호 입력페이지로 이동
				request.setAttribute("boardNo", boardNo);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp");
				rd.forward(request, response);
				
			}else {//입력한 비밀번호 체크해서 일치하면 뷰페이지로 이동
				try {
					QnaVO qna = new QnaService().checkPw(boardNo,boardPw);
					if(qna != null) {
						request.setAttribute("qna", qna);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/qnaView.jsp");
						rd.forward(request, response);
						
					}else {
						request.setAttribute("boardNo", boardNo);
						request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp");
						rd.forward(request, response);
					}
					
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
