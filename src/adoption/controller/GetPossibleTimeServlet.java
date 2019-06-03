package adoption.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import adoption.model.service.BookApplyService;

/**
 * Servlet implementation class GetPossibleTimeServlet
 */
@WebServlet(name = "GetPossibleTime", urlPatterns = { "/getPossibleTime" })
public class GetPossibleTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetPossibleTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String visitDate = request.getParameter("visitDate");
		String careNm = request.getParameter("careNm");
		System.out.println("방문날짜 : "+visitDate);
		System.out.println("보호소명 : "+careNm);
		ArrayList<String> list = new BookApplyService().possibleTime(visitDate, careNm);
		System.out.println("4Servlet");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		new Gson().toJson(list,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
