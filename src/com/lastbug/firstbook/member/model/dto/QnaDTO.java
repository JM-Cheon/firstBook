package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class QnaDTO implements Serializable{
	
	private static final long serialVersionUID = -7685458380084706512L;
	private int postNum;
	private MemberDTO memNum;
	private String qnaTitle;
	private String qnaOpenYn;
	private String qnaAnswerYn;
	private java.sql.Date qnaDate;
	private String qnaContent;
	
	public QnaDTO() {
		
	}

	public QnaDTO(int postNum, MemberDTO memNum, String qnaTitle, String qnaOpenYn, String qnaAnswerYn, Date qnaDate,
			String qnaContent) {
		super();
		this.postNum = postNum;
		this.memNum = memNum;
		this.qnaTitle = qnaTitle;
		this.qnaOpenYn = qnaOpenYn;
		this.qnaAnswerYn = qnaAnswerYn;
		this.qnaDate = qnaDate;
		this.qnaContent = qnaContent;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public MemberDTO getMemNum() {
		return memNum;
	}

	public void setMemNum(MemberDTO memNum) {
		this.memNum = memNum;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaOpenYn() {
		return qnaOpenYn;
	}

	public void setQnaOpenYn(String qnaOpenYn) {
		this.qnaOpenYn = qnaOpenYn;
	}

	public String getQnaAnswerYn() {
		return qnaAnswerYn;
	}

	public void setQnaAnswerYn(String qnaAnswerYn) {
		this.qnaAnswerYn = qnaAnswerYn;
	}

	public java.sql.Date getQnaDate() {
		return qnaDate;
	}

	public void setQnaDate(java.sql.Date qnaDate) {
		this.qnaDate = qnaDate;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QnaDTO [postNum=" + postNum + ", memNum=" + memNum + ", qnaTitle=" + qnaTitle + ", qnaOpenYn="
				+ qnaOpenYn + ", qnaAnswerYn=" + qnaAnswerYn + ", qnaDate=" + qnaDate + ", qnaContent=" + qnaContent
				+ "]";
	}
	
}
