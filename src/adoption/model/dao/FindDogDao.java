package adoption.model.dao;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import adoption.model.vo.DogList;

public class FindDogDao {

	public ArrayList<DogList> getList(int page) {
		ArrayList<DogList> list = new ArrayList<>();
		// TODO Auto-generated method stub
		  
			
		  
		  	try{
				while(true){
					// parsing할 url 지정(API 키 포함해서)
					String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic?bgnde=20190101&endde=20190524&pageNo="+page+"&upkind=417000&numOfRows=12&ServiceKey=TZzGtB8BZdZ0VsTPgpNVa1IQMCBLU9%2FlEriT0S4AFcqcswb4YiOAqJiR7So%2BJMbWd5fB0P6%2B8JQsI7EpN4KKrg%3D%3D";
					
					DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
					Document doc = dBuilder.parse(url);
					
					// root tag 
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());		//XML의 최상위 tag값 가져오기
					
					// 파싱할 tag
					NodeList nList = doc.getElementsByTagName("item");
					//System.out.println("파싱할 리스트 수 : "+ nList.getLength());
					int count=0;
					for(int temp = 0; temp < nList.getLength(); temp++){
						DogList dl = new DogList();
						Node nNode = nList.item(temp);
						if(nNode.getNodeType() == Node.ELEMENT_NODE){
							
							Element eElement = (Element) nNode;
							System.out.println("######################");
							//System.out.println(eElement.getTextContent());	getTextContent(): 전체 정보
							dl.setAge(getTagValue("age",eElement));
							dl.setCareAddr(getTagValue("careAddr", eElement));
							dl.setCareNm(getTagValue("careNm", eElement));
							dl.setCareTel(getTagValue("careTel", eElement));
							dl.setColorCd(getTagValue("colorCd", eElement));
							dl.setFilename(getTagValue("filename", eElement));
							dl.setHappenDt(getTagValue("happenDt", eElement));
							dl.setHappenPlace(getTagValue("happenPlace", eElement));
							dl.setKindCd(getTagValue("kindCd", eElement));
							dl.setNoticeEdt(getTagValue("noticeEdt", eElement));
							dl.setNoticeNo(getTagValue("noticeNo", eElement));
							dl.setOrgNm(getTagValue("orgNm", eElement));
							dl.setSexCd(getTagValue("sexCd", eElement));
							dl.setSpecialMark(getTagValue("specialMark", eElement));
							dl.setWeight(getTagValue("weight", eElement));
							count++;
//							System.out.println("강아지 나이  : " + getTagValue("age", eElement));
//							System.out.println("보호소 주소  : " + getTagValue("careAddr", eElement));
//							System.out.println("보호소 이름 : " + getTagValue("careNm", eElement));
//							System.out.println("보호소 전화번호  : " + getTagValue("careTel", eElement));
//							System.out.println("강아지 색깔 : " + getTagValue("colorCd", eElement));
//							System.out.println("파일이름 : " + getTagValue("filename", eElement));		//컬럼 삭제하기
//							System.out.println("happenDt : " + getTagValue("happenDt", eElement));
//							System.out.println("발견장소 : " + getTagValue("happenPlace", eElement));
//							System.out.println("고양이/개 : " + getTagValue("kindCd", eElement));		
//							System.out.println("중성화 여부 : " + getTagValue("neuterYn", eElement));	
//							System.out.println("공고시작일 : " + getTagValue("noticeSdt", eElement));	//컬럼 추가해야함
//							System.out.println("공고번호 : " + getTagValue("noticeNo", eElement));
//							System.out.println("공고 종료일 : " + getTagValue("noticeEdt", eElement));
//							System.out.println("관할 기관 : " + getTagValue("orgNm", eElement));
//							System.out.println("popfile : " + getTagValue("popfile", eElement));	//컬럼 추가해야함
//							System.out.println("입양상태 : " + getTagValue("processState", eElement));	//컬럼 추가해야함
//							System.out.println("성별 : " + getTagValue("sexCd", eElement));
//							System.out.println("특징 : " + getTagValue("specialMark", eElement));
//							System.out.println("몸무게 : " + getTagValue("weight", eElement));
							System.out.println("데이터 담은수:"+count);
							list.add(dl);
						}	// for end
					}	// if end
					
					
					System.out.println("page number : "+page);
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
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

}
