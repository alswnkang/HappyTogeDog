package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String query = "insert into member values(?,?,?,?,?,?,?,null,?,?)";
		pstmt= conn.prepareStatement(query);
		System.out.println("dao!!!!");
		pstmt.setString(1, m.getId());
		pstmt.setString(2, m.getPw());
		pstmt.setString(3, id);
		pstmt.setString(4, m.getName());
		pstmt.setString(5, m.getPhone());
		pstmt.setString(6, m.getPost());
		pstmt.setString(7, m.getAddress());
		pstmt.setString(8, m.getEmail());
		pstmt.setInt(9, m.getMemberLevel());
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
	
	public int memberJoin2(Member m) throws SQLException {
		System.out.println("dao!!");
		Connection conn = JDBCTemplate.getCon();
		
		PreparedStatement pstmt = null;
		int result = 0;
		String id = m.getId();
		String query = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
		pstmt= conn.prepareStatement(query);
		System.out.println("dao!!!!");
		pstmt.setString(1, m.getId());
		pstmt.setString(2, m.getPw());
		pstmt.setString(3, m.getCode());
		pstmt.setString(4, m.getName());
		pstmt.setString(5, m.getPhone());
		pstmt.setString(6, m.getPost());
		pstmt.setString(7, m.getAddress());
		pstmt.setString(8, m.getPossibleTime());
		pstmt.setString(9, m.getEmail());
		pstmt.setInt(10, m.getMemberLevel());
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
	
	public Member login(String id, String pw) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where id =? and  pw=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			m = new Member();
			m.setId(rset.getString("id"));
			m.setPw(rset.getString("pw"));
			m.setCode(rset.getString("code"));
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			m.setPost(rset.getString("post"));
			m.setAddress(rset.getString("address"));
			m.setPossibleTime(rset.getString("possible_time"));
			m.setEmail(rset.getString("email"));
			m.setMemberLevel(rset.getInt("member_level"));
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return m;
	}
	
	public Member selectOne(String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where id=?";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			m = new Member();
			m.setId(rset.getString("id"));
			m.setPw(rset.getString("pw"));
			m.setCode(rset.getString("code"));
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			m.setPost(rset.getString("post"));
			m.setAddress(rset.getString("address"));
			m.setPossibleTime(rset.getString("possible_time"));
			m.setEmail(rset.getString("email"));
			m.setMemberLevel(rset.getInt("member_level"));
			System.out.println("다오");
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return m;
		
		
	}
	
	
	
}
