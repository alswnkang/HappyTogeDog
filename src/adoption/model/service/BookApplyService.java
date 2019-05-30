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
	public BookApplyPageData selectList(int reqPage,String id) throws SQLException {
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
		String pageNavi ="";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/5)*5+1;
		if(pageNo!=1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/dogAdopList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize||pageNo>100)) {		//pageNo>100 : totalPage를 구하는 방법 생각해보기
			if(reqPage==pageNo) {
				pageNavi += "<span class='cur'>"+pageNo+"</span>";
			}else {
				pageNavi +="<a href='/dogAdopList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= 100) {		//pageN<=100 : totalPage를 구하는 방법 생각해보기
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
	
	
}
