package com.example.demo.board.domain;

import java.util.Date;

public class CommentVO {

	private int cno;   // 댓글 키 
	private int bno;   // 게시글 키
	private String boardName; //게시판 이름
	private String content; // 내용
	private String writer; //글쓴이이름
	private String uid;  // 아이디 
	private Date reg_date; //날짜 
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String comment) {
		this.content = comment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
