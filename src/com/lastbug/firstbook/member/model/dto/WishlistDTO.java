package com.lastbug.firstbook.member.model.dto;

import java.sql.Date;

import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class WishlistDTO {
	
	private WebNovelInfoDTO webNov;
	private int memNum;
	private java.sql.Date wishlistDate;
	
	public WishlistDTO() {
		
	}

	public WishlistDTO(WebNovelInfoDTO webNov, int memNum, Date wishlistDate) {
		super();
		this.webNov = webNov;
		this.memNum = memNum;
		this.wishlistDate = wishlistDate;
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

	public java.sql.Date getWishlistDate() {
		return wishlistDate;
	}

	public void setWishlistDate(java.sql.Date wishlistDate) {
		this.wishlistDate = wishlistDate;
	}

	@Override
	public String toString() {
		return "WishlistDTO [webNov=" + webNov + ", memNum=" + memNum + ", wishlistDate=" + wishlistDate + "]";
	}

}
