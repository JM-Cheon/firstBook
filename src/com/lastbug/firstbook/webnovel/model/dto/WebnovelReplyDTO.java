package com.lastbug.firstbook.webnovel.model.dto;

import java.util.Date;

import com.lastbug.firstbook.member.model.dto.MemberDTO;

public class WebnovelReplyDTO {
	
	public WebnovelReplyDTO() {}

	private int replyNum;
	private MemberDTO memNum;
	public WebnovelReplyDTO(int replyNum, MemberDTO memNum, MemberDTO memId, WebNovelInfoDTO webNovNum, Date replyDate,
			String replyContent) {
		this.replyNum = replyNum;
		this.memNum = memNum;
		this.memId = memId;
		this.webNovNum = webNovNum;
		this.replyDate = replyDate;
		this.replyContent = replyContent;
	}
	public MemberDTO getMemId() {
		return memId;
	}
	public void setMemId(MemberDTO memId) {
		this.memId = memId;
	}

	private MemberDTO memId;
	private WebNovelInfoDTO webNovNum;
	private java.util.Date replyDate;
	private String replyContent;
	public WebnovelReplyDTO(int replyNum, MemberDTO memNum, WebNovelInfoDTO webNovNum, Date replyDate,
			String replyContent) {
		this.replyNum = replyNum;
		this.memNum = memNum;
		this.webNovNum = webNovNum;
		this.replyDate = replyDate;
		this.replyContent = replyContent;
	}
	@Override
	public String toString() {
		return "WebnovelReplyDTO [replyNum=" + replyNum + ", memNum=" + memNum + ", memId=" + memId + ", webNovNum="
				+ webNovNum + ", replyDate=" + replyDate + ", replyContent=" + replyContent + "]";
	}
	public int getReplyNum() {
		return replyNum;
	}
	public MemberDTO getMemNum() {
		return memNum;
	}
	public WebNovelInfoDTO getWebNovNum() {
		return webNovNum;
	}
	public java.util.Date getReplyDate() {
		return replyDate;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public void setMemNum(MemberDTO memNum) {
		this.memNum = memNum;
	}
	public void setWebNovNum(WebNovelInfoDTO webNovNum) {
		this.webNovNum = webNovNum;
	}
	public void setReplyDate(java.util.Date replyDate) {
		this.replyDate = replyDate;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
	
}
