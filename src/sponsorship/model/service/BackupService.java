package sponsorship.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import sponsorship.model.dao.BackupDao;

public class BackupService {

	public ArrayList<String> backup() {
		Connection conn = JDBCTemplate.getCon();
		ArrayList<String> insert = new BackupDao().backup(conn);
		JDBCTemplate.close(conn);
		return insert;
	}

}
