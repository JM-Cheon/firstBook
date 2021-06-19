package com.lastbug.firstbook.contest.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class ContestSsnDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4485201657690558970L;
	private String competSsn;

	
	public ContestSsnDTO() {}


	public ContestSsnDTO(String competSsn) {
		super();
		this.competSsn = competSsn;
	}


	public String getCompetSsn() {
		return competSsn;
	}


	public void setCompetSsn(String competSsn) {
		this.competSsn = competSsn;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "ContestSsnDTO [competSsn=" + competSsn + "]";
	}


	
	
	
}
