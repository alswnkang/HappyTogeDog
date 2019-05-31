package adoption.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import adoption.model.vo.BookApply;
import adoption.model.vo.DogList;
import common.JDBCTemplate;

public class BookApplyDao {
	private Properties prop = new Properties();
	public BookApplyDao() {
		String fileName = BookApplyDao.class.getResource("/adoption/sql/bookApply2.properties").getPath();
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
		pstmt.setString(1, id);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
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
	
	//유기견 리스트 가져오기
	public ArrayList<DogList> dogList(int reqPage){
		ArrayList<DogList> list = null;
		try {
			while (true) {
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20190501&endde=20190502&pageNo="
						+ reqPage
						+ "&upkind=417000&numOfRows=12&ServiceKey=aLiSUfKw3hrZNSZrqXuG6iJtNr0ufMlgmB8Y%2Fh93hFuOk5E%2Brl8bd8mxxl%2Fcga%2B6i2CP7lD5%2BGBnLYmmVm%2BkFw%3D%3D";
				//보경 서비스키 : TZzGtB8BZdZ0VsTPgpNVa1IQMCBLU9%2FlEriT0S4AFcqcswb4YiOAqJiR7So%2BJMbWd5fB0P6%2B8JQsI7EpN4KKrg%3D%3D
				//지영이 서비스키 : aLiSUfKw3hrZNSZrqXuG6iJtNr0ufMlgmB8Y%2Fh93hFuOk5E%2Brl8bd8mxxl%2Fcga%2B6i2CP7lD5%2BGBnLYmmVm%2BkFw%3D%3D
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc;
				NodeList nList2;
				while(true) {	//null이 나올경우 오류발생하기 때문에 not null일 때까지 반목문실행
					doc = dBuilder.parse(url);
					
					// root tag
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // XML의 최상위 tag값 가져오기
					
					//총 유기견 마리수 구하기(totalCount)
					nList2 = doc.getElementsByTagName("body");
					System.out.println(nList2.item(0));
					System.out.println(nList2);
					if(nList2.item(0) !=null) {
						break;
					}
				}
				/*Document doc = dBuilder.parse(url);
				System.out.println("doc : " + doc);
				System.out.println("toString : "+doc.toString());
				
				// root tag
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // XML의 최상위 tag값 가져오기
				
				//총 유기견 마리수 구하기(totalCount)
				NodeList nList2 = doc.getElementsByTagName("body");
				System.out.println(nList2);*/
				
				Node nNode2 = nList2.item(0);
//				System.out.println(nList2.item(0));
				Element eElement2 = (Element) nNode2;
//				System.out.println(eElement2);
				System.out.println(getTagValue("totalCount",eElement2));
				String totalCount = getTagValue("totalCount",eElement2);
				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
//				Node node1 = doc.getElementsByTagName("totalCount").item(0);
				// System.out.println("파싱할 리스트 수 : "+ nList.getLength());
				
				int count = 0;
				list = new ArrayList<DogList>();
				for (int temp = 0; temp < nList.getLength(); temp++) {
					DogList dl = new DogList();
					Node nNode = nList.item(temp);					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						System.out.println("######################");
						// System.out.println(eElement.getTextContent()); getTextContent(): 전체 정보
						dl.setAge(getTagValue("age", eElement));
						dl.setCareAddr(getTagValue("careAddr", eElement));
						dl.setCareNm(getTagValue("careNm", eElement));
						dl.setCareTel(getTagValue("careTel", eElement));
						dl.setColorCd(getTagValue("colorCd", eElement));
						dl.setFilename(getTagValue("popfile", eElement));
						dl.setHappenDt(getTagValue("happenDt", eElement));
						dl.setHappenPlace(getTagValue("happenPlace", eElement));
						dl.setKindCd(getTagValue("kindCd", eElement));
						dl.setNoticeEdt(getTagValue("noticeEdt", eElement));
						dl.setNoticeNo(getTagValue("noticeNo", eElement));
						dl.setOrgNm(totalCount);		//관할기관대신 총 갯수 전달
						dl.setSexCd(getTagValue("sexCd", eElement));
						dl.setSpecialMark(getTagValue("specialMark", eElement));
						dl.setWeight(getTagValue("weight", eElement));
						dl.setNoticeSdt(getTagValue("noticeSdt", eElement));
						dl.setProcessState(getTagValue("processState", eElement));
						dl.setNeuterYn(getTagValue("neuterYn", eElement));
						count++;
						System.out.println("데이터 담은수:" + count);
						list.add(dl);
					} // for end
				} // if end
				System.out.println("page number : " + reqPage);
				
				break;
			} // while end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private static String getTagValue(String tag, Element eElement) {
		//System.out.println(eElement.getElementsByTagName(tag));		
//		System.out.println(eElement.getElementsByTagName(tag).item(0).toString());
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}
	
	//보호소 방문가능시간 가져오기
	public String careTime(Connection conn, String careNm) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String careTime="";
		String query = prop.getProperty("careTime");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, careNm);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				careTime = rset.getString("possible_time");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return careTime;
	}

	public int reservationCareCount(Connection conn, String code,String startDay, String endDay) throws SQLException {
		System.out.println("갯수 구하기Dao");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select count(*) as cnt from book_apply where code=?"+sql;
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, code);
		rset=pstmt.executeQuery();
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}
	
	//보호소에서 방문예약 신청내역 리스트 보기
	public ArrayList<BookApply> reservationCareList(Connection conn, int start, int end, String code, String startDay, String endDay) throws SQLException {
		System.out.println("리스트구하기 Dao");
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) where rnum BETWEEN ? and ? and code=?"+sql;
//		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) where rnum BETWEEN ? and ? and code=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		pstmt.setString(3, code);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setNo(rset.getInt("rnum"));
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
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

	public BookApply viewOne(Connection conn, int no, String startDay, String endDay, String code) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(visit_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		System.out.println(no);
		System.out.println(code);
		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) where code=? and rnum=?"+sql;
