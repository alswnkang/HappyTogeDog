package adoption.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import adoption.model.vo.BookApply;
import common.JDBCTemplate;
import adoption.model.dao.BookApplyDao;

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
}
