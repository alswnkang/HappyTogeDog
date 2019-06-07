package adoption.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.vo.Shelter;
import adoption.model.vo.ShelterPageData;
import common.JDBCTemplate;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

public class PrintShelterDao {

	public MemberPageData selectList(int city, Connection conn, int start, int end) throws SQLException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		
		Member m = null;
		MemberPageData spd = null;
		ArrayList<Member> list = null;
		
		String query="";
		
		switch (city) {
		case 2:
			
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '서울%') M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 14:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '부산%') M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 15:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '대구%') M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 6:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '대전%') M) WHERE RNUM BETWEEN ? AND ?";
		
			break;
		case 10:
			
				query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '광주%') M) WHERE RNUM BETWEEN ? AND ?";
			
			break;
		case 50:
			
			break;
		case 3:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경기%') M) WHERE RNUM BETWEEN ? AND ?";
				
		
			break;
	
		case 12:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경상남%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 8:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '경상북%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 5:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '충청남%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 7:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '충청북%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 11:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '전라남%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 9:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '전라북%') M) WHERE RNUM BETWEEN ? AND ?";
			break;
		case 4:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '강원%') M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 16:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '세종%') M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 1:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '인천%') M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		case 13:
			query="SELECT * FROM (SELECT ROWNUM AS RNUM, M.*FROM (select * from member where address like '제주%') M) WHERE RNUM BETWEEN ? AND ?";
			break;	
		
		}
		
		
		pstmt= conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		
		System.out.println(start+""+end+""+city);
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		spd = new MemberPageData();
		System.out.println(query);
		
		while(rset.next()) {							
			m = new Member();
			
			System.out.println("여기들어왔나요");
			String address=rset.getString("address");
			
			
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			m.setAddress(address);
			
			list.add(m);
			
		}	
			
		
		
		spd.setList(list);
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
	
		
		return spd;
	
	}
	public int totalCount(Connection conn, int city) throws SQLException {
		PreparedStatement pstmt = null;	
		ResultSet rset= null;
		

		String query="select count(*) cnt from member where address like '서울%'";
		
		switch (city) {
		case 2:
			
			query="select count(*) cnt from member where address like '서울%'";
			
			break;
		case 14:
			
			query="select count(*) cnt from member where address like '부산%'";
			
			break;
		case 15:
			
			query="select count(*) cnt from member where address like '대구%'";
			
			break;
		case 6:
			
			query="select count(*) cnt from member where address like '대전%'";
		
			break;
		case 10:
			
			query="select count(*) cnt from member where address like '광주%'";
			
			break;
		case 50:
			
			break;
		case 3:
			query="select count(*) cnt from member where address like '경기%'";
			break;
	
		case 12:
			query="select count(*) cnt from member where address like '경상남%'";
			break;
		case 8:
			query="select count(*) cnt from member where address like '경상북%'";
			break;
		case 5:
			query="select count(*) cnt from member where address like '충청남%'";
			break;
		case 7:
			query="select count(*) cnt from member where address like '충청북%'";
			break;
		case 11:
			query="select count(*) cnt from member where address like '전라남%'";
			break;
		case 9:
			query="select count(*) cnt from member where address like '전라북%'";;
			break;
		case 4:
			query="select count(*) cnt from member where address like '강원%'";
			break;	
		case 16:
			query="select count(*) cnt from member where address like '세종%'";
			break;	
		case 1:
			query="select count(*) cnt from member where address like '인천%'";
			break;	
		case 13:
			query="select count(*) cnt from member where address like '제주%'";
			break;	
		
		}
		
		pstmt= conn.prepareStatement(query);
		
		rset= pstmt.executeQuery();
		
		
		int result=0;
		System.out.println(query);
		
		if(rset.next()) {							
				
			System.out.println("들어옴여긴??");
			result=rset.getInt("cnt");		
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
		JDBCTemplate.close(pstmt);
		
		
		return result;
		
		
	}
	public ArrayList<Shelter> getAllList(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Shelter>list =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from shelter";
		
		
		
		pstmt= conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		list = new ArrayList<>();
		
		while(rset.next()) {
			Shelter s = new Shelter();
			s.setAddr(rset.getString("addr"));
			s.setCode(rset.getString("code"));
			s.setName(rset.getString("name"));
			s.setPhone(rset.getString("phone"));
			s.setLevel(rset.getInt("lev"));
			list.add(s);
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return list;
	}
	public ArrayList<Member> getSearchName(Connection conn, String key) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Member>list =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member";
		
		
		
		pstmt= conn.prepareStatement(query);
		rset = pstmt.executeQuery();
		list = new ArrayList<>();
		
		while(rset.next()) {
			Member s = new Member();
			s.setAddress(rset.getString("address"));
			s.setName(rset.getString("name"));
			s.setPhone(rset.getString("phone"));
			
			if(rset.getString("name").contains(key)) {
				list.add(s);
			}		
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return list;
	}
}