//		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) where rnum BETWEEN ? and ? and code=?";
		pstmt = conn.prepareStatement(query);
		System.out.println("dao"+query);
		pstmt.setString(1, code);
		pstmt.setInt(2, no);
		rset = pstmt.executeQuery();
		BookApply ba = null;
		if(rset.next()) {
			ba = new BookApply();
			ba.setNo(rset.getInt("rnum"));
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			ba.setYard(rset.getString("yard"));
			ba.setAnimal(rset.getString("animal"));
			ba.setFamily(rset.getString("family"));
			ba.setExperience(rset.getString("experience"));
			ba.setAvgTime(rset.getString("avg_time"));
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return ba;
	}

	/*public int selectDateCount(Connection conn, String startDay, String endDay, String code) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from book_apply where visit_date between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd') and code=?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, startDay);
		pstmt.setString(2, endDay);
		pstmt.setString(3, code);
		rset=pstmt.executeQuery();
		if(rset.next()) {
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}

	public ArrayList<BookApply> selectDateList(Connection conn, int start, int end, String startDay, String endDay,String code) throws SQLException {
		System.out.println("리스트구하기 Dao");
		PreparedStatement pstmt =null;
		ResultSet rset = null;
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		String sql="";
		if(startDay!=null && startDay!=""){
			sql += " and visit_date>'"+startDay+"'";
		}
		if(endDay!=null && endDay!=""){
			sql += " and TO_CHAR(spon_date,'yyyy-mm-dd')<='"+endDay+"'";
		}
		String query = "select * from (select ROWNUM as rNum,b.* from (select * from book_apply order by 1 desc) b) wherernum BETWEEN ? and ? and code=?"+sql;
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		pstmt.setString(3, code);
		rset = pstmt.executeQuery();
		while(rset.next()) {
			BookApply ba = new BookApply();
			ba.setNo(rset.getInt("rnum"));
			ba.setId(rset.getString("id"));
			ba.setName(rset.getString("name"));
			ba.setPhone(rset.getString("phone"));
			ba.setVisitDate(rset.getDate("visit_date"));
			
			ba.setVisitTime(rset.getString("visit_time"));
			ba.setApplyDate(rset.getDate("apply_date"));
			ba.setStatus(rset.getInt("status"));
			list.add(ba);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return list;
	}*/
	
	
	
	
	
}
