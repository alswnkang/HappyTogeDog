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
		prdList.add(new ProductVO("0", "에코에코에코백", "76896814691427225_1127979769.jpg", "20000"));
		prdList.add(new ProductVO("1", "배찌뱃지뻇지", "39066105050978558_-1615663619.jpg", "10000"));
		prdList.add(new ProductVO("2", "달력달력달력", "prd_img01.jpg", "15000"));
		
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
