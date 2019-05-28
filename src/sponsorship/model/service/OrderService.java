package sponsorship.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import common.JDBCTemplate;
import sponsorship.model.dao.OrderDao;
import sponsorship.model.vo.OrderInfoVO;

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

}
