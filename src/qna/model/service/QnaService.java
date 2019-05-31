package qna.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.dao.QnaDao;
import qna.model.vo.QnaListVO;
import qna.model.vo.QnaVO;
import sponsorship.model.vo.SearchVO;

public class QnaService {

	public QnaListVO selectQna(SearchVO search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		
		int reqPage = search.getReqPage();
		int total = 0;

		total = new QnaDao().totalCount(conn,search);
		
		int pageNum = 5;//한 페이지에 출력할 게시물 개수
		int totalPage = (total%pageNum==0)?(total/pageNum):(total/pageNum)+1;
		
		
		int start = (reqPage*pageNum-pageNum)+1;//시작 번호
		int end  = reqPage*pageNum;
		ArrayList<QnaVO> qnainfoList = null;

		qnainfoList = new QnaDao().selectQna(conn,start,end,search);
		
		int totalNavi = 5;//노출시킬 네비 버튼 개수
		String pageNavi = "";
		
		int pageNo = ((reqPage-1)/totalNavi)*totalNavi+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='javascript:list("+(pageNo-1)+");'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a> ";
			
		}
		int i = 1;
		while(!(i++>totalNavi || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur'>"+pageNo+"</a> ";
			}else {
				pageNavi += "<a href='javascript:list("+pageNo+");'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='javascript:list("+pageNo+");'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		QnaListVO qnaList = new QnaListVO(qnainfoList, pageNavi);
		JDBCTemplate.close(conn);
		return qnaList;
	}

	public QnaVO selectQna(int boardNo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		QnaVO qna = new QnaDao().selectQna(conn, boardNo);
		JDBCTemplate.close(conn);
		return qna;
	}

}
