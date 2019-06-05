package sponsorship.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sponsorship.model.service.BackupService;
import sponsorship.model.service.OrderService;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderListVO;
import sponsorship.model.vo.OrderUpdate;
import sponsorship.model.vo.ProductVO;
import sponsorship.model.vo.SearchVO;
import sponsorship.model.vo.TotalOrder;

@WebServlet(name = "Admin", urlPatterns = { "/orderView", "/orderList", "/updateOrder", "/updateStatus", "/dataBackup" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		//TODO 관리자 회원일 때만 조회할 수 있도록 처리하기
		
		/* 주문 관리 상세 페이지*/
		if(action.equals("orderView")) {
			String no = request.getParameter("no");
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
				request.setAttribute("prdList", prdList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderView.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 주문 관리 리스트 페이지 */
		}else if(action.equals("orderList")){
			int reqPage;
			try {
				reqPage = Integer.parseInt(request.getParameter("reqPage"));
			}catch (Exception e) {
				reqPage = 1;
			}
			String startDay = request.getParameter("startDay");
			String endDay = request.getParameter("endDay");
			String status = request.getParameter("status");
			String payMethod = request.getParameter("payMethod");
			String searchType = request.getParameter("searchType");
			String searchVal = request.getParameter("searchVal");
			
			SearchVO search = new SearchVO(reqPage, startDay, endDay, status, payMethod, searchType, searchVal,null);
			try {
				request.setAttribute("search", search);
				
				TotalOrder total = new OrderService().totalOrder(search);
				request.setAttribute("total", total);
				
				OrderListVO orderList = new OrderService().selectOrder(search);
				request.setAttribute("orderList", orderList);
				request.setAttribute("prdList", prdList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderList.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 주문 관리 상세 페이지 - 주문상태 및 운송장 번호 변경 */
		}else if(action.equals("updateOrder")){
			String no = request.getParameter("no");
			String deilveryNum = request.getParameter("deilveryNum");
			int status = Integer.parseInt(request.getParameter("status"));
			
			OrderUpdate updateInfo = new OrderUpdate(no, deilveryNum, status);
			try {
				int result = new OrderService().updateOrder(updateInfo);
				if(result>0){
					System.out.println("수정 완료");
				}else{
					System.out.println("수정 실패");
				}
				response.sendRedirect("/orderView?no="+no);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 주문관리 리스트 - 주문상태 변경 */
		}else if(action.equals("updateStatus")){
			String no = request.getParameter("no");
			int status = Integer.parseInt(request.getParameter("status"));
			OrderUpdate updateInfo = new OrderUpdate(no, null, status);
			try {
				int result = new OrderService().updateOrder(updateInfo);

				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				if(result>0){
					System.out.println("수정 완료");
					out.print("success");
				}else{
					System.out.println("수정 실패");
					out.print("fail");
				}
				
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		}else if(action.equals("dataBackup")) {
			
			ArrayList<String> insert = new BackupService().backup();
			String print = "";
			for (String string : insert) {
				print += string+"<br>";
			}
			request.setAttribute("print", print);
			request.getRequestDispatcher("/WEB-INF/data.jsp").forward(request, response);
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
