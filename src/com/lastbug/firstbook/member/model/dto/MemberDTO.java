package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class MemberDTO implements Serializable{
	
	private static final long serialVersionUID = -7685458380084706512L;

	private int memNum;
	private String memName;
	private String memId;
	private String memPwd;
	private String memEmail;
	private String memAddress;
	private java.sql.Date memBirthDate;
	private int memLoginCount;
	private int memCoin;
	private String memCanVoteYn;
	private String memWithdrawYn;
	private java.sql.Date memWithdrawDate;
	private String memClass;
	private String memBlockYn;
	private java.sql.Date memBlockDate;
	private java.sql.Date memEnrollDate;
	private String memWeeklyCoinYn;
	
	public MemberDTO() {
	}

	public MemberDTO(int memNum, String memName, String memId, String memPwd, String memEmail, String memAddress,
			Date memBirthDate, int memLoginCount, int memCoin, String memCanVoteYn, String memWithdrawYn,
			Date memWithdrawDate, String memClass, String memBlockYn, Date memBlockDate, Date memEnrollDate,
			String memWeeklyCoinYn) {
		super();
		this.memNum = memNum;
		this.memName = memName;
		this.memId = memId;
		this.memPwd = memPwd;
		this.memEmail = memEmail;
		this.memAddress = memAddress;
		this.memBirthDate = memBirthDate;
		this.memLoginCount = memLoginCount;
		this.memCoin = memCoin;
		this.memCanVoteYn = memCanVoteYn;
		this.memWithdrawYn = memWithdrawYn;
		this.memWithdrawDate = memWithdrawDate;
		this.memClass = memClass;
		this.memBlockYn = memBlockYn;
		this.memBlockDate = memBlockDate;
		this.memEnrollDate = memEnrollDate;
		this.memWeeklyCoinYn = memWeeklyCoinYn;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public java.sql.Date getMemBirthDate() {
		return memBirthDate;
	}

	public void setMemBirthDate(java.sql.Date memBirthDate) {
		this.memBirthDate = memBirthDate;
	}

	public int getMemLoginCount() {
		return memLoginCount;
	}

	public void setMemLoginCount(int memLoginCount) {
		this.memLoginCount = memLoginCount;
	}

	public int getMemCoin() {
		return memCoin;
	}

	public void setMemCoin(int memCoin) {
		this.memCoin = memCoin;
	}

	public String getMemCanVoteYn() {
		return memCanVoteYn;
	}

	public void setMemCanVoteYn(String memCanVoteYn) {
		this.memCanVoteYn = memCanVoteYn;
	}

	public String getMemWithdrawYn() {
		return memWithdrawYn;
	}

	public void setMemWithdrawYn(String memWithdrawYn) {
		this.memWithdrawYn = memWithdrawYn;
	}

	public java.sql.Date getMemWithdrawDate() {
		return memWithdrawDate;
	}

	public void setMemWithdrawDate(java.sql.Date memWithdrawDate) {
		this.memWithdrawDate = memWithdrawDate;
	}

	public String getMemClass() {
		return memClass;
	}

	public void setMemClass(String memClass) {
		this.memClass = memClass;
	}

	public String getMemBlockYn() {
		return memBlockYn;
	}

	public void setMemBlockYn(String memBlockYn) {
		this.memBlockYn = memBlockYn;
	}

	public java.sql.Date getMemBlockDate() {
		return memBlockDate;
	}

	public void setMemBlockDate(java.sql.Date memBlockDate) {
		this.memBlockDate = memBlockDate;
	}

	public java.sql.Date getMemEnrollDate() {
		return memEnrollDate;
	}

	public void setMemEnrollDate(java.sql.Date memEnrollDate) {
		this.memEnrollDate = memEnrollDate;
	}

	public String getMemWeeklyCoinYn() {
		return memWeeklyCoinYn;
	}

	public void setMemWeeklyCoinYn(String memWeeklyCoinYn) {
		this.memWeeklyCoinYn = memWeeklyCoinYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MemberDTO [memNum=" + memNum + ", memName=" + memName + ", memId=" + memId + ", memPwd=" + memPwd
				+ ", memEmail=" + memEmail + ", memAddress=" + memAddress + ", memBirthDate=" + memBirthDate
				+ ", memLoginCount=" + memLoginCount + ", memCoin=" + memCoin + ", memCanVoteYn=" + memCanVoteYn
				+ ", memWithdrawYn=" + memWithdrawYn + ", memWithdrawDate=" + memWithdrawDate + ", memClass=" + memClass
				+ ", memBlockYn=" + memBlockYn + ", memBlockDate=" + memBlockDate + ", memEnrollDate=" + memEnrollDate
				+ ", memWeeklyCoinYn=" + memWeeklyCoinYn + "]";
	}

	
	
}
