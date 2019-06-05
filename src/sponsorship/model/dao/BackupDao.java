package sponsorship.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;

public class BackupDao {

	public ArrayList<String> backup(Connection conn) {
		ArrayList<String> insert = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select no,id,name,phone,pay_method,amount,pay,status,deilvery_num,product_name,TO_CHAR(spon_date,'YYYY/MM/DD HH24:MI:SS') as time,memo,post,address,email,receive_name,receive_phone,vbank_name,vbank_num,vbank_holder,vbank_date from sponsorship";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			insert = new ArrayList<String>();
			while(rset.next()){
				String query = "insert into sponsorship values(";
				query += "'"+rset.getString("no")+"'";
				
				query += ","+(rset.getString("id")==null?"null":("'"+rset.getString("id")+"'"));

				query += ",'"+rset.getString("name")+"'";
				query += ",'"+rset.getString("phone")+"'";
				query += ",'"+rset.getString("pay_method")+"'";
			
				query += ","+rset.getInt("pay");
				query += ","+rset.getInt("amount");
				query += ","+rset.getInt("status");
				

				query += ","+(rset.getString("deilvery_num")==null?"null":("'"+rset.getString("deilvery_num")+"'"));
				
				query += ",'"+rset.getString("product_name")+"'";
				query += ",TO_DATE('"+rset.getString("time")+"','YYYY/MM/DD HH24:MI:SS')";
				query += ","+(rset.getString("memo")==null?"null":("'"+rset.getString("memo")+"'"));
				
				query += ",'"+rset.getString("post")+"'";
				query += ",'"+rset.getString("address")+"'";
				query += ",'"+rset.getString("email")+"'";
				query += ",'"+rset.getString("receive_name")+"'";
				query += ",'"+rset.getString("receive_phone")+"'";

				query += ","+(rset.getString("vbank_name")==null?"null":("'"+rset.getString("vbank_name")+"'"));
				query += ","+(rset.getString("vbank_num")==null?"null":("'"+rset.getString("vbank_num")+"'"));
				query += ","+(rset.getString("vbank_holder")==null?"null":("'"+rset.getString("vbank_holder")+"'"));
				query += ","+(rset.getString("vbank_date")==null?"null":("'"+rset.getString("vbank_date")+"'"));
				

				query += ");";
				insert.add(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return insert;
	}

}
