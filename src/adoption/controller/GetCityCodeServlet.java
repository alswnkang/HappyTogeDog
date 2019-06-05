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
import openApi.model.vo.cityCode;

/**
 * Servlet implementation class GetCityCodeServlet
 */
@WebServlet(name = "GetCityCode", urlPatterns = { "/getCityCode" })
public class GetCityCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetCityCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<cityCode> list = new BookApplyService().getCityCode();
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
