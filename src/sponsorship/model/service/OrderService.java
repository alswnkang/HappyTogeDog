package sponsorship.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.dao.OrderDao;
import sponsorship.model.vo.OrderInfoVO;
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
	
	public ArrayList<OrderInfoVO> selectOrder() throws SQLException {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<OrderInfoVO> orderList = new OrderDao().selectOrder(conn);
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
