package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import adoption.model.dao.BookApplyDao;
import adoption.model.vo.BookApply;
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
	
	
	
}
