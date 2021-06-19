package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;

public class CoinChargeDTO implements Serializable {
	
	private static final long serialVersionUID = -6969401045849976242L;
	private int coinChargeNum;
	private int memNum;
	private java.sql.Date coinChargeDate;
	private int coinChargeCount;
	private int coinChargeTotal;
	private String weeklyCoinYn;
	
	public CoinChargeDTO() {
		
	}

	public CoinChargeDTO(int coinChargeNum, int memNum, Date coinChargeDate, int coinChargeCount, int coinChargeTotal,
			String weeklyCoinYn) {
		super();
		this.coinChargeNum = coinChargeNum;
		this.memNum = memNum;
		this.coinChargeDate = coinChargeDate;
		this.coinChargeCount = coinChargeCount;
		this.coinChargeTotal = coinChargeTotal;
		this.weeklyCoinYn = weeklyCoinYn;
	}

	public int getCoinChargeNum() {
		return coinChargeNum;
	}

	public void setCoinChargeNum(int coinChargeNum) {
		this.coinChargeNum = coinChargeNum;
	}

	public int getMemNum() {
		return memNum;
	}

	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}

	public java.sql.Date getCoinChargeDate() {
		return coinChargeDate;
	}

	public void setCoinChargeDate(java.sql.Date coinChargeDate) {
		this.coinChargeDate = coinChargeDate;
	}

	public int getCoinChargeCount() {
		return coinChargeCount;
	}

	public void setCoinChargeCount(int coinChargeCount) {
		this.coinChargeCount = coinChargeCount;
	}

	public int getCoinChargeTotal() {
		return coinChargeTotal;
	}

	public void setCoinChargeTotal(int coinChargeTotal) {
		this.coinChargeTotal = coinChargeTotal;
	}

	public String getWeeklyCoinYn() {
		return weeklyCoinYn;
	}

	public void setWeeklyCoinYn(String weeklyCoinYn) {
		this.weeklyCoinYn = weeklyCoinYn;
	}

	@Override
	public String toString() {
		return "CoinChargeDTO [coinChargeNum=" + coinChargeNum + ", memNum=" + memNum + ", coinChargeDate="
				+ coinChargeDate + ", coinChargeCount=" + coinChargeCount + ", coinChargeTotal=" + coinChargeTotal
				+ ", weeklyCoinYn=" + weeklyCoinYn + "]";
	}

}
