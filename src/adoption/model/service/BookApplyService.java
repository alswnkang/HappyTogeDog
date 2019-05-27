package adoption.model.service;

import java.sql.Connection;

import adoption.model.dao.BookApplyDao;
import adoption.model.vo.BookApply;
import common.JDBCTemplate;

public class BookApplyService {
	public int reservation(BookApply ba) {
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
