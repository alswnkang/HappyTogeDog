package volunteer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryApplyBoard;

/**
 * Servlet implementation class VoluntaryApplyListServlet
 */
@WebServlet(name = "VoluntaryApplyList", urlPatterns = { "/voluntaryApplyList" })
public class VoluntaryApplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VoluntaryApplyListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		ArrayList<VoluntaryApplyBoard> list = new VoluntaryService().myVoluntaryList(id);
		
		String view="";
		if(!list.isEmpty()) {
			request.setAttribute("list", list);
			view = "/WEB-INF/volunteer/voluntaryApplyList.jsp?id="+list.get(0).getId();
		}
		else {
			request.setAttribute("msg", "해당 봉사활동 신청 공고가 존재하지 않습니다.");
			request.setAttribute("loc", "/totalMyPage");
			view = "/WEB-INF/common/msg.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
