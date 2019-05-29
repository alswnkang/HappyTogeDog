package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.BookApplyDao;
import adoption.model.vo.BookApply;
import adoption.model.vo.BookApplyPageData;
import common.JDBCTemplate;

public class BookApplyService {
	//방문예약 신청
	public int reservation(BookApply ba) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().reservation(conn, ba);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	//마이페이지에 방문예약 신청내역 보여주기
	public BookApplyPageData selectList(int reqPage,String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 3;
		int totalCount = new BookApplyDao().reservationCount(conn,id);
		System.out.println(totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().selectList(conn, start, end, id);
		
		String pageNavi ="";
		int pageNaviSize = 3;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			/*pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage=1'><<</a>";*/
			pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/reservationMypage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
			/*pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+totalPage+"'>>></a>";*/
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}
	
	
}
