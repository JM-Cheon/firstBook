package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class WebNovChapSearchDTO implements Serializable {


	private static final long serialVersionUID = -5599879095724072423L;

	public WebNovChapSearchDTO() {}
	
	private WebNovelInfoDTO webNovNum;
	private WebNovChapNumDTO webNovChapNum;
	private java.sql.Date webChapNumDate;
	private String chapReadOrNot;
	private int chapPerPrice;
	private String chapPerIsUsed;
	private String chapPerTitle;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WebNovelInfoDTO getWebNovNum() {
		return webNovNum;
	}

	public WebNovChapNumDTO getWebNovChapNum() {
		return webNovChapNum;
	}

	public java.sql.Date getWebChapNumDate() {
		return webChapNumDate;
	}

	public String getChapReadOrNot() {
		return chapReadOrNot;
	}

	public int getChapPerPrice() {
		return chapPerPrice;
	}

	public String getChapPerIsUsed() {
		return chapPerIsUsed;
	}

	public String getChapPerTitle() {
		return chapPerTitle;
	}

	public void setWebNovNum(WebNovelInfoDTO webNovNum) {
		this.webNovNum = webNovNum;
	}

	public void setWebNovChapNum(WebNovChapNumDTO webNovChapNum) {
		this.webNovChapNum = webNovChapNum;
	}

	public void setWebChapNumDate(java.sql.Date webChapNumDate) {
		this.webChapNumDate = webChapNumDate;
	}

	public void setChapReadOrNot(String chapReadOrNot) {
		this.chapReadOrNot = chapReadOrNot;
	}

	public void setChapPerPrice(int chapPerPrice) {
		this.chapPerPrice = chapPerPrice;
	}

	public void setChapPerIsUsed(String chapPerIsUsed) {
		this.chapPerIsUsed = chapPerIsUsed;
	}

	public void setChapPerTitle(String chapPerTitle) {
		this.chapPerTitle = chapPerTitle;
	}

	@Override
	public String toString() {
		return "WebNovChapSearchDTO [webNovNum=" + webNovNum + ", webNovChapNum=" + webNovChapNum + ", webChapNumDate="
				+ webChapNumDate + ", chapReadOrNot=" + chapReadOrNot + ", chapPerPrice=" + chapPerPrice
				+ ", chapPerIsUsed=" + chapPerIsUsed + ", chapPerTitle=" + chapPerTitle + "]";
	}

	public WebNovChapSearchDTO(WebNovelInfoDTO webNovNum, WebNovChapNumDTO webNovChapNum, Date webChapNumDate,
			String chapReadOrNot, int chapPerPrice, String chapPerIsUsed, String chapPerTitle) {
		this.webNovNum = webNovNum;
		this.webNovChapNum = webNovChapNum;
		this.webChapNumDate = webChapNumDate;
		this.chapReadOrNot = chapReadOrNot;
		this.chapPerPrice = chapPerPrice;
		this.chapPerIsUsed = chapPerIsUsed;
		this.chapPerTitle = chapPerTitle;
	}
	
}
