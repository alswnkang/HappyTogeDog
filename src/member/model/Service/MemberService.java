package member.model.Service;

import java.sql.SQLException;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public int memberJoin(Member m) throws SQLException {
		System.out.println("서비스");
		int result = 0;
		if(m.getMemberLevel() > 0) {
			result = new MemberDao().memberJoin2(m);
		}else {
			result = new MemberDao().memberJoin(m);
	}
		return result;
	}
	
	public Member login(String id, String pw) throws SQLException {
		Member m = new MemberDao().login(id,pw);
		return m;
	}
}
