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
	private Properties prop = new Properties();
	public BoardCommentDao() {
			String fileName = BoardCommentDao.class.getResource("/siSql/boardComment/boardCommentQuery.properties").getPath();
			System.out.println(fileName);
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public int commentInsert(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board_comment values(board_comment_seq.nextval,?,?,null,?,?,null,sysdate)";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardCommentType());
			pstmt.setString(2, bc.getBoardCommentId());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
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
