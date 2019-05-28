package volunteer.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.JDBCTemplate;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryRegisterDao {

	public int insertVoluntaryRegister(Connection conn, VoluntaryRegister vr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into volunteer_register values(volunteer_register_seq.nextval,?,?,?,?,?,?,1,0,?,?,sysdate)";
		
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

}
