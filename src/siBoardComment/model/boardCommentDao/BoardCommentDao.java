package siBoardComment.model.boardCommentDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import siBoardComment.model.boardCommentVo.BoardComment;
import siTemplete.JDBCTemplete;

public class BoardCommentDao {
	public int commentInsert(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,?,?,?,null,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardCommentType());
			pstmt.setString(2, bc.getBoardCommentId());
			pstmt.setString(3, bc.getBoardCommentName());
			pstmt.setString(4, bc.getBoardCommentContent());
			pstmt.setInt(5, bc.getBoardRef());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public int commentUpdate(Connection conn,String memberId,String boardCommentContent,int boardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board_comment set board_comment_content = ? where board_comment_id = ? and board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, boardCommentContent);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, boardCommentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}	
	public int commentDelete(Connection conn,int boardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete board_comment where board_comment_no = ?";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardCommentNo);
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
