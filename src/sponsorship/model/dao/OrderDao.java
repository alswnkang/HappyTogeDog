package sponsorship.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.vo.OrderInfoVO;

public class OrderDao {

	public int insertOrder(Connection conn, OrderInfoVO orderInfo) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into sponsorship values(?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, orderInfo.getNo());
		pstmt.setString(2, orderInfo.getId());
		pstmt.setString(3, orderInfo.getName());
		pstmt.setString(4, orderInfo.getPhone());
		pstmt.setString(5, orderInfo.getPayMethod());
		pstmt.setInt(6, orderInfo.getPay());
		pstmt.setInt(7, orderInfo.getAmount());
		pstmt.setInt(8, orderInfo.getStatus());
		pstmt.setString(9, orderInfo.getDeilveryNum());
		pstmt.setString(10, orderInfo.getProductName());
		pstmt.setString(11, orderInfo.getMemo());
		pstmt.setString(12, orderInfo.getPost());
		pstmt.setString(13, orderInfo.getAddress());
		pstmt.setString(14, orderInfo.getEmail());
		pstmt.setString(15, orderInfo.getReceiveName());
		pstmt.setString(16, orderInfo.getReceivePhone());
		result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);

		return result;
	}

	public OrderInfoVO selectOrder(Connection conn, String no) throws SQLException {
		OrderInfoVO orderInfo = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYY/MM/DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone from sponsorship where no=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		rset = pstmt.executeQuery();
		if(rset.next()){
			orderInfo = new OrderInfoVO();
			orderInfo.setNo(rset.getString("no"));
			orderInfo.setId(rset.getString("id"));
			orderInfo.setName(rset.getString("name"));
			orderInfo.setPhone(rset.getString("phone"));
			orderInfo.setPayMethod(rset.getString("pay_method"));
			orderInfo.setAmount(rset.getInt("amount"));
			orderInfo.setPay(rset.getInt("pay"));
			orderInfo.setStatus(rset.getInt("status"));
			orderInfo.setDeilveryNum(rset.getString("deilvery_num"));
			orderInfo.setProductName(rset.getString("product_name"));
			orderInfo.setSponDate(rset.getString("time"));
			orderInfo.setMemo(rset.getString("memo"));
			orderInfo.setPost(rset.getString("post"));
			orderInfo.setAddress(rset.getString("address"));
			orderInfo.setEmail(rset.getString("email"));
			orderInfo.setReceiveName(rset.getString("receive_name"));
			orderInfo.setReceivePhone(rset.getString("receive_phone"));
			
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return orderInfo;
	}
	
	public ArrayList<OrderInfoVO> selectOrder(Connection conn) throws SQLException {
		ArrayList<OrderInfoVO> orderList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYYY/MM/DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone from sponsorship ";
		
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		orderList = new ArrayList<OrderInfoVO>();
		while(rset.next()){
			OrderInfoVO orderInfo = new OrderInfoVO();
			orderInfo.setNo(rset.getString("no"));
			orderInfo.setId(rset.getString("id"));
			orderInfo.setName(rset.getString("name"));
			orderInfo.setPhone(rset.getString("phone"));
			orderInfo.setPayMethod(rset.getString("pay_method"));
			orderInfo.setAmount(rset.getInt("amount"));
			orderInfo.setPay(rset.getInt("pay"));
			orderInfo.setStatus(rset.getInt("status"));
			orderInfo.setDeilveryNum(rset.getString("deilvery_num"));
			orderInfo.setProductName(rset.getString("product_name"));
			orderInfo.setSponDate(rset.getString("time"));
			orderInfo.setMemo(rset.getString("memo"));
			orderInfo.setPost(rset.getString("post"));
			orderInfo.setAddress(rset.getString("address"));
			orderInfo.setEmail(rset.getString("email"));
			orderInfo.setReceiveName(rset.getString("receive_name"));
			orderInfo.setReceivePhone(rset.getString("receive_phone"));
			orderList.add(orderInfo);
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return orderList;
	}

	public int findOrder(Connection conn, String no, String phone) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from sponsorship where no=? and phone=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		pstmt.setString(2, phone);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result++;
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int totalPrice(Connection conn) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select sum(pay) as price from sponsorship where status!=0";
		
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result = rset.getInt("price");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int totalCount(Connection conn) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select count(*) as cnt from sponsorship ";
		
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		if(rset.next()){
			result = rset.getInt("cnt");
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
