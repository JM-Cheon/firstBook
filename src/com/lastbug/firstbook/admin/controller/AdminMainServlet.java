package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminService;


@WebServlet("/admin/main")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminService adminService = new AdminService();
		
		
		/* 회원관리 통계 표시용 */
		int allMemberCount = adminService.allMemberCount();
		
		int member10s = adminService.member10sCount();
		int member20s = adminService.member20sCount();
		int member30s = adminService.member30sCount();
		int member40s = adminService.member40sCount();
		int member50s = adminService.member50sCount();
		
		/* 웹소설관리 통계 표시용 */
		
		int allWebNovelCount = adminService.allWnCount();
		
		int fantasyCount = adminService.fantasyCount();
		int modernFatasyCount = adminService.modernFatasyCount();
		int sportsCount = adminService.sportsCount();
		int fantasyHistoryCount = adminService.fantasyHistoryCount();
		int romanceCount = adminService.romanceCount();
		
		
		
		
		if(allMemberCount!=0) {
			
			request.setAttribute("allMemberCount", allMemberCount);
			request.setAttribute("member10s", member10s);
			request.setAttribute("member20s", member20s);
			request.setAttribute("member30s", member30s);
			request.setAttribute("member40s", member40s);
			request.setAttribute("member50s", member50s);
			
			request.setAttribute("allWebNovelCount", allWebNovelCount);
			request.setAttribute("fantasyCount", fantasyCount);
			request.setAttribute("modernFatasyCount", modernFatasyCount);
			request.setAttribute("sportsCount", sportsCount);
			request.setAttribute("fantasyHistoryCount", fantasyHistoryCount);
			request.setAttribute("romanceCount", romanceCount);
			
			request.getRequestDispatcher("/WEB-INF/views/admin/adminMain.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("message", "failed");
			
			
		}
		
	}

}
