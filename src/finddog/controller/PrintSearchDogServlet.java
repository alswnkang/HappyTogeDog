package finddog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.vo.SearchDogPageData;
import finddog.model.service.SearchDogService;
import siBoard.model.boardVo.BoardPageData;

/**
 * Servlet implementation class PrintSearchDogServlet
 */
@WebServlet(name = "printSearchDog", urlPatterns = { "/printSearchDog" })
public class PrintSearchDogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintSearchDogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String kind = request.getParameter("kind");
		String cityCode= request.getParameter("happenCity");
		
	
		
		
		String sDay=request.getParameter("startDay");
		String eDay=request.getParameter("endDay");
		System.out.println(sDay+","+eDay);
		int count =0;
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(NumberFormatException e){ // 처음요청시 숫자가 아니므로 1을 줘서 첫페이지로 향하게한다.
			page = 1;
		}
		boolean b= true;
		SearchDogPageData sdpd = new SearchDogPageData();
		while(b) {
			sdpd = new SearchDogService().selectList(page,sDay,eDay,kind,cityCode);
			if(sdpd.getList().size()<=8) { //8개의 리스트를답을때까지 반복
				count++;
				b=false;
				if(count==10) {
					b=false;
				}
			}
		}
		
		
		
		BoardPageData sdpd2 = new BoardPageData();
		
		sdpd2 = new SearchDogService().selectListDB(page, sDay, eDay, kind, cityCode);
		
		
		
		
		
		request.setAttribute("sdpd2", sdpd2);
		request.setAttribute("sdpd", sdpd);   //pagedata저장
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/finddog/searchDog.jsp");
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
