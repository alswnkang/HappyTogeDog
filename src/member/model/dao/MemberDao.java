package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public ArrayList<CareCode> getCode() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<CareCode> list = new ArrayList<>();
	  
			try{
				while(true){
					// parsing할 url 지정(API 키 포함해서)
					String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter?upr_cd=6110000&org_cd=3220000&ServiceKey=TZzGtB8BZdZ0VsTPgpNVa1IQMCBLU9%2FlEriT0S4AFcqcswb4YiOAqJiR7So%2BJMbWd5fB0P6%2B8JQsI7EpN4KKrg%3D%3D&";
					
					DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
					Document doc = dBuilder.parse(url);
					
					// root tag 
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());		//XML의 최상위 tag값 가져오기
					
					// 파싱할 tag
					NodeList nList = doc.getElementsByTagName("item");
					//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
					
					for(int temp = 0; temp < nList.getLength(); temp++){
						CareCode cc = new CareCode();
						Node nNode = nList.item(temp);
						if(nNode.getNodeType() == Node.ELEMENT_NODE){
							
							Element eElement = (Element) nNode;
							System.out.println("######################");
							
							cc.setCareNm(getTagValue("careNm", eElement));
							cc.setCareRegNo(getTagValue("careRegNo", eElement));
						
							
//					
						
							list.add(cc);
						}	// for end
					}	// if end
					
					break;
					
					
				}	// while end
				
			} catch (Exception e){	
				e.printStackTrace();
			}
	
		return list;
		
		
	}

	public ArrayList<cityCode> getCityCode() {
		// TODO Auto-generated method stub
		ArrayList<cityCode> list = new ArrayList<>();
	    
		int page=1;
			// 페이지 초기값 
			try{
				while(true){
					System.out.println(page);
					// parsing할 url 지정(API 키 포함해서)
					String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?ServiceKey=BbQpcBG0GiwHYBNrM7gxpyWUTHH9PAwSZgtjL%2Bj%2FIb6YYiHG86O4qZta75KyCzkPPBmRjXzOHffarvGnn67JzA%3D%3D&pageNo="+page;
					
					DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
					Document doc = dBuilder.parse(url);
					
					// root tag 
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());		//XML의 최상위 tag값 가져오기
					
					// 파싱할 tag
					NodeList nList = doc.getElementsByTagName("item");
					//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
					
					for(int temp = 0; temp < nList.getLength(); temp++){
						cityCode cc = new cityCode();
						Node nNode = nList.item(temp);
						if(nNode.getNodeType() == Node.ELEMENT_NODE){
							
							Element eElement = (Element) nNode;
							
							cc.setCityCode(getTagValue("orgCd", eElement));
							cc.setCityName(getTagValue("orgdownNm", eElement));

						
							list.add(cc);
						}	// if end
					}	// for end
					page++;
					
					for(int i=0;i<200000;i++) {
						
					}
				
					if(page>2) {
						System.out.println("종료");
						page=1;
						break;
					}
					
				
				}	// while end
				
			} catch (Exception e){	
				e.printStackTrace();
			}
			System.out.println("DAO : "+list.size()+""+page);
		
		
			return list;
		
		
		
		
		
		
	}
	
	
	
	public ArrayList<cityCode> getAreaCode(String code) {
		// TODO Auto-generated method stub
		ArrayList<cityCode> list = new ArrayList<>();
	    
	
		// 페이지 초기값 
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sigungu?upr_cd="+code+"&ServiceKey=BbQpcBG0GiwHYBNrM7gxpyWUTHH9PAwSZgtjL%2Bj%2FIb6YYiHG86O4qZta75KyCzkPPBmRjXzOHffarvGnn67JzA%3D%3D";
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());		//XML의 최상위 tag값 가져오기
				
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
				//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				for(int temp = 0; temp < nList.getLength(); temp++){
					cityCode cc = new cityCode();
					System.out.println("제발 좀 되자");
					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						
						Element eElement = (Element) nNode;
						
						cc.setDistrict(getTagValue("orgCd", eElement));
						cc.setDistrictName(getTagValue("orgdownNm", eElement));
					
						
					
						list.add(cc);
					}	// for end
				}	// if end
				
			break;
			}	// while end
			
		} catch (Exception e){	
			e.printStackTrace();
		}

	return list;
	}
	

	
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	
	
}
