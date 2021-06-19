package com.lastbug.firstbook.contest.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class CompetContentDTO implements Serializable{

	
	
	private static final long serialVersionUID = 1802850318150560454L;
	private int numberOfPages;
	private int pages;
	private String competContent;
	
	public CompetContentDTO() {}

	public CompetContentDTO(int numberOfPages, int pages, String competContent) {
		super();
		this.numberOfPages = numberOfPages;
		this.pages = pages;
		this.competContent = competContent;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}



	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}



	public int getPages() {
		return pages;
	}



	public void setPages(int pages) {
		this.pages = pages;
	}



	public String getCompetContent() {
		return competContent;
	}



	public void setCompetContent(String competContent) {
		this.competContent = competContent;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "CompetContentDTO [numberOfPages=" + numberOfPages + ", pages=" + pages + ", competContent="
				+ competContent + "]";
	}



	
	
	
}
