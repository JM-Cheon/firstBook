package com.lastbug.firstbook.contest.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ContestDTO implements Serializable{

	private static final long serialVersionUID = -2149929819578413569L;
	
	private int competNum;
	private int memNum;
	private String competPaperYn;
	private String novTitle;
	private String novInfo;
	private String competSsn;
	private String competNovImgLocation;
	private int Score;
	private String competActYn;
	private int ageLimit;

	
	public ContestDTO() {}


	public ContestDTO(int competNum, int memNum, String competPaperYn, String novTitle, String novInfo,
			String competSsn, String competNovImgLocation, int score, String competActYn, int ageLimit) {
		super();
		this.competNum = competNum;
		this.memNum = memNum;
		this.competPaperYn = competPaperYn;
		this.novTitle = novTitle;
		this.novInfo = novInfo;
		this.competSsn = competSsn;
		this.competNovImgLocation = competNovImgLocation;
		Score = score;
		this.competActYn = competActYn;
		this.ageLimit = ageLimit;
	}


	public int getCompetNum() {
		return competNum;
	}


	public void setCompetNum(int competNum) {
		this.competNum = competNum;
	}


	public int getMemNum() {
		return memNum;
	}


	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}


	public String getCompetPaperYn() {
		return competPaperYn;
	}


	public void setCompetPaperYn(String competPaperYn) {
		this.competPaperYn = competPaperYn;
	}


	public String getNovTitle() {
		return novTitle;
	}


	public void setNovTitle(String novTitle) {
		this.novTitle = novTitle;
	}


	public String getNovInfo() {
		return novInfo;
	}


	public void setNovInfo(String novInfo) {
		this.novInfo = novInfo;
	}


	public String getCompetSsn() {
		return competSsn;
	}


	public void setCompetSsn(String competSsn) {
		this.competSsn = competSsn;
	}


	public String getCompetNovImgLocation() {
		return competNovImgLocation;
	}


	public void setCompetNovImgLocation(String competNovImgLocation) {
		this.competNovImgLocation = competNovImgLocation;
	}


	public int getScore() {
		return Score;
	}


	public void setScore(int score) {
		Score = score;
	}


	public String getCompetActYn() {
		return competActYn;
	}


	public void setCompetActYn(String competActYn) {
		this.competActYn = competActYn;
	}


	public int getAgeLimit() {
		return ageLimit;
	}


	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "ContestDTO [competNum=" + competNum + ", memNum=" + memNum + ", competPaperYn=" + competPaperYn
				+ ", novTitle=" + novTitle + ", novInfo=" + novInfo + ", competSsn=" + competSsn
				+ ", competNovImgLocation=" + competNovImgLocation + ", Score=" + Score + ", competActYn=" + competActYn
				+ ", ageLimit=" + ageLimit + "]";
	}

	

	
	
	
}
