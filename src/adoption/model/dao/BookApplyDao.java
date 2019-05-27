package adoption.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import adoption.model.vo.BookApply;
import adoption.model.dao.BookApplyDao;

public class BookApplyDao {
	private Properties prop = new Properties();
	public BookApplyDao() {
		String fileName = BookApplyDao.class.getResource("/sql/notice/noticeQuery3.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int reservation(Connection conn, BookApply ba) {
		
	}
}
