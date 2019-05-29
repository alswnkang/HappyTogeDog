package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name = "MemberJoin", urlPatterns = { "/memberJoin" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String time = request.getParameter("time");
		String endTime = request.getParameter("endTime");
		String possibleTime = time+"시~"+endTime+"시";
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String fullAddress = address+" "+detailAddress;
		System.out.println(fullAddress);
		System.out.println(possibleTime);
		Member m = new Member();
		m.setId(request.getParameter("id"));
		m.setPw(request.getParameter("pw"));
		m.setCode(request.getParameter("code"));
		m.setName(request.getParameter("name"));
		m.setPhone(request.getParameter("phone"));
		m.setPost(request.getParameter("post"));
		m.setAddress(fullAddress);
		m.setPossibleTime(possibleTime);
		m.setEmail(request.getParameter("email"));
		m.setMemberLevel(Integer.parseInt(request.getParameter("level")));
		System.out.println("memberjoin");
		System.out.println(m.getMemberLevel());
		try {
			int result = new MemberService().memberJoin(m);
			System.out.println(result);
			if(result > 0) {
				System.out.println("가입완료");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}else {
				System.out.println("가입실패");
				RequestDispatcher rd = request.getRequestDispatcher("/join");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			System.out.println("SQL문 오류");
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
