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

import adoption.model.service.PrintShelterService;
import member.model.vo.Member;

/**
 * Servlet implementation class NameSearchShelterServlet
 */
@WebServlet(name = "NameSearchShelter", urlPatterns = { "/nameSearchShelter" })
public class NameSearchShelterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NameSearchShelterServlet() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key=request.getParameter("keyword");
		System.out.println(key);
		ArrayList<Member> list=null;
		try {
			list = new PrintShelterService().getSearchName(key);
			
			System.out.println(list.get(0).getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/NameSearchShelter.jsp");
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
