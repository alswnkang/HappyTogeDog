package siBoardComment.model.boardCommentVo;

import java.sql.Date;

public class BoardComment {
	private int boardCommentNo;
	private int boardCommentLevel;
	private String boardCommentId;
	private String boardCommentName;
	private String boardCommentContent;
	private int boardRef;
	private int boardCommentRef;
	private Date boardCommentDate;
	public BoardComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardComment(int boardCommentNo, int boardCommentLevel, String boardCommentId, String boardCommentName,
			String boardCommentContent, int boardRef, int boardCommentRef, Date boardCommentDate) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommentId = boardCommentId;
		this.boardCommentName = boardCommentName;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentDate = boardCommentDate;
	}
	public int getBoardCommentNo() {
		return boardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}
	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}
	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}
	public String getBoardCommentId() {
		return boardCommentId;
	}
	public void setBoardCommentId(String boardCommentId) {
		this.boardCommentId = boardCommentId;
	}
	public String getBoardCommentName() {
		return boardCommentName;
	}
	public void setBoardCommentName(String boardCommentName) {
		this.boardCommentName = boardCommentName;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBoardCommentRef() {
		return boardCommentRef;
	}
	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}
	public Date getBoardCommentDate() {
		return boardCommentDate;
	}
	public void setBoardCommentDate(Date boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}
	
}
