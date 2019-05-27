package member.model.Service;

import java.sql.SQLException;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {

	public int memberJoin(Member m) throws SQLException {
		System.out.println("서비스");
		int result = new MemberDao().memberJoin(m);
		return result;
	}
}
