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
import member.model.vo.Member;

public class PrintShelterDao {

	public ShelterPageData selectList(int city, Connection conn, int start, int end) throws SQLException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pstmt = null;
		
		
		
		ArrayList<Shelter> list = null;
		Shelter shelter = null;
		ShelterPageData spd = null;
		String query ="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from shelter order by name) M) WHERE RNUM BETWEEN ? AND ? and citycode=?";
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
	public int addShelter(Connection conn, Member m) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "insert into shelter values(?,?,?,?,?)";
		
		pstmt=conn.prepareStatement(query);
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, m.getCode());
		pstmt.setString(2, m.getName());
		pstmt.setString(3, m.getPhone());
		pstmt.setString(4, m.getAddress());
		
		int city=0;
		int result=0;
		String[] array = m.getAddress().split(" ");
		//city
	
		switch (array[0]) {
		case "서울특별시":
			city=2;
			break;
		case "부산광역시":
			city=14;
			break;
		case "대구광역시":
			city=15;
			break;
		case "대전광역시":
			city=6;
			break;
		case "광주광역시":
			city=10;
			break;
		case "울산광역시":
			break;
		case "경기도":
			city=3;
			break;
		case "경상남도":
			city=12;
			break;
		case "경상북도":
			city=8;
			break;
		case "충청남도":
			city=5;
			break;
		case "충청북도":
			city=7;
			break;
		case "전라남도":
			city=11;
			break;
		case "전라북도":
			city=9;
			break;
		case "강원도":
			city=4;
			break;	
		case "세종특별시":
			city=16;
			break;	
		case "인천광역시":
			city=1;
			break;	
		case "제주특별시":
			city=13;
			break;	
		
		}
		
		
		pstmt.setInt(5, city);
		
		
		result=pstmt.executeUpdate();
		
		return result;
		
		
	}
}
