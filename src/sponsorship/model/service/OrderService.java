package sponsorship.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.dao.OrderDao;
import sponsorship.model.vo.OrderInfoVO;
import sponsorship.model.vo.OrderListVO;
import sponsorship.model.vo.TotalOrder;

public class OrderService {

	public int insertOrder(OrderInfoVO orderInfo) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		
		int result = new OrderDao().insertOrder(conn,orderInfo);
		if(result>0){
			JDBCTemplate.commit(conn);
		}else{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public OrderInfoVO selectOrder(String no) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		OrderInfoVO orderInfo = new OrderDao().selectOrder(conn,no);
		JDBCTemplate.close(conn);
		return orderInfo;
	}
	
	public OrderListVO selectOrder(int reqPage) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		
		int total = 0;
		/*
		if(type.equals("")) total = new NoticeDao().listCnt(conn);
		else total = new NoticeDao().listCnt(conn, type, keyword);
		*/
		total = new OrderDao().totalCount(conn);
		
		int pageNum = 5;//한 페이지에 출력할 게시물 개수
		int totalPage = (total%pageNum==0)?(total/pageNum):(total/pageNum)+1;
		
		
		int start = (reqPage*pageNum-pageNum)+1;//시작 번호
		int end  = reqPage*pageNum;
		ArrayList<OrderInfoVO> orderinfoList = null;
		/*
		if(type.equals("")) list = new NoticeDao().noticeList(conn,start,end);
		else list = new NoticeDao().noticeList(conn,start,end,type,keyword);
		*/
		orderinfoList = new OrderDao().selectOrder(conn,start,end);
		
		int totalNavi = 5;//노출시킬 네비 버튼 개수
		String pageNavi = "";
		
		int pageNo = ((reqPage-1)/totalNavi)*totalNavi+1;
		if(pageNo != 1) {
			//pageNavi += "<a class='btn' href='/orderList?reqPage="+(pageNo-1)+"&type="+type+"&keyword="+keyword+"'>[이전]</a>";//[이전] 버튼 생성.
			pageNavi += "<a class='paging-arrow prev-arrow' href='/orderList?reqPage="+(pageNo-1)+"'><img src='/img/left_arrow.png' style='width:30px;height:30px;'></a> ";//[이전] 버튼 생성.
			
		}
		int i = 1;
		while(!(i++>totalNavi || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<a class='cur'>"+pageNo+"</a> ";
			}else {
				//pageNavi += "<a class='btn' href='/orderList?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>"+pageNo+"</a>";
				pageNavi += "<a href='/orderList?reqPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		if(pageNo <= totalPage) {
			//pageNavi += "<a class='btn' href='/orderList?reqPage="+pageNo+"&type="+type+"&keyword="+keyword+"'>[다음]</a>";//[다음] 버튼 생성
			pageNavi += "<a class='paging-arrow next-arrrow' href='/orderList?reqPage="+pageNo+"'><img src='/img/right_arrow.png' style='width:30px;height:30px;'></a>";//[다음] 버튼 생성
		}
		
		OrderListVO orderList = new OrderListVO(orderinfoList, pageNavi);
		JDBCTemplate.close(conn);
		return orderList;
	}

	public int findOrder(String no, String phone) throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int result = new OrderDao().findOrder(conn,no,phone);
		JDBCTemplate.close(conn);
		return result;
	}

	public TotalOrder totalOrder() throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		int price = new OrderDao().totalPrice(conn);
		int count = new OrderDao().totalCount(conn);
		JDBCTemplate.close(conn);
		TotalOrder total = new TotalOrder(price, count);
		return total;
	}

}
