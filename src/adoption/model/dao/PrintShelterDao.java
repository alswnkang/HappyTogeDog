package adoption.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import adoption.model.vo.Shelter;
import adoption.model.vo.ShelterPageData;
import common.JDBCTemplate;

public class PrintShelterDao {

	public ShelterPageData selectList(int city, Connection conn, int start, int end) throws SQLException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pstmt = null;
		
		
		
		ArrayList<Shelter> list = null;
		Shelter shelter = null;
		ShelterPageData spd = null;
		String query ="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from shelter order by name) M) WHERE RNUM BETWEEN ? AND ?,citycode=?";
		ResultSet rset= null;
		pstmt= conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		pstmt.setInt(3, city);
		
		
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		spd = new ShelterPageData();
		while(rset.next()) {							
			shelter = new Shelter();
			shelter.setAddr(rset.getString("address"));		
			shelter.setName(rset.getString("name"));
			shelter.setPhone(rset.getString("phone"));
			list.add(shelter);

		}	
			
		
		
		spd.setList(list);
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
	
		
		return spd;
	
	}
	public int totalCount(Connection conn, int city) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from shelter where city_code=?";
		
		
		int result =0;
		pstmt= conn.prepareStatement(query);
		pstmt.setInt(1, city);
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			result =rset.getInt("cnt");
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return result;
	}

}
