package finddog.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import adoption.model.dao.FindDogDao;
import adoption.model.vo.DogList;
import adoption.model.vo.SearchDogPageData;
import common.JDBCTemplate;
import finddog.model.dao.SearchDogDao;
import finddog.model.vo.Kind;
import siBoard.model.boardDao.BoardDao;
import siBoard.model.boardVo.Board;
import siBoard.model.boardVo.BoardPageData;
import siTemplete.JDBCTemplete;

public class SearchDogService {

	public ArrayList<Kind> getKindCode() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		ArrayList<Kind> list = new SearchDogDao().getKindCode(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public SearchDogPageData selectList(int page, String sDay, String eDay, String kind, String cityCode) {
		
		ArrayList<DogList> list= new SearchDogDao().getList(page,sDay,eDay,kind,cityCode);
		
		//네비바 생성
		String pageNavi="";
		if(page==1) {   //시작페이지 버튼
			pageNavi+="<a href='/printSearchDog?page=1'>1</a>";
			pageNavi+="<a href='/printSearchDog?page=2'>2</a>";
			pageNavi+="<a href='/printSearchDog?page=3'>3</a>";	
			pageNavi+="<a href='/printSearchDog?page=4'>4</a>";
			pageNavi+="<a href='/printSearchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";
		}else if(page==2) {  //2번페이지 이후
			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'</a>";
			pageNavi+="<a href='/printSearchDog?page=1'>1</a>";
			pageNavi+="<a href='/printSearchDog?page=2'>2</a>";
			pageNavi+="<a href='/printSearchDog?page=3'>3</a>";	
			pageNavi+="<a href='/printSearchDog?page=4'>4</a>";
			pageNavi+="<a href='/printSearchDog?page=5'>5</a>";
 			pageNavi+="<a href='' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";
		}else {
			pageNavi+="<a href='/printSearchDog?page="+(page-1)+"' class='paging-arrow next-arrrow'><img src='/img/left_arrow.png' style='width:30px;height:30px'</a>";
			pageNavi+="<a href='/printSearchDog?page="+(page-2)+"'>"+(page-2)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+(page-1)+"'>"+(page-1)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+page+"'>"+page+"</a>";	
			pageNavi+="<a href='/printSearchDog?page="+(page+1)+"'>"+(page+1)+"</a>";
			pageNavi+="<a href='/printSearchDog?page="+(page+2)+"'>"+(page+2)+"</a>";
 			pageNavi+="<a href='/printSearchDog?page="+(page+1)+"' class='paging-arrow next-arrrow'><img src='/img/right_arrow.png' style='width:30px;height:30px'</a>";		
		}
		SearchDogPageData sdpd = new SearchDogPageData(list,pageNavi);
		return sdpd;
	}

	public BoardPageData boardAll(int reqPage) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 10;
		int totalCount = new SearchDogDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new SearchDogDao().boardAll(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 10;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/takeBoard?reqPage="+(pageNo-1)+"'>이전</a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/takeBoard?reqPage="+pageNo+"'>다음</a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}

}
