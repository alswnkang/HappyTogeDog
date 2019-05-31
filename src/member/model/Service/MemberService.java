package member.model.Service;

import java.sql.SQLException;
import java.util.ArrayList;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.MemberPageData;

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
	
	public Member selectOne(String id) throws SQLException {
		Member m = new MemberDao().selectOne(id);
		return m;
	}
	public int memberModify(Member m) throws SQLException {
		int result = new MemberDao().memberModify(m);
		return result;
	}
	public int memberModify2(Member m) throws SQLException {
		int result = new MemberDao().memberModify2(m);
		return result;
	}
	public int delete(String id) throws SQLException {
		int result = new MemberDao().delete(id);
		return result;
	}
	public MemberPageData selectList(int reqPage) throws SQLException{
		// 페이지당 게시물 수
		int numPerPage = 5;
		//총 게시물수 구하기
		int totalCount = new MemberDao().totalCount();
		
		//총 페이지수 구하기
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		// 요청 페이지의 시작 게시물 번호와 끝 게시물 번호 구하기
		// 시작 게시물 번호
		int start = (reqPage-1)*numPerPage+1;
		
		//1페이지, 페이지당 게시물수 5 -> 1번 ~ 5번
		//2페이지, 페이지당 게시물수 5 -> 6번 ~ 10번
		//3페이지, 페이지당 게시물수 5 -> 11번 ~ 15번
		int end = reqPage*numPerPage;
		
		ArrayList<Member> list = new MemberDao().selectList(start,end);
		
		String pageNavi = "";
		int pageNaviSize = 5;
		// 요청 페이지가 1 ~ 5번 사이인경우
		// 1 2 3 4 5 [다음]
		// 요청페이지가  6 ~ 10번인 경우
		// [이전] 6 7 8 9 10 [다음]
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//이전 버튼 생성
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+(pageNo-1)+"'>이전</a>";
			
			// 페이지 번호 버튼 생성 (1 2 3 4 5)
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<span class=selectPage>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		// 다음 버튼 생성
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>다음</a>";
		}
		
		MemberPageData pd = new MemberPageData(list,pageNavi);
		return pd;
	}
	
	public MemberPageData searchUser(int reqPage,int select,String search) throws SQLException{
		// 페이지당 게시물 수
		int numPerPage = 5;
		//총 게시물수 구하기
		int totalCount = new MemberDao().searchCount(select,search);
		
		//총 페이지수 구하기
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		// 요청 페이지의 시작 게시물 번호와 끝 게시물 번호 구하기
		// 시작 게시물 번호
		int start = (reqPage-1)*numPerPage+1;
		
		//1페이지, 페이지당 게시물수 5 -> 1번 ~ 5번
		//2페이지, 페이지당 게시물수 5 -> 6번 ~ 10번
		//3페이지, 페이지당 게시물수 5 -> 11번 ~ 15번
		int end = reqPage*numPerPage;
		
		ArrayList<Member> list = new MemberDao().searchUser(start,end,select,search);
		
		String pageNavi = "";
		int pageNaviSize = 5;
		// 요청 페이지가 1 ~ 5번 사이인경우
		// 1 2 3 4 5 [다음]
		// 요청페이지가  6 ~ 10번인 경우
		// [이전] 6 7 8 9 10 [다음]
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//이전 버튼 생성
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+(pageNo-1)+"'>이전</a>";
			
			// 페이지 번호 버튼 생성 (1 2 3 4 5)
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<span class=selectPage>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		// 다음 버튼 생성
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>다음</a>";
		}
		
		MemberPageData pd = new MemberPageData(list,pageNavi);
		return pd;
	}
	public MemberPageData seeUser(int reqPage,int user) throws SQLException{
		// 페이지당 게시물 수
		int numPerPage = 5;
		//총 게시물수 구하기
		int totalCount = new MemberDao().seeUserCount(user);
		
		//총 페이지수 구하기
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		
		// 요청 페이지의 시작 게시물 번호와 끝 게시물 번호 구하기
		// 시작 게시물 번호
		int start = (reqPage-1)*numPerPage+1;
		
		//1페이지, 페이지당 게시물수 5 -> 1번 ~ 5번
		//2페이지, 페이지당 게시물수 5 -> 6번 ~ 10번
		//3페이지, 페이지당 게시물수 5 -> 11번 ~ 15번
		int end = reqPage*numPerPage;
		
		ArrayList<Member> list = new MemberDao().seeUser(start,end,user);
		
		String pageNavi = "";
		int pageNaviSize = 5;
		// 요청 페이지가 1 ~ 5번 사이인경우
		// 1 2 3 4 5 [다음]
		// 요청페이지가  6 ~ 10번인 경우
		// [이전] 6 7 8 9 10 [다음]
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//이전 버튼 생성
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+(pageNo-1)+"'>이전</a>";
			
			// 페이지 번호 버튼 생성 (1 2 3 4 5)
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage == pageNo) {
				pageNavi += "<span class=selectPage>"+pageNo+"</span>";
			}else {
				pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		// 다음 버튼 생성
		if(pageNo <= totalPage) {
			pageNavi += "<a class='btn' href='/adminPage?reqPage="+pageNo+"'>다음</a>";
		}
		
		MemberPageData pd = new MemberPageData(list,pageNavi);
		return pd;
	}
	
	
}
