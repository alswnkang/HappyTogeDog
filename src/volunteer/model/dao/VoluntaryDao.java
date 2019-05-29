package volunteer.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import volunteer.model.vo.VoluntaryApplyBoard;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryDao {

	// 봉사활동 공고 등록
	public int insertVoluntaryRegister(Connection conn, VoluntaryRegister vr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into volunteer_register values(volunteer_register_seq.nextval,?,?,?,?,?,?,0,?,?,sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vr.getCode());
			pstmt.setString(2, vr.getTitle());
			pstmt.setString(3, vr.getVolunDate());
			pstmt.setString(4, vr.getVolunTime());
			pstmt.setInt(5, vr.getPerson());
			pstmt.setString(6, vr.getDetail());
			pstmt.setString(7, vr.getFilename());
			pstmt.setString(8, vr.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 봉사활동 리스트 총 게시물 수
	public int totalCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register";
		int result = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(stmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 리스트
	public ArrayList<VoluntaryRegister> voluntaryList(Connection conn, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) order by no desc) v) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 신청 게시판 총 갯수 :: 검색 :: 제목 
	public int totalCountTitle(Connection conn, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register where title like(?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 신청 게시판 총 개수 :: 검색 :: 보호소명 
	public int totalCountName(Connection conn, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from volunteer_register left join member using(code) where name like(?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return result;
	}

	// 봉사활동 신청 게시판 :: 검색 :: 제목 
	public ArrayList<VoluntaryRegister> searchKeywordTitle(Connection conn, String keyword, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) where title like(?) order by title) v) where rnum between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 신청 게시판 :: 검색 :: 보호소명 
	public ArrayList<VoluntaryRegister> searchKeywordName(Connection conn, String keyword, int start, int end) {
		ArrayList<VoluntaryRegister> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select rownum as rnum, v.* from (select * from volunteer_register left join member using(code) where name like(?) order by name) v) where rnum between ? and ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<VoluntaryRegister>();
			
			while(rset.next()) {
				VoluntaryRegister vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
				list.add(vr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return list;
	}

	// 봉사활동 뷰
	public VoluntaryRegister voluntaryView(Connection conn, int no) {
		VoluntaryRegister vr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from volunteer_register left join member using(code) where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				vr = new VoluntaryRegister();
				vr.setNo(rset.getInt("no"));
				vr.setCode(rset.getString("code"));
				vr.setName(rset.getString("name"));
				vr.setTitle(rset.getString("title"));
				vr.setVolunDate(rset.getString("volun_date"));
				vr.setVolunTime(rset.getString("volun_time"));
				vr.setPerson(rset.getInt("person"));
				vr.setDetail(rset.getString("detail"));
				vr.setFilename(rset.getString("filename"));
				vr.setFilepath(rset.getString("filepath"));
				vr.setEnrollDate(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return vr;
	}

	// 봉사활동 공고 수정
	public int updateVoluntary(Connection conn, VoluntaryRegister vr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "update volunteer_register set title=?, volun_date=?, volun_time=?, person=?, detail=?, filename=?, filepath=? where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vr.getTitle());
			pstmt.setString(2, vr.getVolunDate());
			pstmt.setString(3, vr.getVolunTime());
			pstmt.setInt(4, vr.getPerson());
			pstmt.setString(5, vr.getDetail());
			pstmt.setString(6, vr.getFilename());
			pstmt.setString(7, vr.getFilepath());
			pstmt.setInt(8, vr.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	// 봉사활동 공고 삭제
	public int deleteVoluntary(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "delete from volunteer_register where no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	// 봉사활동 신청
	

}
