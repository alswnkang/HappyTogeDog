package siBoardComment.model.boardCommentService;

import java.sql.Connection;
import java.util.ArrayList;

import siBoardComment.model.boardCommentDao.BoardCommentDao;
import siBoardComment.model.boardCommentVo.BoardComment;
import siTemplete.JDBCTemplete;

public class BoardCommentService {
	public int commentInsert(BoardComment bc) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardCommentDao().commentInsert(conn,bc);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
}
