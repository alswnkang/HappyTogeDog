package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.JDBCTemplate;
import member.model.vo.CareCode;
import member.model.vo.Member;
import member.model.vo.cityCode;

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
		}
		JDBCTemplate.close(conn);
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return m;
	}

	public int memberModify(Member m) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set pw=?,name=?,phone=?,post=?,address=?,email=? where id=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, m.getPw());
		pstmt.setString(2, m.getName());
		pstmt.setString(3, m.getPhone());
		pstmt.setString(4, m.getPost());
		pstmt.setString(5, m.getAddress());
		pstmt.setString(6, m.getEmail());
		pstmt.setString(7, m.getId());
		result = pstmt.executeUpdate();
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(pstmt);
		return result;
	}
	public int memberModify2(Member m) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set pw=?,name=?,phone=?,post=?,address=?,email=?,possible_time=? where id=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, m.getPw());
		pstmt.setString(2, m.getName());
		pstmt.setString(3, m.getPhone());
		pstmt.setString(4, m.getPost());
		pstmt.setString(5, m.getAddress());
		pstmt.setString(6, m.getEmail());
		pstmt.setString(7, m.getPossibleTime());
		pstmt.setString(8, m.getId());
		result = pstmt.executeUpdate();
		System.out.println(m.getPossibleTime());
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(pstmt);
		return result;
	}
	public int delete(String id) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where id=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		result = pstmt.executeUpdate();
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<Member> selectList(int start,int end) throws SQLException{
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where not member_level='2' order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
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
			list.add(m);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return list;
	}
	public int totalCount() throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from member where not member_level='2'";
		int result = 0;
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		if(rset.next()) {
		result = rset.getInt("cnt");
		}
		JDBCTemplate.close(stmt);
		JDBCTemplate.close(rset);
		return result;
	}
	
	public ArrayList<Member> searchUser(int start,int end,int select,String search) throws SQLException{
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query= "";
		ArrayList<Member> list = new ArrayList<Member>();
		if(select == 1) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where id like ? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}else if(select == 2) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where name like ? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}else if(select == 3) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where code=? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "%"+search+"%");
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
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
			list.add(m);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return list;
	}
	public int searchCount(int select,String search) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "";
		if(select == 1) {
			query = "select count(*) cnt from member where id like ?";
		}else if(select == 2) {
			query = "select count(*) cnt from member where name like ?";
		}else if(select == 3) {
			query = "select count(*) cnt from member where code=?";
		}
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "%"+search+"%");
		rset = pstmt.executeQuery();
		if(rset.next()) {
		result = rset.getInt("cnt");
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rset);
		return result;
	}
	public ArrayList<Member> seeUser(int start,int end,int user) throws SQLException{
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query= "";
		ArrayList<Member> list = new ArrayList<Member>();
		if(user == 0) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where member_level=? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}else if(user == 1) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where member_level=? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}else if(user == 2) {
			query = "SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where not member_level=? order by member_level) M) WHERE RNUM BETWEEN ? AND ?";
		}
		
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, user);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		rset = pstmt.executeQuery();
		while(rset.next()) {
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
			list.add(m);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return list;
	}
	public int seeUserCount(int user) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "";
		if(user == 0) {
			query = "select count(*) cnt from member where member_level = ?";
		}else if(user == 1) {
			query = "select count(*) cnt from member where member_level = ?";
		}else if(user == 2) {
			query = "select count(*) cnt from member where not member_level = ?";
		}
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, user);
		rset = pstmt.executeQuery();
		if(rset.next()) {
		result = rset.getInt("cnt");
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rset);
		return result;
	}
	public int emailOverlap(String email) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "select * from member where email = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, email);
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return result;
		
	}
	public Member findUser(String email) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where email = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, email);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			m = new Member();
			m.setId(rset.getString("id"));
			m.setPw(rset.getString("pw"));
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rset);
		JDBCTemplate.close(conn);
		return m;
	}
	public int temporaryPassword(String pw,String email) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set pw=? where email=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, pw);
		pstmt.setString(2, email);
		result = pstmt.executeUpdate();
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(conn);
		return result;
	}
	
}
