package adoption.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;

/**
 * Servlet implementation class UpdateStatusServlet
 */
@WebServlet(name = "UpdateStatus", urlPatterns = { "/careUpdateStatus" })
public class UpdateStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int status = Integer.parseInt(request.getParameter("status"));
		int no = Integer.parseInt(request.getParameter("no"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("ebdDay");
		System.out.println("updateStatusServlet no : "+no);
		System.out.println("updateStatusServlet reqPage : "+reqPage);
		int result = new BookApplyService().updateStatus(status,no);
		if(result>0) {
			request.setAttribute("no", no);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("msg", "상태 수정 완료");
			RequestDispatcher rd = request.getRequestDispatcher("/reservationView");
			rd.forward(request, response);
		}else {
			request.setAttribute("no", no);
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
			request.setAttribute("reqPage", reqPage);
			request.setAttribute("msg", "상태 수정 실패");
			RequestDispatcher rd = request.getRequestDispatcher("/reservationView");
			rd.forward(request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
