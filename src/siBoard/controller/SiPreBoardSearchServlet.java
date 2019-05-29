package siBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siBoard.model.boardService.BoardService;
import siBoard.model.boardVo.Board;
import siBoard.model.boardVo.BoardPageData;

/**
 * Servlet implementation class SiPreBoardSearchServlet
 */
@WebServlet(name = "SiPreBoardSearch", urlPatterns = { "/siPreBoardSearch" })
public class SiPreBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SiPreBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String searchType = request.getParameter("searchWord");
		String searchKeyword = request.getParameter("keyword");
		ArrayList<Board> list = new BoardService().boardSearch(searchType,searchKeyword);
		// 여기서 검색부터 다시 해야됨
		String view = "";
		if(list.size()>0){
			request.setAttribute("list", list);
			view = "/WEB-INF/siViews/board/siPreBoardSearchList.jsp";
		}else {
			request.setAttribute("msg", "일치하는 정보가 없습니다.");
			request.setAttribute("loc", "/siPreBoard");
			view = "/WEB-INF/siViews/common/siMsg.jsp";
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
