package qna.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.model.vo.Member;
import qna.model.service.CommentService;
import qna.model.service.QnaService;
import qna.model.vo.CommentVO;
import qna.model.vo.QnaListVO;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.SearchVO;

@WebServlet(name = "Qna", urlPatterns = { "/qnaList", "/qnaView", "/checkPw", "/regiQna", "/insertQna", "/myQnaList" })
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] url = request.getRequestURL().toString().split("/");
		String action = url[url.length-1];
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("member");
		
		/* Q&A 게시판 리스트 */
		if(action.equals("qnaList")){
			
			int reqPage;
			try {
				reqPage = Integer.parseInt(request.getParameter("reqPage"));
			}catch (Exception e) {
				reqPage = 1;
			}
			String searchType = request.getParameter("searchType");
			String searchVal = request.getParameter("searchVal");
			SearchVO search = new SearchVO(reqPage, null, null, null, null, searchType, searchVal,null);
			
			try {
				QnaListVO qnaList = new QnaService().selectQna(search);
				request.setAttribute("qnaList", qnaList);
				
				request.setAttribute("search", search);
				request.getRequestDispatcher("/WEB-INF/qna/qnaList.jsp").forward(request, response);
				
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}
		/* 나의 Q&A 게시판 리스트 */	
		}else if(action.equals("myQnaList")){
			if(member != null) {
				int reqPage;
				try {
					reqPage = Integer.parseInt(request.getParameter("reqPage"));
				}catch (Exception e) {
					reqPage = 1;
				}
				//String searchType = request.getParameter("searchType");
				//String searchVal = request.getParameter("searchVal");
				SearchVO search = new SearchVO(reqPage, null, null, null, null, "board_id", member.getId(),null);
				
				try {
					QnaListVO qnaList = new QnaService().selectQna(search);
					request.setAttribute("qnaList", qnaList);
					request.setAttribute("search", search);
					request.getRequestDispatcher("/WEB-INF/qna/myQnaList.jsp").forward(request, response);
					
				} catch (SQLException e) {
					System.out.println("SQL에러 ㅠ");
				}
		}else {
			request.setAttribute("msg", "로그인 후 이용해주세요");
			request.setAttribute("loc", "/member/login.jsp");
			request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
		}
		/* Q&A 뷰 페이지 */	
		}else if(action.equals("qnaView")){
			int boardNo;
			try {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}catch(Exception e){
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/qnaList");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				return;
			}
			
			try {
				QnaVO qna = new QnaService().selectQna(boardNo);
				if(qna.getBoardSecret()==1) {//비밀글인데

					if(member==null) {//로그인 안되어있는 상태에서
						if(qna.getBoardId()==null) {//비회원이 쓴 글이면
							response.sendRedirect("/checkPw?boardNo="+boardNo);//비밀번호 체크로 보내버리기
							return;
						}else {//회원이 쓴 글이면
							request.setAttribute("msg", "접근 권한이 없습니다.");
							request.setAttribute("loc", "/member/login.jsp");//로그인 페이지로 보내기
							request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
							return;
						}
					}else if(member.getMemberLevel()==2){
						//관리자는 모든 글 조회 가능
					}else if(!member.getId().equals(qna.getBoardId())) {//로그인 상태에서 자신이 쓴 글 아닌데
						if(qna.getBoardId()==null) {//비회원이 쓴 글이면
							response.sendRedirect("/checkPw?boardNo="+boardNo);//비밀번호 체크로 보내기
							return;
						}else {//회원이 쓴 글이면
							request.setAttribute("msg", "접근 권한이 없습니다.");
							request.setAttribute("loc", "/qnaList");
							request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
							return;
						}
						
					}
					
					//로그인 상태이고, 자신이 쓴글이면 보여주기....
				}

				request.setAttribute("pageName", request.getParameter("pageName"));
				String searchType = request.getParameter("searchType");
				String searchVal = request.getParameter("searchVal");
				SearchVO search = new SearchVO(Integer.parseInt(request.getParameter("reqPage")), null, null, null, null, searchType, searchVal,null);
				request.setAttribute("search", search);
				
				request.setAttribute("qna", qna);
				CommentVO comment = new CommentService().selectComment(boardNo);
				request.setAttribute("comment", comment);
				request.getRequestDispatcher("/WEB-INF/qna/qnaView.jsp").forward(request, response);
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}

		}else if(action.equals("checkPw")){
			int boardNo;
			try {
				boardNo = Integer.parseInt(request.getParameter("boardNo"));
			}catch(Exception e){
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("loc", "/qnaList");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				return;
			}
			String boardPw = request.getParameter("boardPw");
			
			if(boardPw == null) {//비밀번호 입력페이지로 이동
				request.setAttribute("boardNo", boardNo);
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				
			}else {//입력한 비밀번호 체크해서 일치하면 뷰페이지로 이동
				try {
					QnaVO qna = new QnaService().checkPw(boardNo,boardPw);
					if(qna != null) {
						request.setAttribute("pageName", "/qnaList");
						request.setAttribute("qna", qna);
						request.getRequestDispatcher("/WEB-INF/qna/qnaView.jsp").forward(request, response);
						
					}else {
						request.setAttribute("boardNo", boardNo);
						request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
						request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
					}
					
				} catch (SQLException e) {
					System.out.println("SQL에러 ㅠ");
				}
				
			}

			
		}else if(action.equals("regiQna")) {
			String prdCode = request.getParameter("prdCode");
			request.setAttribute("prdCode", prdCode);
			request.getRequestDispatcher("/WEB-INF/qna/qnaRegister.jsp").forward(request, response);
			
		}else if(action.equals("insertQna")) {
			if(!ServletFileUpload.isMultipartContent(request)) {
				request.setAttribute("msg", "Q&A 작성 오류 [enctype]");
				request.setAttribute("loc", "/qnaList");
				request.getRequestDispatcher("/WEB-INF/qna/passwordPage.jsp").forward(request, response);
				return ;
			}
			String saveDirectory = getServletContext().getRealPath("/")+"upload/qna";
			MultipartRequest mRequest = new MultipartRequest(request, saveDirectory,10*1024*1024,"utf-8",new DefaultFileRenamePolicy());
			
			
			int boardType;
			try {
				boardType = Integer.parseInt(mRequest.getParameter("boardType"));
			}catch(Exception e){
				boardType = 3;
			}
			String boardTitle = mRequest.getParameter("boardTitle");
			String boardId = mRequest.getParameter("boardId");
			String boardName = mRequest.getParameter("boardName");
			int boardSecret;
			try {
				boardSecret = Integer.parseInt(mRequest.getParameter("boardSecret"));
			}catch(Exception e){
				boardSecret = 0;
			}
			String boardPrdcode = mRequest.getParameter("boardPrdcode");
			String boardPw = mRequest.getParameter("boardPw");
			String boardContent = mRequest.getParameter("boardContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>");
			
			String boardFilename = mRequest.getOriginalFileName("filename");//기존 파일명
			String boardFilepath = mRequest.getFilesystemName("filename");
			
			QnaVO qna = new QnaVO(0, 0, boardType, boardId, boardName, boardTitle, boardContent, boardFilename, boardFilepath, null, 0, boardSecret, boardPw, boardPrdcode);
			
			try {
				int result = new QnaService().insertQna(qna);
				if(result>0) {
					System.out.println("등록 성공");
				}else {
					System.out.println("뇨우");
				}
				response.sendRedirect("/qnaList");
			} catch (SQLException e) {
				System.out.println("SQL에러 ㅠ");
			}

			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}