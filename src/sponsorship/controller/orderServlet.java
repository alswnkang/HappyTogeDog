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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.QnaListVO;
import qna.model.vo.QnaVO;
import sponsorship.model.service.OrderService;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderListVO;
import sponsorship.model.vo.OrderUpdate;
import sponsorship.model.vo.ProductVO;
import sponsorship.model.vo.SearchVO;
import sponsorship.model.vo.TotalOrder;

@WebServlet(name = "order", urlPatterns = { "/sponsorship", "/viewProduct", "/order", "/orderIng",
		"/orderEnd", "/findOrder", "/myOrder", "/orderList", "/myOrderList",
		"/orderView", "/updateOrder", "/updateStatus"})
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public orderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		
		ArrayList<ProductVO> prdList = new ArrayList<ProductVO>();
		prdList.add(new ProductVO("0", "해피투게DOG 후원 에코백", "76896814691427225_1127979769.jpg", "20000","&lt;포인핸드 라이프백&gt; 에는 사람과 동물이 손을 맞잡은 포인핸드의 의미가 담겨있습니다.포인핸드는 굿즈를 통해 일상 속에서 사지않고 입양하는 문화를 알리고, 수익금으로 유기동물 입양을 위한 환경을 만들어가고 있습니다. \r\n" + 
				"따듯한 봄, 포인핸드 라이프백과 함께하세요."));
		prdList.add(new ProductVO("1", "해피투게DOG 후원 뱃지 무지개 다리", "39066105050978558_-1615663619.jpg", "10000", "상세설명"));
		prdList.add(new ProductVO("2", "해피투게DOG 캘린더", "prd_img01.jpg", "15000","상세설명"));
		
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");

		/* 후원상품 리스트 페이지 */
		if(action.equals("sponsorship")) {
			request.setAttribute("prdList", prdList);
			request.getRequestDispatcher("/WEB-INF/sponsorship/productList.jsp").forward(request, response);
		/* 후원상품 상세 페이지 */
		}else if(action.equals("viewProduct")) {
			String code = request.getParameter("code");
			if(code==null){
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/sponsorship");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				return;
				
			}else{
				int prdCode = Integer.parseInt(code);
				request.setAttribute("prd", prdList.get(prdCode));		
				try {
					ArrayList<QnaVO> qnaList = new QnaService().selectQna(1,5,new SearchVO(0, null, null, null, null, null, null, code));
					request.setAttribute("qnaList", qnaList);
				} catch (SQLException e) {
					System.out.println("SQL에러 ㅠ");
				}
				
				request.getRequestDispatcher("/WEB-INF/sponsorship/productDetail.jsp").forward(request, response);
			}
		/* 주문 관리 리스트 페이지 */
		}else if(action.equals("orderList")){
			
			//TODO 관리자 회원일 때만 조회할 수 있도록 처리하기
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
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderList.jsp");
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
				//TODO 오늘 날짜 자동으로 들어가게 추가하기
				if(startDay == null) {
					startDay = "2019-05-03";
				}
				if(endDay == null) {
					endDay = "2019-06-03";
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
			
		/* 주문서 작성 페이지 */	
		}else if(action.equals("order")) {
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
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/myOrder.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
		/* 주문 관리 상세 페이지*/
		}else if(action.equals("orderView")) {
			String no = request.getParameter("no");
			
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderView.jsp");
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
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
