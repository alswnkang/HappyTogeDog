package member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.mail.MailAuth;
import member.model.Service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindUserServlet
 */
@WebServlet(name = "FindUser", urlPatterns = { "/findUser" })
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserServlet() {
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
		String email = request.getParameter("email");
		Member m = null;
		try {
		m = new MemberService().findUser(email);
		} catch (SQLException e1) {
			System.out.println("findUser SQL문 오류");
		}
		if(m != null) {
		try {
			
			Random rnd =new Random();
			StringBuffer buf =new StringBuffer();
			for(int i=0;i<12;i++){
			    if(rnd.nextBoolean()){
			        buf.append((char)((int)(rnd.nextInt(26))+97));
			    }else{
			        buf.append((rnd.nextInt(10))); 
			    }
			}
			String pw = buf.toString();
			System.out.println(pw);
			int result = new MemberService().temporaryPassword(pw,email);
			m = new MemberService().findUser(email);
			Properties prop = System.getProperties();
			prop.put("mail.smtp.starttls.enable", "true");				//로그인시 TLS를 사용할 것인지 설정
			prop.put("mail.smtp.host", "smtp.naver.com");				// 이메일 발송을 처리해줄 SMTP서버
			prop.put("mail.smtp.debug", "true");						
			prop.put("mail.smtp.auth","true");							// SMTP서버의 인증을 사용한다
			prop.put("mail.smtp.port", "587");							// TLS의 포트번호, SSL의 포트번호는 465
			Authenticator auth = new MailAuth();		//MailAuth.class에서 Authenticator를 상속
			Session session = Session.getDefaultInstance(prop,auth);	//getDefaultInstance의 첫 번째 파라미터는 앞에 설정한 메일 처리 환경
			MimeMessage msg = new MimeMessage(session);			//MimeMessage는 Message(추상) 클래스를 상속받은 인터넷 메일을 위한 클래스이며 위에서 생성한 세션을 담아 msg객체를 생성한다
			
			try {
				msg.setSentDate(new Date());		//보내는 날짜 지정
				msg.setFrom(new InternetAddress("wlsdh104@naver.com","해피투게독"));	//Message 클래스의 setFrom()메소드를 사용하여 발송자를 지정한다. 발송자의 메일, 발송자명 InternetAddress클래스는 이메일 주소를 나타날 때 사용하는 클래스이다
				InternetAddress to = new InternetAddress(email);		//수신자의 메일
				msg.setRecipient(Message.RecipientType.TO, to);			//Message 클래스의 setRecipient()메소드를 사용하여 수신자를 설정한다. setRecipient()메소드로 수신자,참조,숨은 참조 설정이 가능하다.
				msg.setSubject("제목","utf-8");		//메일의 제목
				msg.setText("아이디는 "+m.getId()+" 입니다.\n"+"임시 비밀번호는 "+m.getPw()+" 입니다."
						+ "\n 로그인후 비밀번호를 꼭 변경해 주세요","utf-8");		//메일의 내용
				Transport.send(msg);		//Transport는 메일을 최종적으로 보내는 클래스로 메일을 보내는 부분이다.
				
			} catch (MessagingException e) {			//메일 계정인증 관련 예외 처리
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {		//지원되지 않는 인코딩을 사용할 경우 예외 처리
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
			request.setAttribute("msg", "아이디/비밀번호 메일을 발송했습니다");
			request.setAttribute("loc", "/member/login.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			System.out.println("SQL문 오류");
		}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/common/msg.jsp");
			request.setAttribute("msg", "등록된 이메일이 없습니다");
			request.setAttribute("loc", "/member/findUser.jsp");
			rd.forward(request, response);
		}
		
		
	}

	private String String(StringBuffer buf) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}