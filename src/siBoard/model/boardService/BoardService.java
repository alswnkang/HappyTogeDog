package siBoard.model.boardService;

import java.sql.Connection;
import java.util.ArrayList;

import siBoard.model.boardDao.BoardDao;
import siBoard.model.boardVo.Board;
import siBoard.model.boardVo.BoardPageData;
import siTemplete.JDBCTemplete;

public class BoardService {
	public BoardPageData boardAll(int reqPage){
		Connection conn = JDBCTemplete.getConnection();
		int numPerPage = 10;
		int totalCount = new BoardDao().totalCount(conn);
		int totalPage = (totalCount%numPerPage==0)?(totalCount/numPerPage):(totalCount/numPerPage)+1;
		int start = (reqPage-1)*numPerPage+1;
		int end = reqPage*numPerPage;
		ArrayList<Board> list = new BoardDao().boardAll(conn,start,end);
		String pageNavi = "";
		int pageNaviSize = 10;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		if(pageNo!=1) {
			pageNavi+="<a href='/siPreBoard?reqPage="+(pageNo-1)+"'>이전</a>";
		}
		int i = 1;
		while(!(i++>pageNaviSize || pageNo>totalPage)) {
			if(reqPage==pageNo) {
				pageNavi+="<span>"+pageNo+"</span>";
			}else {
				pageNavi+="<a href='/siPreBoard?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		if(pageNo<=totalPage) {
			pageNavi+="<a href='/siPreBoard?reqPage="+pageNo+"'>다음</a>";
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData(list,pageNavi);
		return bp;
	}
	
	public int boardInsert(Board b) {
		Connection conn = JDBCTemplete.getConnection();
		int result = new BoardDao().boardInsert(conn, b);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public Board boardView(int boardNo) {
		Connection conn = JDBCTemplete.getConnection();
		Board b = new BoardDao().boardView(conn, boardNo);
		JDBCTemplete.close(conn);
		return b;
	}
	public int boardUpdate(int boardNo, String boardTitle, String boardContent, String boardFilename, String boardFilepath) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new BoardDao().boardUpdate(conn, boardNo, boardTitle, boardContent, boardFilename, boardFilepath);
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public Board boardUpdateOriginal(int boardNo,Board b) {
		Connection conn = JDBCTemplete.getConnection();
		b = new BoardDao().boardUpdateOriginal(conn, boardNo, b);
		JDBCTemplete.close(conn);
		return b;
	}
	public int boardDelete(int boardNo) {
		Connection conn = JDBCTemplete.getConnection();
		int result= new BoardDao().boardDelete(conn, boardNo);		
		if(result>0) {
			JDBCTemplete.commit(conn);
		}else {
			JDBCTemplete.rollback(conn);
		}
		JDBCTemplete.close(conn);
		return result;
	}
	public ArrayList<Board> boardSearch(String searchType, String searchKeyword){
		Connection conn = JDBCTemplete.getConnection();
		ArrayList<Board> list = new ArrayList<Board>();
		if(searchType.equals("boardName")) {
			list = new BoardDao().boardSearchName(conn,searchKeyword);
		}else if(searchType.equals("boardTitle")) {
			list = new BoardDao().boardSearchTitle(conn,searchKeyword);
		}
		JDBCTemplete.close(conn);
		BoardPageData bp = new BoardPageData();
		return list;
	}
}
