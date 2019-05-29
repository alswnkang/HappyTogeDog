package adoption.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import adoption.model.vo.BookApply;
import common.JDBCTemplate;

public class BookApplyDao {
	private Properties prop = new Properties();
	public BookApplyDao() {
		String fileName = BookApplyDao.class.getResource("/adoption/sql/bookApply.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//방문예약 신청
	public int reservation(Connection conn, BookApply ba) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("bookApply");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, ba.getCode());
		pstmt.setString(2,  ba.getId());
		pstmt.setString(3, ba.getName());
		pstmt.setString(4,  ba.getPhone());
		pstmt.setDate(5, ba.getVisitDate());
		pstmt.setString(6, ba.getVisitTime());
		pstmt.setString(7, ba.getYard());
		pstmt.setString(8, ba.getAnimal());
		pstmt.setString(9, ba.getFamily());
		pstmt.setString(10, ba.getExperience());
		pstmt.setString(11, ba.getAvgTime());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}
	//방문예약 신청 내역 갯수 구하기
	public int reservationCount(Connection conn,String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("reservationCount");
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, id);
		rset=pstmt.executeQuery();
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}
	//방문예약 신청 내역 정보 가져오기
	public ArrayList<BookApply> selectList(Connection conn, int start, int end, String id) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String query = prop.getProperty("selectList");
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		pstmt.setString(3, id);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setNo(rset.getInt("rnum"));
			ba.setCode(rset.getString("careNm"));	//보호소 코드에 보호소 이름 넣기
			/*ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));*/
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			list.add(ba);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}
	
	
	
	
	
	
	
}
