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
			
			String amount = request.getParameter("amount");
			String pay = request.getParameter("pay");

			//OrderInfoVO orderInfo = new OrderInfoVO(no, id, name, phone, payMethod, pay, amount, status, deilveryNum, productName, sponDate, memo, post, address, email, receiveName, receivePhone);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderSuc.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
