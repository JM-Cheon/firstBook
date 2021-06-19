package com.lastbug.firstbook.contest.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.lastbug.firstbook.contest.model.dao.ContestDAO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestSsnDTO;

public class ContestService {
	
	private final ContestDAO contestDAO;
	
	public ContestService() {
		contestDAO = new ContestDAO();
	}

	public List<ContestDTO> selectAllconstList() {
		

		Connection con = getConnection();
		String month = "";
		String trueMonth ="";
		
		
		switch (NowMonth()) {
		case "01"  : trueMonth += 1; break;
		case "02"  : trueMonth += 1; break;
		case "03"  : trueMonth += 1; break;
		case "04"  : trueMonth += 2; break;
		case "05"  : trueMonth += 2; break;
		case "06"  : trueMonth += 2; break;
		case "07"  : trueMonth += 3; break;
		case "08"  : trueMonth += 3; break;
		case "09"  : trueMonth += 3; break;
		case "10"  : trueMonth += 4; break;
		case "11"  : trueMonth += 4; break;
		case "12"  : trueMonth += 4; break;
		}
		
		month = NowYear() + "Q" + trueMonth;
		
		
		List<ContestDTO> contestList = contestDAO.selectAllContestList(con, month);
		
		close(con);
		
		return contestList;
		
	}
	
	// 8강 올라간 인원 투표
	public List<ContestDTO> rankEightconstList() {
		
		Connection con = getConnection();
		String month = "";
		String trueMonth ="";
		
		
		switch (NowMonth()) {
		case "01"  : trueMonth += 1; break;
		case "02"  : trueMonth += 1; break;
		case "03"  : trueMonth += 1; break;
		case "04"  : trueMonth += 2; break;
		case "05"  : trueMonth += 2; break;
		case "06"  : trueMonth += 2; break;
		case "07"  : trueMonth += 3; break;
		case "08"  : trueMonth += 3; break;
		case "09"  : trueMonth += 3; break;
		case "10"  : trueMonth += 4; break;
		case "11"  : trueMonth += 4; break;
		case "12"  : trueMonth += 4; break;
		}
		
		month = NowYear() + "Q" + trueMonth;
		
		
		List<ContestDTO> contestListRankEight = contestDAO.rankEightconstList(con, month);
		
		close(con);
		return contestListRankEight;
	}
	
	/* 전체 공모전 작품 보는 페이지 */
	public List<ContestDTO> selectAllconstListTwo(String selectTwo) {
		
		Connection con = getConnection();
		
		List<ContestDTO> contestList = contestDAO.selectAllconstListTwo(con, selectTwo);
		
		close(con);
		
		return contestList;
	}
	
	/* 전체 시즌 공모전 코드 조회 */
	public List<ContestSsnDTO> selectAllconstListTime() {
		Connection con = getConnection();
		
		List<ContestSsnDTO> contestList = contestDAO.selectAllconstListTime(con);
		
		close(con);
		return contestList;
	}
	
	
	
	// 8강 4강 결승 보이게해주는 서비스
	public int selectdate() {
		int date = 0;
		if(Integer.valueOf(NowDate()) > 415 && Integer.valueOf(NowDate()) < 630) {
			date += 1;
		}
		return date;
	}
	
	
	
	
	
	// 현재 년도 알아오는 메소드
	public static String NowYear() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy");
		String str = dayTime.format(new Date(time));
		return str; 
	}
	
	// 현재 달 알아오는 메소드 
	public static String NowMonth() {
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("MM");
		String str = dayTime.format(new Date(time));
		return str; 
	}
	
	// 8강 4강 결승 보이게해주는 메소드
		public static String NowDate() {
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("MMdd");
			String str = dayTime.format(new Date(time));
			return str; 
		}

		public int selectdate8() {
			int date = 0;
			if(Integer.valueOf(NowDate()) > 415 && Integer.valueOf(NowDate()) < 630) {
				date += 2;
			}
			return date;
		}
		
		public int selectdate4() {
			int date = 0;
			if(Integer.valueOf(NowDate()) > 415 && Integer.valueOf(NowDate()) < 630) {
				date += 3;
			}
			return date;
		}

		public List<ContestDTO> mainTopContest() {
			Connection con = getConnection();
			String month = "";
			String trueMonth ="";
			int mainTop = Integer.valueOf(NowYear());
			
			switch (NowMonth()) {
			case "01"  : trueMonth += 4; mainTop-=1; break;
			case "02"  : trueMonth += 4; mainTop-=1; break;
			case "03"  : trueMonth += 4; mainTop-=1; break;
			case "04"  : trueMonth += 1; break;
			case "05"  : trueMonth += 1; break;
			case "06"  : trueMonth += 1; break;
			case "07"  : trueMonth += 2; break;
			case "08"  : trueMonth += 2; break;
			case "09"  : trueMonth += 2; break;
			case "10"  : trueMonth += 3; break;
			case "11"  : trueMonth += 3; break;
			case "12"  : trueMonth += 3; break;
			}
			
			month = mainTop + "Q" + trueMonth;
			
			
			List<ContestDTO> contestList = contestDAO.mainTopContest(con, month);
			
			close(con);
			
			return contestList;
		}

		public int selectdate2(List<ContestDTO> contestList) {
			// TODO Auto-generated method stub
			return 0;
		}

	

	

		

		
	

	

	


	
}
