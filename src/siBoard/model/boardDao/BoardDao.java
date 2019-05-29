package siBoard.model.boardDao;

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

import siBoard.model.boardVo.Board;
import siTemplete.JDBCTemplete;

public class BoardDao {
	private Properties prop = new Properties();
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/siSql/board/boardQuery.properties").getPath();
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
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from board";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(stmt);
		}
		return result;
	}
	public ArrayList<Board> boardAll(Connection conn,int start,int end){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
	public int boardInsert(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into board values(board_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,null)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getBoardType());
			pstmt.setString(2, b.getBoardId());
			pstmt.setString(3, b.getBoardName());
			pstmt.setString(4, b.getBoardTitle());
			pstmt.setString(5, b.getBoardContent());
			pstmt.setString(6, b.getBoardFilename());
			pstmt.setString(7, b.getBoardFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Board boardView(Connection conn, int boardNo){
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return b;
	}
	public int boardUpdate(Connection conn, int boardNo, String boardTitle, String boardContent, String boardFilename, String boardFilepath) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update board set board_title=?, board_content=?, board_filename=?, board_filepath=? where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardFilename);
			pstmt.setString(4, boardFilepath);
			pstmt.setInt(5, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public Board boardUpdateOriginal(Connection conn, int boardNo,Board b) {
		b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return b;
	}
	public int boardDelete(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from board where board_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(pstmt);
		}
		return result;
	}
	public ArrayList<Board> boardSearchName(Connection conn,String searchKeyword){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_name = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
	public ArrayList<Board> boardSearchTitle(Connection conn,String searchKeyword){
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from board where board_Title like ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
}
