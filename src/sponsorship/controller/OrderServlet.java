package sponsorship.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import member.model.vo.Member;
import sponsorship.model.service.OrderService;
import sponsorship.model.vo.*;

@WebServlet(name = "order", urlPatterns = {  "/order", "/orderIng", "/orderEnd",
		"/findOrder", "/myOrder", "/myOrderList" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO(0));
		prdList.add(new ProductVO(1));
		prdList.add(new ProductVO(2));
		
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
	
		/* 주문서 작성 페이지 */	
		if(action.equals("order")) {
			//TODO 상품코드 없을때 예외처리하기
			int prdCode = Integer.parseInt(request.getParameter("prdCode"));
			String amount = request.getParameter("amount");
			String price = request.getParameter("price");

			request.setAttribute("amount", amount);
			request.setAttribute("price", price);
			request.setAttribute("prd", prdList.get(prdCode));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderForm.jsp");
			rd.forward(request, response);
			
		/* 주문등록(AJAX) */
		}else if(action.equals("orderIng")) {
			//TODO 예외처리 하기
			String root = getServletContext().getRealPath("/");//절대경로
			String saveDirectory = root+"upload";
			MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, "utf-8");

			String no = mRequest.getParameter("orderNo");
			String id = mRequest.getParameter("id");
			String name = mRequest.getParameter("name");
			String phone = mRequest.getParameter("phone1")+"-"+mRequest.getParameter("phone2")+"-"+mRequest.getParameter("phone3");
			String payMethod = mRequest.getParameter("payMethod");
			int pay = Integer.parseInt(mRequest.getParameter("pay"));
			int amount = Integer.parseInt(mRequest.getParameter("amount"));
			String productName = mRequest.getParameter("productName");
			String memo = mRequest.getParameter("memo");
			String post = mRequest.getParameter("post");
			String address = mRequest.getParameter("address")+"//"+mRequest.getParameter("address2");
			String email = mRequest.getParameter("email");
			String receiveName = mRequest.getParameter("receiveName");
			String receivePhone = mRequest.getParameter("receivePhone1")+"-"+mRequest.getParameter("receivePhone2")+"-"+mRequest.getParameter("receivePhone3");
			String vbankName = mRequest.getParameter("vbankName");
			String vbankNum = mRequest.getParameter("vbankNum");
			String vbankHolder = mRequest.getParameter("vbankHolder");
			String vbankDate = mRequest.getParameter("vbankDate");
			OrderInfoVO orderInfo = new OrderInfoVO(0,no, id, name, phone, payMethod, pay, amount, 0, null, productName, "sysdate", memo, post, address, email, receiveName, receivePhone,vbankName,vbankNum,vbankHolder,vbankDate);

			try {
				int result =  new OrderService().insertOrder(orderInfo);
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				if(result>0){
					out.print("/orderEnd?no="+no);
				}else{
					out.print("fail");
				}
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 주문 완료 페이지 */
		}else if(action.equals("orderEnd")) {
			//TODO 예외처리 필요
			String no = request.getParameter("no");
			
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderSuc.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 비회원 주문 조회  */
		}else if(action.equals("findOrder")) {
			String no = request.getParameter("no");
			String phone = request.getParameter("phone");
			try {
				int result = new OrderService().findOrder(no,phone);
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				if(result>0){
					out.print("/myOrder?no="+no);
				}else{
					out.print("fail");
				}
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
			
		/* 나의 주문내역 상세 페이지 */
		}else if(action.equals("myOrder")) {
			String no = request.getParameter("no");
			
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
				request.setAttribute("prdList", prdList);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/myOrder.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
		
		/* 나의 후원내역 페이지 */
		}else if(action.equals("myOrderList")){
			if(member != null) {
				int reqPage;
				try {
					reqPage = Integer.parseInt(request.getParameter("reqPage"));
				}catch (Exception e) {
					reqPage = 1;
				}
				String startDay = request.getParameter("startDay");
				String endDay = request.getParameter("endDay");
				
				/* 기본 검색 기간 1개월로 설정 */
				Date today = new Date();
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				if(startDay == null) {
					Date sday = new Date();
					sday.setMonth(sday.getMonth()-1);
					
					startDay = f.format(sday);
				}
				if(endDay == null) {
					endDay = f.format(today);
				}
				
				try {
					SearchVO search = new SearchVO(reqPage, startDay, endDay, null, null, "id", member.getId(),null);
					request.setAttribute("search", search);
						
					OrderListVO orderList = new OrderService().selectOrder(search);
					request.setAttribute("orderList", orderList);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/myOrderList.jsp");
					rd.forward(request, response);
				} catch (SQLException e) {
					System.out.println("SQL에러 ㅠ");
				}	
			}else {
				request.setAttribute("msg", "로그인 후 이용해주세요");
				request.setAttribute("loc", "/member/login.jsp");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
