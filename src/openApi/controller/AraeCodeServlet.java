package openApi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import openApi.model.dao.OpenApiDao;
import openApi.model.vo.cityCode;
import openApi.model.vo.getCareCode;

/**
 * Servlet implementation class AraeCodeServlet
 */
@WebServlet(name = "AraeCode", urlPatterns = { "/areaCode" })
public class AraeCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AraeCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String code=request.getParameter("value");
		
		
		ArrayList<cityCode> list = new OpenApiDao().getAreaCode(code);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
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
