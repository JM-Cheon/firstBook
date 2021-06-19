package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class QnaReplyDTO implements Serializable{
	
	private static final long serialVersionUID = -7685458380084706512L;
	private int replyNum;
	private int postNum;
	private java.sql.Date replyDate;
	private MemberDTO member;
	private String replyContent;
	
	public QnaReplyDTO() {
		
	}

	public QnaReplyDTO(int replyNum, int postNum, Date replyDate, MemberDTO member, String replyContent) {
		super();
		this.replyNum = replyNum;
		this.postNum = postNum;
		this.replyDate = replyDate;
		this.member = member;
		this.replyContent = replyContent;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public java.sql.Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(java.sql.Date replyDate) {
		this.replyDate = replyDate;
	}

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QnaReplyDTO [replyNum=" + replyNum + ", postNum=" + postNum + ", replyDate=" + replyDate + ", member="
				+ member + ", replyContent=" + replyContent + "]";
	}

}
