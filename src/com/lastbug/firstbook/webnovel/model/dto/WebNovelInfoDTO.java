package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;

public class WebNovelInfoDTO implements Serializable{

	private static final long serialVersionUID = 4694686975276953035L;
	public WebNovelInfoDTO() {
	}
	
	private int webNovNum;
	private String categoryCode;
	private String webNovTitle;
	private String webNovAuthor;
	private int chapPerCoin;
	private String webNovPublisher;
	private String webNovInform;
	private String webNovImgLocation;
	private String dayOfWeek;
	private String webNovOpenOrClose;
	private String finishedOrNot;
	private GenreCategoryDTO categoryName;
	public WebNovelInfoDTO(int webNovNum, String categoryCode, String webNovTitle, String webNovAuthor, int chapPerCoin,
			String webNovPublisher, String webNovInform, String webNovImgLocation, String dayOfWeek,
			String webNovOpenOrClose, String finishedOrNot, GenreCategoryDTO categoryName) {
		super();
		this.webNovNum = webNovNum;
		this.categoryCode = categoryCode;
		this.webNovTitle = webNovTitle;
		this.webNovAuthor = webNovAuthor;
		this.chapPerCoin = chapPerCoin;
		this.webNovPublisher = webNovPublisher;
		this.webNovInform = webNovInform;
		this.webNovImgLocation = webNovImgLocation;
		this.dayOfWeek = dayOfWeek;
		this.webNovOpenOrClose = webNovOpenOrClose;
		this.finishedOrNot = finishedOrNot;
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "WebNovelInfoDTO [webNovNum=" + webNovNum + ", categoryCode=" + categoryCode + ", webNovTitle="
				+ webNovTitle + ", webNovAuthor=" + webNovAuthor + ", chapPerCoin=" + chapPerCoin + ", webNovPublisher="
				+ webNovPublisher + ", webNovInform=" + webNovInform + ", webNovImgLocation=" + webNovImgLocation
				+ ", dayOfWeek=" + dayOfWeek + ", webNovOpenOrClose=" + webNovOpenOrClose + ", finishedOrNot="
				+ finishedOrNot + ", categoryName=" + categoryName + "]";
	}
	public int getWebNovNum() {
		return webNovNum;
	}
	public void setWebNovNum(int webNovNum) {
		this.webNovNum = webNovNum;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getWebNovTitle() {
		return webNovTitle;
	}
	public void setWebNovTitle(String webNovTitle) {
		this.webNovTitle = webNovTitle;
	}
	public String getWebNovAuthor() {
		return webNovAuthor;
	}
	public void setWebNovAuthor(String webNovAuthor) {
		this.webNovAuthor = webNovAuthor;
	}
	public int getChapPerCoin() {
		return chapPerCoin;
	}
	public void setChapPerCoin(int chapPerCoin) {
		this.chapPerCoin = chapPerCoin;
	}
	public String getWebNovPublisher() {
		return webNovPublisher;
	}
	public void setWebNovPublisher(String webNovPublisher) {
		this.webNovPublisher = webNovPublisher;
	}
	public String getWebNovInform() {
		return webNovInform;
	}
	public void setWebNovInform(String webNovInform) {
		this.webNovInform = webNovInform;
	}
	public String getWebNovImgLocation() {
		return webNovImgLocation;
	}
	public void setWebNovImgLocation(String webNovImgLocation) {
		this.webNovImgLocation = webNovImgLocation;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getWebNovOpenOrClose() {
		return webNovOpenOrClose;
	}
	public void setWebNovOpenOrClose(String webNovOpenOrClose) {
		this.webNovOpenOrClose = webNovOpenOrClose;
	}
	public String getFinishedOrNot() {
		return finishedOrNot;
	}
	public void setFinishedOrNot(String finishedOrNot) {
		this.finishedOrNot = finishedOrNot;
	}
	public GenreCategoryDTO getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(GenreCategoryDTO categoryName) {
		this.categoryName = categoryName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
