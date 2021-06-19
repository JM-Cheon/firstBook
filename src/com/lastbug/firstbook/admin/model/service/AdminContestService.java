package com.lastbug.firstbook.admin.model.service;

import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.close;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.commit;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.getConnection;
import static com.lastbug.firstbook.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.lastbug.firstbook.admin.model.dao.AdminContestDAO;
import com.lastbug.firstbook.contest.model.dto.ContestDTO;

public class AdminContestService {

	private final AdminContestDAO acDAO;
	
	public AdminContestService() {
		
		acDAO = new AdminContestDAO();
		
	}

	public int ContestModify(int nop, int pg0, String body0, int pg1, String body1) {
		
		Connection con = getConnection();
		
		int result = acDAO.ContestModify(con, nop, pg0, body0, pg1, body1);
		
		
		if(result > 0) {
		
			commit(con);
		} else {
		
			rollback(con);
		}
		
		close(con);
		
		return result;

	}

	public int ContestActiveOrDeactive(int competNum, String activeYn) {
		
		Connection con = getConnection();
		
		int result = acDAO.ContestActiveOrDeactive(con, competNum, activeYn);
		
		if(result > 0) {
		
			commit(con);
	
		} else {
		
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public List<ContestDTO> selectContestList(int competNum) {
					
		Connection con = getConnection();
		
		List<ContestDTO> contestList = acDAO.selectContestList(con, competNum);
		
		close(con);
		
		return contestList;
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
		
		
		List<ContestDTO> contestList = acDAO.selectAllContestList(con, month);
		
		close(con);
		
		return contestList;
	
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
	
	
}
