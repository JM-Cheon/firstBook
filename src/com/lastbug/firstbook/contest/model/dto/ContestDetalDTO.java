package com.lastbug.firstbook.contest.model.dto;

import java.io.Serializable;

public class ContestDetalDTO implements Serializable{

	private static final long serialVersionUID = 1944936767675805353L;
	
	private int chapterNum;
	private int competNum;
	private String novContent;
	private String competDate;
	private int numberOfPages;
	
	public ContestDetalDTO() {}

	public ContestDetalDTO(int chapterNum, int competNum, String novContent, String competDate, int numberOfPages) {
		super();
		this.chapterNum = chapterNum;
		this.competNum = competNum;
		this.novContent = novContent;
		this.competDate = competDate;
		this.numberOfPages = numberOfPages;
	}

	public int getChapterNum() {
		return chapterNum;
	}

	public void setChapterNum(int chapterNum) {
		this.chapterNum = chapterNum;
	}

	public int getCompetNum() {
		return competNum;
	}

	public void setCompetNum(int competNum) {
		this.competNum = competNum;
	}

	public String getNovContent() {
		return novContent;
	}

	public void setNovContent(String novContent) {
		this.novContent = novContent;
	}

	public String getCompetDate() {
		return competDate;
	}

	public void setCompetDate(String competDate) {
		this.competDate = competDate;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ContestDetalDTO [chapterNum=" + chapterNum + ", competNum=" + competNum + ", novContent=" + novContent
				+ ", competDate=" + competDate + ", numberOfPages=" + numberOfPages + "]";
	}

	
	

	
	

}
