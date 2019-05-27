package adoption.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.vo.BookApply;

/**
 * Servlet implementation class VisitReservationCompleteServlet
 */
@WebServlet(name = "VisitReservationComplete", urlPatterns = { "/visitReservationComplete" })
public class VisitReservationCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitReservationCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookApply ba = new BookApply();
		ba.setCode(request.getParameter("code"));
		ba.setName(request.getParameter("name"));
		ba.setId(request.getParameter("id"));
		ba.setPhone(request.getParameter("phone"));
		ba.setYard(request.getParameter("yard"));
		ba.setAnimal(request.getParameter("animal"));
		ba.setFamily(request.getParameter("family"));
		ba.setExperience(request.getParameter("experience"));
		ba.setAvgTime(request.getParameter("avgTime"));
		ba.setVisitDate(Date.valueOf(request.getParameter("visitDate")));
		ba.setVisitTime(Integer.parseInt(request.getParameter("visitTime")));
		int result = new BookApply().reservation(ba);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
