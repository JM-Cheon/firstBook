package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;

public class WebNovPageNumDTO implements Serializable{


	private static final long serialVersionUID = -5399810160986423454L;
	private int webNovPageNum;
	private String webNovPageName;
	public WebNovPageNumDTO() {
		super();
	}
	public WebNovPageNumDTO(int webNovPageNum, String webNovPageName) {
		super();
		this.webNovPageNum = webNovPageNum;
		this.webNovPageName = webNovPageName;
	}
	@Override
	public String toString() {
		return "WebNovPageNumDTO [webNovPageNum=" + webNovPageNum + ", webNovPageName=" + webNovPageName + "]";
	}
	public int getWebNovPageNum() {
		return webNovPageNum;
	}
	public void setWebNovPageNum(int webNovPageNum) {
		this.webNovPageNum = webNovPageNum;
	}
	public String getWebNovPageName() {
		return webNovPageName;
	}
	public void setWebNovPageName(String webNovPageName) {
		this.webNovPageName = webNovPageName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
