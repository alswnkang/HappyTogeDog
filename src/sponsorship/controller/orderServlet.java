package sponsorship.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sponsorship.model.vo.OrderInfoVO;

@WebServlet(name = "order", urlPatterns = { "/sponsorship", "/viewProduct", "/order", "/4" })
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public orderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		

		if(action.equals("sponsorship")) {	
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/productList.jsp");
			rd.forward(request, response);
			
		}else if(action.equals("viewProduct")) {	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/productDetail.jsp");
			rd.forward(request, response);
		}else if(action.equals("order")) {
			
			String amount = request.getParameter("amount");
			String price = request.getParameter("price");

			request.setAttribute("amount", amount);
			request.setAttribute("price", price);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderForm.jsp");
			rd.forward(request, response);
		}else if(action.equals("orderIng")) {
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
			String payMethod = request.getParameter("payMethod");
			int pay = Integer.parseInt(request.getParameter("pay"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			String productName = request.getParameter("productName");
			String memo = request.getParameter("memo");
			String post = request.getParameter("post");
			String address = request.getParameter("address")+" "+request.getParameter("address2");
			String email = request.getParameter("email");
			String receiveName = request.getParameter("receiveName");
			String receivePhone = request.getParameter("receivePhone1")+"-"+request.getParameter("receivePhone2")+"-"+request.getParameter("receivePhone3");
			
			System.out.println();
			
			OrderInfoVO orderInfo = new OrderInfoVO(0, id, name, phone, payMethod, pay, amount, 0, null, productName, "sysdate", memo, post, address, email, receiveName, receivePhone);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderSuc.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
