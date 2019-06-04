package adoption.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.dao.PrintShelterDao;
import adoption.model.service.PrintShelterService;
import adoption.model.vo.Shelter;
import adoption.model.vo.ShelterPageData;

/**
 * Servlet implementation class PrintShelterServlet
 */
@WebServlet(name = "PrintShelter", urlPatterns = { "/printShelter" })
public class PrintShelterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintShelterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int city = Integer.parseInt(request.getParameter("city"));
		int page;
		try {	
			page = Integer.parseInt(request.getParameter("page"));
		}catch (NumberFormatException e) {
			// TODO: handle exception
			page=1;
		}
		System.out.println(city);
		ShelterPageData spd = new ShelterPageData();
		
		try {
			spd = new PrintShelterService().printShelter(city,page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(spd.getList().size());
		request.setAttribute("spd", spd);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/findShelter.jsp");
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
