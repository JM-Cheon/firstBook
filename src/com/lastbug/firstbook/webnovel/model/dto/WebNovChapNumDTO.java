package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;

public class WebNovChapNumDTO implements Serializable{

	
	private static final long serialVersionUID = 6957800855553695509L;
	private int webNovChapNum;
	private String webNovchapName;
	public WebNovChapNumDTO() {
//		super();
	}
	public WebNovChapNumDTO(int webNovChapNum, String webNovchapName) {
		super();
		this.webNovChapNum = webNovChapNum;
		this.webNovchapName = webNovchapName;
	}
	@Override
	public String toString() {
		return "WebNovChapNumDTO [webNovChapNum=" + webNovChapNum + ", webNovchapName=" + webNovchapName + "]";
	}
	public int getWebNovChapNum() {
		return webNovChapNum;
	}
	public void setWebNovChapNum(int webNovChapNum) {
		this.webNovChapNum = webNovChapNum;
	}
	public String getWebNovchapName() {
		return webNovchapName;
	}
	public void setWebNovchapName(String webNovchapName) {
		this.webNovchapName = webNovchapName;
	}
	
	
}
