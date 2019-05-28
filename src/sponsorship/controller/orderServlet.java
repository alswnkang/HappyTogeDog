package sponsorship.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import sponsorship.model.service.OrderService;
import sponsorship.model.vo.OrderInfoVO;

@WebServlet(name = "order", urlPatterns = { "/sponsorship", "/viewProduct", "/order", "/orderIng", "/orderEnd" })
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
			String root = getServletContext().getRealPath("/");//절대경로
			String saveDirectory = root+"upload";
			//int maxSize = 10*1024*1024;
			MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, "utf-8");
					//request, saveDirectory,maxSize,"utf-8",new DefaultFileRenamePolicy());
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
	
			
			OrderInfoVO orderInfo = new OrderInfoVO(no, id, name, phone, payMethod, pay, amount, 0, null, productName, "sysdate", memo, post, address, email, receiveName, receivePhone);

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
				e.printStackTrace();
			}
			
			
			
			
		}else if(action.equals("orderEnd")) {
			String no = request.getParameter("no");
			
			try {
				OrderInfoVO orderInfo = new OrderService().selectOrder(no);
				request.setAttribute("orderInfo", orderInfo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/sponsorship/orderSuc.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
