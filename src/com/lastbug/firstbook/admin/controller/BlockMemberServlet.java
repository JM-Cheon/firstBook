package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;


@WebServlet("/admin/block")
public class BlockMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int selectNum = Integer.valueOf(request.getParameter("selectNum"));
		
		String blockCheck = request.getParameter("blockCheck");
				
		

		
		/* 현재시간으로 블락 시간을 잡아준다. */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
		String time = format.format(System.currentTimeMillis());
		
		java.sql.Date memBlockDate = Date.valueOf(time);
		
		/* 차단 해제의 경우 차단이 아니므로 차단 시간을 null값으로 바꾼다. */
		if(blockCheck.equals("N")) {
			
			memBlockDate = null;
			
		}
		
		
		MemberDTO mem = new MemberDTO();
		
		mem.setMemNum(selectNum);
		mem.setMemBlockYn(blockCheck);
		mem.setMemBlockDate(memBlockDate);
		
		
		AdminService adminService = new AdminService();
		
		int result = adminService.selectMemberBlockUnblock(mem);
		
		List<MemberDTO> memList = adminService.selectAllMemList();
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/admin/memberList.jsp";
			request.setAttribute("successCode", "blockUnblockMem");
			request.setAttribute("memList", memList);
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "차단 or 해제 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
		
	}
	
	
}
