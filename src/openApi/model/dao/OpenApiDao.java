package openApi.model.dao;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import openApi.model.vo.CareCode;
import	openApi.model.vo.cityCode;
import openApi.model.vo.getCareCode;

public class OpenApiDao {
	
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
	    
		
			// 페이지 초기값 
			try{
				while(true){
					
					// parsing할 url 지정(API 키 포함해서)
					String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/sido?ServiceKey=BbQpcBG0GiwHYBNrM7gxpyWUTHH9PAwSZgtjL%2Bj%2FIb6YYiHG86O4qZta75KyCzkPPBmRjXzOHffarvGnn67JzA%3D%3D";
					
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
				
					
					
				
				
				break;
					
					
				
				}	// while end
				
			} catch (Exception e){	
				e.printStackTrace();
			}
			
		
		
			return list;
		
		
		
		
		
		
	}
	
	
	
	public ArrayList<cityCode> getAreaCode(String code) {
		// TODO Auto-generated method stub
		ArrayList<cityCode> list = new ArrayList<cityCode>();
	    
	
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
	
	public ArrayList<CareCode> getCareCode(String arecode, String citcode) {
		// TODO Auto-generated method stub
		
		ArrayList<CareCode> list = new ArrayList<CareCode>();
	    
	
		// 페이지 초기값 
		try{
			while(true){
				// parsing할 url 지정(API 키 포함해서)
				String url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/shelter?upr_cd="+citcode+"&org_cd="+arecode+"&ServiceKey=BbQpcBG0GiwHYBNrM7gxpyWUTHH9PAwSZgtjL%2Bj%2FIb6YYiHG86O4qZta75KyCzkPPBmRjXzOHffarvGnn67JzA%3D%3D";
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				System.out.println("여기들어왔는지 응답좀...");
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
						cc.setCareNm(getTagValue("careNm", eElement));
						cc.setCareRegNo(getTagValue("careRegNo", eElement));
					
						System.out.println("여기들어왔나요?");
						System.out.println(getTagValue("careNm", eElement));
					
						list.add(cc);
					}	// for end
				}	// if end
				
				System.out.println("DAO : "+list.size());
				
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
