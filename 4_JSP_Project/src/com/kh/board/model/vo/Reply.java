package com.kh.board.model.vo;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private String replyContent;
	private int refBno;
	private String boardWriter;
	private Date createDate;
	private String status;
	
	
	public Reply() {
		
	}


	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public int getRefBno() {
		return refBno;
	}


	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Reply(int replyNo, String replyContent, int refBno, String boardWriter, Date createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBno=" + refBno + ", boardWriter="
				+ boardWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
}
