package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class NoticeDTO implements Serializable{
	
	private static final long serialVersionUID = -7685458380084706512L;

	private int noticeNum;
	private java.sql.Date noticeDate;
	private String noticeName;
	private int noticeViewCount;
	private String noticeContent;
	private String noticeStatus;
	
	public NoticeDTO() {
	}

	public NoticeDTO(int noticeNum, Date noticeDate, String noticeName, int noticeViewCount, String noticeContent,
			String noticeStatus) {
		super();
		this.noticeNum = noticeNum;
		this.noticeDate = noticeDate;
		this.noticeName = noticeName;
		this.noticeViewCount = noticeViewCount;
		this.noticeContent = noticeContent;
		this.noticeStatus = noticeStatus;
	}

	public int getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public int getNoticeViewCount() {
		return noticeViewCount;
	}

	public void setNoticeViewCount(int noticeViewCount) {
		this.noticeViewCount = noticeViewCount;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noticeNum=" + noticeNum + ", noticeDate=" + noticeDate + ", noticeName=" + noticeName
				+ ", noticeViewCount=" + noticeViewCount + ", noticeContent=" + noticeContent + ", noticeStatus="
				+ noticeStatus + "]";
	}

}
