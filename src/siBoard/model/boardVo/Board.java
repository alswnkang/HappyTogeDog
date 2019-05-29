package siBoard.model.boardVo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private int boardLevel;
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
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int boardNo, int boardLevel, String boardId, String boardName, String boardTitle, String boardContent,
			String boardFilename, String boardFilepath, Date boardDate, int boardCount, int boardSecret,
			String boardPw) {
		super();
		this.boardNo = boardNo;
		this.boardLevel = boardLevel;
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
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
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
	
}
