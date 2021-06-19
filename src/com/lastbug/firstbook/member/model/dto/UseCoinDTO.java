package com.lastbug.firstbook.member.model.dto;

import java.sql.Date;

import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class UseCoinDTO {
	
	private int coinUseNum;
	private WebNovelInfoDTO webNov;
	private int memNum;
	private java.sql.Date coinUseDate;
	private int coinUsage;
	private int chapterNum;
	
	public UseCoinDTO() {
		
	}

	public UseCoinDTO(int coinUseNum, WebNovelInfoDTO webNov, int memNum, Date coinUseDate, int coinUsage,
			int chapterNum) {
		this.coinUseNum = coinUseNum;
		this.webNov = webNov;
		this.memNum = memNum;
		this.coinUseDate = coinUseDate;
		this.coinUsage = coinUsage;
		this.chapterNum = chapterNum;
	}

	public int getCoinUseNum() {
		return coinUseNum;
	}

	public void setCoinUseNum(int coinUseNum) {
		this.coinUseNum = coinUseNum;
	}

	public WebNovelInfoDTO getWebNov() {
		return webNov;
	}

	public void setWebNov(WebNovelInfoDTO webNov) {
		this.webNov = webNov;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public java.sql.Date getCoinUseDate() {
		return coinUseDate;
	}

	public void setCoinUseDate(java.sql.Date coinUseDate) {
		this.coinUseDate = coinUseDate;
	}

	public int getCoinUsage() {
		return coinUsage;
	}

	public void setCoinUsage(int coinUsage) {
		this.coinUsage = coinUsage;
	}

	public int getChapterNum() {
		return chapterNum;
	}

	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}

	@Override
	public String toString() {
		return "UseCoinDTO [coinUseNum=" + coinUseNum + ", webNov=" + webNov + ", memNum=" + memNum + ", coinUseDate="
				+ coinUseDate + ", coinUsage=" + coinUsage + ", chapterNum=" + chapterNum + "]";
	}
	
}
