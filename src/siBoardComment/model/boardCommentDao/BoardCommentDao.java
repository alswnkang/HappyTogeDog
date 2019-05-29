package siBoardComment.model.boardCommentDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BoardCommentDao {
	private Properties prop = new Properties();
	public BoardCommentDao() {
			String fileName = BoardCommentDao.class.getResource("/siSql/boardComment/boardCommentQuery.properties").getPath();
			System.out.println(fileName);
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



}
