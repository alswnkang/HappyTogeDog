package openApi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import openApi.model.dao.OpenApiDao;
import openApi.model.vo.CareCode;
import openApi.model.vo.cityCode;

/**
 * Servlet implementation class SearchCityCodeServlet
 */
@WebServlet(name = "SearchCityCode", urlPatterns = { "/searchCityCode" })
public class SearchCityCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCityCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<cityCode> list=null;
		boolean b= true;
		while(b) {
			list= new OpenApiDao().getCityCode();
			System.out.println(list.size());
			if(!list.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/member/cityCode.jsp");
				rd.forward(request, response);
			}
		
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
