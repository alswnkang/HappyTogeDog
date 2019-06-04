package adoption.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import adoption.model.dao.PrintShelterDao;
import adoption.model.vo.ShelterPageData;
import common.JDBCTemplate;
import member.model.vo.Member;

public class PrintShelterService {

	public ShelterPageData printShelter(int city,int reqPage) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =JDBCTemplate.getCon();
		String pageNavi ="";
		int numPerPage =7;
		int totalCount = new PrintShelterDao().totalCount(conn,city);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		int start =(reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		
		ShelterPageData spd = new PrintShelterDao().selectList(city,conn,start,end);
		
		int pageNaviSize =5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		
		if(pageNo!=1) {
			pageNavi +="<a class='paging-arrow prev-arrow' href='/printShelter?page="+(pageNo-1)+"'>이전</a>";	
		}
		
		int i =1;
		while(!(i++>pageNaviSize||pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi += "<span calss=selectPage>"+pageNo+"</span>";
			}else {
				pageNavi +="<a class='cur' href='/printShelter?page="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			pageNavi += "<a class='paging-arrow next-arrow' href='/printShelter?page="+(pageNo+1)+"'>다음</a>";
		}
		spd.setPageNavi(pageNavi);
		
		JDBCTemplate.close(conn);

		
		return spd;
	}

	public int addShelter(Member m) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getCon();
		
		int result=new PrintShelterDao().addShelter(conn, m);
		
		
		return result;
	}

}
