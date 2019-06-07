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
		
		String query ="select*from member;";
		
		
		pstmt= conn.prepareStatement(query);
		
		
		
		System.out.println(start+""+end+""+city);
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		spd = new MemberPageData();
		System.out.println(query);
		
		while(rset.next()) {							
			m = new Member();
			
			System.out.println("여기들어왔나요");
			m.setAddress(rset.getString("address"));
			m.setName(rset.getString("name"));
			m.setPhone(rset.getString("phone"));
			String[] array = m.getAddress().split(" ");
			
			switch (array[0]) {
			case "서울특별시":
				if(city==2) {
					list.add(m);
				};
				break;
			case "부산광역시":
				if(city==14) {
					list.add(m);
				};
				break;
			case "대구광역시":
				if(city==15) {
					list.add(m);
				};
				break;
			case "대전광역시":
				if(city==6) {
					list.add(m);
				};
				break;
			case "광주광역시":
				if(city==10) {
					list.add(m);
				};
				break;
			case "울산광역시":
				break;
			case "경기도":
				if(city==3) {
					list.add(m);
				};
				break;
			case "경상남도":
				if(city==12) {
					list.add(m);
				};
				break;
			case "경상북도":
				if(city==8) {
					list.add(m);
				};
				break;
			case "충청남도":
				if(city==5) {
					list.add(m);
				};
				break;
			case "충청북도":
				if(city==7) {
					list.add(m);
				};
				break;
			case "전라남도":
				if(city==11) {
					list.add(m);
				};
				break;
			case "전라북도":
				if(city==9) {
					list.add(m);
				};
				break;
			case "강원도":
				if(city==4) {
					list.add(m);
				};
				break;	
			case "세종특별시":
				if(city==16) {
					list.add(m);
				};
				break;	
			case "인천광역시":
				if(city==1) {
					list.add(m);
				};
				break;	
			case "제주특별시":
				if(city==13) {
					list.add(m);
				};
				break;	
			
			}
			
			
			
		
		}	
			
		
		
		spd.setList(list);
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
	
		
		return spd;
	
	}
	public int totalCount(Connection conn, int city) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from shelter where lev=?";
		
		
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
	public ArrayList<Shelter> getSearchName(Connection conn, String key) throws SQLException {
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
			
			if(rset.getString("name").contains(key)) {
				list.add(s);
			}		
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return list;
	}
}
