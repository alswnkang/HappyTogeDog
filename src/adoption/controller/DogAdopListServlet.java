package adoption.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;
import adoption.model.vo.SearchDogPageData;

/**
 * Servlet implementation class DogAdopListServlet
 */
@WebServlet(name = "DogAdopList", urlPatterns = { "/dogAdopList" })
public class DogAdopListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogAdopListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage;
		try {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}catch(NumberFormatException e) {
			reqPage=1;
		}
		SearchDogPageData sdpd = new SearchDogPageData();
		boolean b= true;
		int i=0;
		while(b) {
			i++;
			sdpd = new BookApplyService().dogList(reqPage);
			int totalCount = Integer.parseInt(sdpd.getList().get(0).getOrgNm());	//totalCount
			if(sdpd.getList().size()==12 || sdpd.getList().size()==totalCount%12) { //12개의 리스트를담거나 마지막페이지의 개수일때까지 반복문 실행
				b=false;
			}
		}
		request.setAttribute("sdpd", sdpd);   //pagedata저장
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/dogAdoptionList.jsp");	//유기견 리스트 페이지로 이동
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
