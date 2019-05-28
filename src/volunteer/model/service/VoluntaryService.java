package volunteer.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import volunteer.model.dao.VoluntaryDao;
import volunteer.model.vo.VoluntaryListData;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryService {

	// 봉사활동 공고 등록
	public int insertVoluntaryRegister(VoluntaryRegister vr) {
		Connection conn = JDBCTemplate.getCon();
		vr.setDetail(vr.getDetail().replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\r\n", "<br>"));
		int result = new VoluntaryDao().insertVoluntaryRegister(conn, vr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//봉사활동 리스트+페이징
	public VoluntaryListData voluntaryList(int reqPage) {
		Connection conn = JDBCTemplate.getCon();
		
		int numPerPage = 10;
		int totalCount = new VoluntaryDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<VoluntaryRegister> list = new VoluntaryDao().voluntaryList(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/volunteerList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur' href='#'>"+pageNo+"</a>";
			}else {
				pageNavi += "<a class='' href='/volunteerList?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/volunteerList?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		VoluntaryListData vld = new VoluntaryListData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vld;
	}

	// 봉사활동 신청 게시판 :: 검색 + 페이징
	public VoluntaryListData searchVoluntary(int reqPage, String type, String keyword) {
		Connection conn = JDBCTemplate.getCon();
		
		int numPerPage = 10;
		int totalCount = 0;
		switch(type) {
			case "title": totalCount = new VoluntaryDao().totalCountTitle(conn, keyword); break;
			case "name": totalCount = new VoluntaryDao().totalCountName(conn, keyword); break;
		}
		int totalPage = (totalCount%numPerPage == 0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		ArrayList<VoluntaryRegister> list = null;
		switch(type) {
			case "title": list = new VoluntaryDao().searchKeywordTitle(conn, keyword, start, end); break;
			case "name": list = new VoluntaryDao().searchKeywordName(conn, keyword, start, end); break;
		}
		String pageNavi = "";
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo != 1) {
			pageNavi += "<a class='paging-arrow prev-arrow' href='/searchVoluntary?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a>";
		}
		
		int i = 1;
		while( !(i++>pageNaviSize || pageNo>totalPage) ) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur' href='#'>"+pageNo+"</a>";
			}else {
				pageNavi += "<a class='' href='/searchVoluntary?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrrow' href='/searchVoluntary?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";
		}
		VoluntaryListData vld = new VoluntaryListData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		
		return vld;
	}

	// 봉사활동 뷰
	public VoluntaryRegister voluntaryView(int no) {
		Connection conn = JDBCTemplate.getCon();
		VoluntaryRegister vr = new VoluntaryDao().voluntaryView(conn, no);
		JDBCTemplate.close(conn);
		return vr;
	}

	// 봉사활동 공고 수정
	public int updateVoluntary(VoluntaryRegister vr) {
		Connection conn = JDBCTemplate.getCon();
		int result = new VoluntaryDao().updateVoluntary(conn, vr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	// 봉사활동 공고 삭제
	public int deleteVoluntary(int no) {
		Connection conn = JDBCTemplate.getCon();
		int result = new VoluntaryDao().deleteVoluntary(conn, no);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
	
	
	

}
