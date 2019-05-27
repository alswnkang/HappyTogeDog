package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.vo.Member;

public class MemberDao {
	
	public int memberJoin(Member m) throws SQLException {
		System.out.println("dao!!");
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String id = m.getId();
		System.out.println(m.getId());
		System.out.println(m.getPw());
		System.out.println(m.getName());
		System.out.println(m.getPhone());
		System.out.println(m.getPost());
		System.out.println(m.getAddress());
		System.out.println(m.getEmail());
		String query = "insert into member values(?,?,?,?,?,?,?,null,?,0)";
		pstmt= conn.prepareStatement(query);
		pstmt.setString(1, m.getId());
		pstmt.setString(2, m.getPw());
		pstmt.setString(3, id);
		pstmt.setString(4, m.getName());
		pstmt.setString(5, m.getPhone());
		pstmt.setString(6, m.getPost());
		pstmt.setString(7, m.getAddress());
		pstmt.setString(8, m.getEmail());
		result = pstmt.executeUpdate();
		System.out.println("DAO");
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return result;
	}
}
