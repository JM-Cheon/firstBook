package com.lastbug.firstbook.webnovel.model.dto;

import java.io.Serializable;

public class GenreCategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5667239823372085485L;
	
	public GenreCategoryDTO () {
		
	}

	private String categoryCode;
	private String categoryName;
	public GenreCategoryDTO(String categoryCode, String categoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "GenreCategoryDTO [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
