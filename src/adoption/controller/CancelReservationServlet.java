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
 * Servlet implementation class CancelReservationServlet
 */
@WebServlet(name = "CancelReservation", urlPatterns = { "/cancelReservation" })
public class CancelReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("신청취소 서블릿/ 넘버: "+no);
		int result = new BookApplyService().cancelReservation(no);
		if(result>0) {
			request.setAttribute("msg", "방문예약 신청 취소 완료");
		}else {
			request.setAttribute("msg", "방문예약 신청 취소 실패");
		}
		request.setAttribute("loc", "/reservationMypage");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
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
