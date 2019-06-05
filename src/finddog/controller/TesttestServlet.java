package finddog.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finddog.model.service.SearchDogService;
import member.model.vo.cityCode;
import openApi.model.dao.OpenApiDao;

/**
 * Servlet implementation class TesttestServlet
 */
@WebServlet(name = "Testtest", urlPatterns = { "/testtest" })
public class TesttestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TesttestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<openApi.model.vo.cityCode> list=null;
		while(true) {
			list=new OpenApiDao().getCityCode();
			if(list.size()>10) {
				break;
			}
		}
		
		for(int i=0;i<17;i++) {
			String city=list.get(i).getCityCode();
			System.out.println(city);
			ArrayList<openApi.model.vo.cityCode> list2= new OpenApiDao().getAreaCode(city);
			
		
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
