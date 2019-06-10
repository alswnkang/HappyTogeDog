package main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siAdoptionBoard.model.adoptionBoardService.AdoptionBoardService;
import siAdoptionBoard.model.adoptionBoardVo.AdoptionBoard;
import siNotice.model.noticeService.NoticeService;
import siNotice.model.noticeVo.Notice;
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
		/* 메인 :: 후기게시판 게시글 노출 */
		ArrayList<AdoptionBoard> adoptionBoardList = new AdoptionBoardService().adoptionBoardList();
		request.setAttribute("adoptionBoardList", adoptionBoardList);
		
		/* 메인 :: 공지사항 게시글 노출 */
		ArrayList<Notice> noticeList = new NoticeService().noticeList();
		request.setAttribute("noticeList", noticeList);
		
		
		/* 메인 :: 후원하기 상품 노출 */
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		
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
