package adoption.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adoption.model.service.BookApplyService;
import adoption.model.vo.DogList;

/**
 * Servlet implementation class DogInfoServlet
 */
@WebServlet(name = "DogInfo", urlPatterns = { "/dogInfo" })
public class DogInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String careNm = request.getParameter("careNm");				//보호소명 받기
		String careAddr = request.getParameter("careAddr");			//보호소주소
		String careTel = request.getParameter("careTel");			//보호소 전화번호
		String kindCd = request.getParameter("kindCd");				//품종
		String age = request.getParameter("age");					//강아지 나이
		String sexCd = request.getParameter("sexCd");				//성별
		String specialMark = request.getParameter("specialMark");	//특이사항
		String neuterYn = request.getParameter("neuterYn");			//중성화여부
		String filename = request.getParameter("filename");			//이미지 사진
		System.out.println(careNm);		//보호소 방문시간 받아오기
		System.out.println(specialMark);
		//보호소 방문가능시간 가져오기
		String careTime = new BookApplyService().careTime(careNm);
		DogList dl = new DogList(age,careAddr,careNm,careTel,filename,kindCd,sexCd,specialMark,neuterYn);
		request.setAttribute("dl", dl);
		request.setAttribute("careTime", careTime);		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/adoption/dogInfo.jsp");
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
