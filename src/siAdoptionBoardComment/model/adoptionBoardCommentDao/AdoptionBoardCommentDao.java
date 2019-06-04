package siAdoptionBoardComment.model.adoptionBoardCommentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import siAdoptionBoardComment.model.adoptionBoardCommentVo.AdoptionBoardComment;
import siTemplete.JDBCTemplete;

public class AdoptionBoardCommentDao {
	public int commentInsert(Connection conn, AdoptionBoardComment ac) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,null,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, ac.getAdoptionBoardCommentType());
			pstmt.setString(2, ac.getAdoptionBoardCommentId());
			pstmt.setString(3, ac.getAdoptionBoardCommentName());
			pstmt.setString(4, ac.getAdoptionBoardCommentContent());
			pstmt.setInt(5, ac.getAdoptionBoardRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentUpdate(Connection conn,String memberId,String adoptionBoardCommentContent,int adoptionBoardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_id = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, adoptionBoardCommentContent);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, adoptionBoardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}	
	public int commentDelete(Connection conn,int adoptionBoardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, adoptionBoardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
}
