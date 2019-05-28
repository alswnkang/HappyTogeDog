package volunteer.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import volunteer.model.dao.VoluntaryRegisterDao;
import volunteer.model.vo.VoluntaryRegister;

public class VoluntaryRegisterService {

	public int insertVoluntaryRegister(VoluntaryRegister vr) {
		Connection conn = JDBCTemplate.getCon();
		vr.setDetail(vr.getDetail().replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\r\n", "<br>"));
		int result = new VoluntaryRegisterDao().insertVoluntaryRegister(conn, vr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
