package finddog.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import adoption.model.vo.DogList;
import common.JDBCTemplate;
import finddog.model.vo.Kind;
import siBoard.model.boardVo.Board;
import siTemplete.JDBCTemplete;

public class SearchDogDao {

	public ArrayList<Kind> getKindCode(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement pstmt = null;
		

		ArrayList<Kind> list = null;
		Kind k = null;
		
		String query ="SELECT * FROM dogkind";
		ResultSet rset= null;
		pstmt= conn.prepareStatement(query);

		
		
		
		rset= pstmt.executeQuery();
		
		list= new ArrayList<>();
		
		while(rset.next()) {							
			k = new Kind();
			k.setKind(rset.getString("kind"));
			k.setCode(rset.getString("code"));
			
			list.add(k);
		}	
			
		
		
		
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		
		return list;
	}

	public ArrayList<DogList> getList(int page, String sDay, String eDay, String kind, String cityCode) {
		// TODO Auto-generated method stub
		ArrayList<DogList> list = null;
		try {
			String kinds="";
			String cityCo="";
			while (true) {
				
				if(sDay.equals("")||sDay==null) {
					sDay=preMonth();
				}
				if(eDay.equals("")||eDay==null) {
					eDay=date();
				}
				if(kind.equals("")||kind==null) {
					kinds="&kind=".concat(kind);
				}
				if(cityCode.equals("")||cityCode==null) {
					cityCo="&upr_cd=".concat(cityCode);
				}
				// parsing할 url 지정(API 키 포함해서)
				System.out.println(sDay+","+eDay+"진짜이상하네");
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde="+sDay+"&endde="+eDay+"&pageNo="
						+ page
						+kinds+cityCo+"&upkind=417000&numOfRows=8&ServiceKey=9foRMY8t3j0MRIsmBCTWOiLUVaW4yJivGOtPfYE9x8yYsPcPCkCUZgGm39bZZGdQQc1ZT9MN87KHULUH8aLpMg%3D%3D";
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				// root tag
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName()); // XML의 최상위 tag값 가져오기

				// 파싱할 tag
				NodeList nList = doc.getElementsByTagName("item");
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
						dl.setOrgNm(getTagValue("orgNm", eElement));
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
				System.out.println("page number : " + page);
				break;
			} // while end
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		

	}
	
	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public int totalCount(Connection conn, int type) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "select count(*) as cnt from board where board_type=?";
		System.out.println("여기는 토탈카운트 도착");
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, type);
			rset = stmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(stmt);
		}
		return result;
	}



	public ArrayList<Board> getListDB(int page, String sDay, String eDay, String kind, String cityCode, Connection conn, int start, int end) {
		// TODO Auto-generated method stub
		//보완 필요
		String city="";
		if(sDay.equals("")||sDay==null) {
			sDay=preMonth();
		}
		if(eDay.equals("")||eDay==null) {
			eDay=date();
		}
		if(cityCode.equals("")||cityCode==null) {
			city="";
		}else {
			city="and happen_city=".concat(cityCode);
		}
		
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println("여기는 겟리스트 도착");
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ? and board_type=3 and dog_kind=? "+city+" and happen_date>?  and happen_date<=?";
		System.out.println(query);
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.setString(3, kind);
			pstmt.setString(4, sDay);
			pstmt.setString(5, eDay);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				System.out.println("리스트담았어요");
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}

	public int change(Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="select*from shelter";
		int result=0;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery(query);
			while(rset.next()) {
				String code=rset.getString("code");
				String name=rset.getString("name");
				String phone=rset.getString("phone");
				String addr=rset.getString("addr");
				String[] array = addr.split(" ");
				int city=0;
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
				
				System.out.println("insert into shelter values('"+code+"','"+name+"','"+phone+"','"+addr+"','"+city+"');");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
	
		
		
		return 0;
	}

	public ArrayList<Board> takeBoard(Connection conn, int start, int end, int type) {
		// TODO Auto-generated method stub
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, n.* FROM (SELECT * FROM BOARD ORDER BY BOARD_NO desc) n) WHERE RNUM BETWEEN ? AND ? and board_type=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			pstmt.setInt(3, type);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rset.next()) {
				Board b = new Board();
				b.setBoardRnum(rset.getInt("rnum"));
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardType(rset.getInt("board_Type"));
				b.setBoardId(rset.getString("board_id"));
				b.setBoardName(rset.getString("board_Name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardFilename(rset.getString("board_filename"));
				b.setBoardFilepath(rset.getString("board_filepath"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setBoardCount(rset.getInt("board_count"));
				b.setBoardSecret(rset.getInt("board_secret"));
				b.setBoardPw(rset.getString("board_pw"));
				b.setBoardPrdCode(rset.getString("board_prdCode"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplete.close(rset);
			JDBCTemplete.close(pstmt);
		}
		return list;
	}
	public String date() {
//		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		System.out.println("date메소드 : "+date.format(cal.getTime()));
		return date.format(cal.getTime());
	}
	public String preMonth() {
//		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		System.out.println("preMonth메소드 : "+date.format(cal.getTime()));
		return date.format(cal.getTime());
	}

}


