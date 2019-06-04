package main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sponsorship.model.vo.ProductVO;
import volunteer.model.service.VoluntaryService;
import volunteer.model.vo.VoluntaryRegister;

@WebServlet(name = "Main", urlPatterns = { "/main" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 메인 :: 후원하기 상품 노출 */
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO("0", "해피투게DOG 후원 에코백", "76896814691427225_1127979769.jpg", "20000","&lt;포인핸드 라이프백&gt; 에는 사람과 동물이 손을 맞잡은 포인핸드의 의미가 담겨있습니다.포인핸드는 굿즈를 통해 일상 속에서 사지않고 입양하는 문화를 알리고, 수익금으로 유기동물 입양을 위한 환경을 만들어가고 있습니다. \r\n" + 
				"따듯한 봄, 포인핸드 라이프백과 함께하세요."));
		prdList.add(new ProductVO("1", "해피투게DOG 후원 뱃지 무지개 다리", "39066105050978558_-1615663619.jpg", "10000", "상세설명"));
		prdList.add(new ProductVO("2", "해피투게DOG 캘린더", "prd_img01.jpg", "15000","상세설명"));
		
		request.setAttribute("prdList", prdList);
		
		/* 메인 :: 봉사활동 게시판 공고 노출 */
		ArrayList<VoluntaryRegister> volunList = new VoluntaryService().mainVoluntaryList();
		request.setAttribute("volunList", volunList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
