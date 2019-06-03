package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.BookApplyDao;
import adoption.model.vo.BookApply;
import adoption.model.vo.BookApplyPageData;
import adoption.model.vo.DogList;
import adoption.model.vo.SearchDogPageData;
import common.JDBCTemplate;

public class BookApplyService {
	//예약된 방문 시간 구해오기
	public ArrayList<String> possibleTime(String visitDate, String careNm){
		System.out.println("1Service");
		Connection conn = JDBCTemplate.getCon();
		ArrayList<String> list = new BookApplyDao().possibleTime(conn,visitDate, careNm);
		System.out.println("3Service");
		JDBCTemplate.close(conn);
		return list;
	}
	//방문예약 신청
	public int reservation(BookApply ba) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().reservation(conn, ba);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	//마이페이지에 방문예약 신청내역 보여주기
	public BookApplyPageData selectList(int reqPage,String id){
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 3;
		int totalCount = new BookApplyDao().reservationCount(conn,id);
		System.out.println(totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().selectList(conn, start, end, id);
		
		String pageNavi ="";
		int pageNaviSize = 3;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			/*pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage=1'><<</a>";*/
			pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/reservationMypage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
			/*pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+totalPage+"'>>></a>";*/
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}
	
	//유기견 리스트 받아오기
	public SearchDogPageData dogList(int reqPage) {
		//기간을 어떻게 잡아줘야할까?
		ArrayList<DogList> list= new BookApplyDao().dogList(reqPage);
//		System.out.println(list.get(0).getSexCd());
		int totalCount = Integer.parseInt(list.get(0).getOrgNm());		//totalCount
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/5)*5+1;
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/dogAdopList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalCount)) {	
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/dogAdopList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalCount) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/dogAdopList?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
			
		}
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}
	
	//보호소 방문가능시간 가져오기
	public String careTime(String careNm) {
		Connection conn = JDBCTemplate.getCon();
		String careTime = new BookApplyDao().careTime(conn, careNm);
		JDBCTemplate.close(conn);
		return careTime;
	}
	
	//보호소가 방문예약 신청내역 리스트 확인
	public BookApplyPageData reservationCareMypage(int reqPage, String code, String startDay, String endDay) throws SQLException {
		if(startDay==null) {
			startDay="";
		}
		if(endDay==null) {
			endDay="";
		}
		System.out.println("목록 service왔다");
		Connection conn = JDBCTemplate.getCon();
		ArrayList<BookApply> list = new ArrayList<BookApply>();
		int numPerPage = 3;
		int totalCount = new BookApplyDao().reservationCareCount(conn,code, startDay, endDay);
		System.out.println("service().reservationCareMypage() totalCount : "+totalCount);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		list = new BookApplyDao().reservationCareList(conn, start, end, code, startDay, endDay);
		System.out.println("Dao에서 리스트 구해옴");
		String pageNavi ="";
		int pageNaviSize = 3;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			/*pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationMypage?reqPage=1'><<</a>";*/
			pageNavi += "<a class='paging-arrow prev-arrow' href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/reservationCareMypage?startDay="+startDay+"&endDay="+endDay+"&reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
			/*pageNavi += "<a class='paging-arrow next-arrow' href='/reservationMypage?reqPage="+totalPage+"'>>></a>";*/
		}
		JDBCTemplate.close(conn);
		BookApplyPageData bp = new BookApplyPageData(list, pageNavi);
		return bp;
	}
	
	//보호소가 방문예약 신청내역 내용 확인
	public BookApply viewOne(int no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		System.out.println("no: "+no);
		BookApply b = new BookApplyDao().viewOne(conn ,no);
		JDBCTemplate.close(conn);
		return b;
	}
	
	//보호소회원이 예약상태 업데이트
	public int updateStatus(int status,int no) {
		System.out.println("방문예약상태(service) : "+status);
		Connection conn = JDBCTemplate.getCon();
		int result = new BookApplyDao().updateStatus(conn, status, no);
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;	
	}
}
