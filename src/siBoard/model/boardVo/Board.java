package siBoard.model.boardVo;

import java.sql.Date;

public class Board {
	private int boardRnum;
	private int boardNo;
	private int boardType;
	private String boardId;
	private String boardName;
	private String boardTitle;
	private String boardContent;
	private String boardFilename;
	private String boardFilepath;
	private Date boardDate;
	private int boardCount;
	private int boardSecret;
	private String boardPw;
	private String boardPrdCode;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardRnum, int boardNo, int boardType, String boardId, String boardName, String boardTitle,
			String boardContent, String boardFilename, String boardFilepath, Date boardDate, int boardCount,
			int boardSecret, String boardPw, String boardPrdCode) {
		super();
		this.boardRnum = boardRnum;
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.boardId = boardId;
		this.boardName = boardName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardFilename = boardFilename;
		this.boardFilepath = boardFilepath;
		this.boardDate = boardDate;
		this.boardCount = boardCount;
		this.boardSecret = boardSecret;
		this.boardPw = boardPw;
		this.boardPrdCode = boardPrdCode;
	}
	public int getBoardRnum() {
		return boardRnum;
	}
	public void setBoardRnum(int boardRnum) {
		this.boardRnum = boardRnum;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBoardSecret() {
		return boardSecret;
	}
	public void setBoardSecret(int boardSecret) {
		this.boardSecret = boardSecret;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public String getBoardPrdCode() {
		return boardPrdCode;
	}
	public void setBoardPrdCode(String boardPrdCode) {
		this.boardPrdCode = boardPrdCode;
	}
	
}
