package siNoticeComment.model.noticeCommentService;

import java.sql.Connection;
import siBoardComment.model.boardCommentDao.BoardCommentDao;
import siNoticeComment.model.noticeCommentDao.NoticeCommentDao;
import siNoticeComment.model.noticeCommentVo.NoticeComment;
import siTemplete.JDBCTemplete;

public class NoticeCommentService {
	public int commentInsert(NoticeComment nc) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new NoticeCommentDao().commentInsert(conn,nc);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentUpdate(String memberId,String noticeCommentContent,int noticeCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new NoticeCommentDao().commentUpdate(conn,memberId,noticeCommentContent,noticeCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentDelete(int noticeCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new NoticeCommentDao().commentDelete(conn,noticeCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
}
