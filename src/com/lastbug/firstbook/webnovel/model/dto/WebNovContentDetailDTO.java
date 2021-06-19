package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;

public class WebNovContentDetailDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private WebNovelInfoDTO webNovNum;
	private WebNovChapNumDTO webNovChapNum;
	private WebNovPageNumDTO webNovPageNum;
	private String webNovPageContent;
	
	public WebNovelInfoDTO getWebNovNum() {
		return webNovNum;
	}
	public void setWebNovNum(WebNovelInfoDTO webNovNum) {
		this.webNovNum = webNovNum;
	}
	public WebNovChapNumDTO getWebNovChapNum() {
		return webNovChapNum;
	}
	public void setWebNovChapNum(WebNovChapNumDTO webNovChapNum) {
		this.webNovChapNum = webNovChapNum;
	}
	public WebNovPageNumDTO getWebNovPageNum() {
		return webNovPageNum;
	}
	public void setWebNovPageNum(WebNovPageNumDTO webNovPageNum) {
		this.webNovPageNum = webNovPageNum;
	}
	public String getWebNovPageContent() {
		return webNovPageContent;
	}
	public void setWebNovPageContent(String webNovPageContent) {
		this.webNovPageContent = webNovPageContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WebNovContentDetailDTO [webNovNum=" + webNovNum + ", webNovChapNum=" + webNovChapNum
				+ ", webNovPageNum=" + webNovPageNum + ", webNovPageContent=" + webNovPageContent + "]";
	}
	public WebNovContentDetailDTO() {
//		super();
	}
	public WebNovContentDetailDTO(WebNovelInfoDTO webNovNum, WebNovChapNumDTO webNovChapNum,
			WebNovPageNumDTO webNovPageNum, String webNovPageContent) {
		super();
		this.webNovNum = webNovNum;
		this.webNovChapNum = webNovChapNum;
		this.webNovPageNum = webNovPageNum;
		this.webNovPageContent = webNovPageContent;
	}


	
	
}
