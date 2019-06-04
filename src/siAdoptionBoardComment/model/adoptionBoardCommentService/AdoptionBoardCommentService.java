package siAdoptionBoardComment.model.adoptionBoardCommentService;

import java.sql.Connection;

import siAdoptionBoardComment.model.adoptionBoardCommentDao.AdoptionBoardCommentDao;
import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;
import siNoticeComment.model.noticeCommentDao.NoticeCommentDao;
import siTemplete.JDBCTemplete;

public class AdoptionBoardCommentService {
	public int commentInsert(AdoptionBoardComment ac) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new AdoptionBoardCommentDao().commentInsert(conn,ac);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentUpdate(String memberId,String adoptionBoardCommentContent,int adoptionBoardCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new AdoptionBoardCommentDao().commentUpdate(conn,memberId,adoptionBoardCommentContent,adoptionBoardCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public int commentDelete(int adoptionBoardCommentNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new AdoptionBoardCommentDao().commentDelete(conn,adoptionBoardCommentNo);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
}
