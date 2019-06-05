package adoption.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.PrintShelterService;
import adoption.model.vo.Shelter;

/**
 * Servlet implementation class FindShelterServlet
 */
@WebServlet(name = "FindShelter", urlPatterns = { "/findShelter" })
public class FindShelterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindShelterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Shelter> list = null;
		
		try {
			list = new PrintShelterService().getAllList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		int seoul=0;
		int incheon=0;
		int gyung=0;
		int gangwon=0;
		int chungnam=0;
		int daejun=0;
		int chungbuk=0;
		int gyungbuk=0;
		int junbuk=0;
		int gwangju=0;
		int junnam=0;
		int gyunnam=0;
		int jeju=0;
		int busan=0;
		int daegu=0;
		int sejong=0;
		
	
		
		for(int i=0;i<list.size();i++) {
			
			switch (list.get(i).getLevel()) {
			case 1:
				incheon++;
				break;
			case 2:
				seoul++;
				break;
			case 3:
				gyung++;
				break;
			case 4:
				gangwon++;
				break;
			case 5:
				chungnam++;
				break;
			case 6:
				daejun++;
		
				break;
			case 7:
				chungbuk++;
			
				break;
			case 8:
				gyungbuk++;
			
				break;
			case 9:
				junbuk++;
		
				break;
			case 10:
				gwangju++;
			
				break;
			case 11:
				junnam++;
			
				break;
			case 12:
				gyunnam++;
			
				break;
			case 13:
				jeju++;
			
				break;
			case 14:
				busan++;
				
				break;
			case 15:
				daegu++;
				
				break;
			case 16:
				sejong++;
				break;
			}
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, incheon);
		map.put(2,seoul);
		map.put(3,gyung);
		map.put(4,gangwon);
		map.put(5,chungnam);
		map.put(6,daejun);
		map.put(7,chungbuk);
		map.put(8,gyungbuk);
		map.put(9,junbuk);
		map.put(10,gwangju);
		map.put(11,junnam);
		map.put(12,gyunnam);
		map.put(13,jeju);
		map.put(14,busan);
		map.put(15,daegu);
		map.put(16,sejong);
		
		request.setAttribute("map", map);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/findShelter.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
