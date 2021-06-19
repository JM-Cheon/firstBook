package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;


@WebServlet("/admin/search")
public class SearchMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String condition = request.getParameter("memSearchCondition");

		String value = request.getParameter("memSearchValue");
		
//		System.out.println("condition : " + condition);
//		System.out.println("value : " + value);
		
//		String currentPage = request.getParameter("currentPage");
		
		
		
		AdminService adminService = new AdminService();
		
		List<MemberDTO> memList = adminService.searchMemList(condition, value);
		
//		for(MemberDTO md : memList) {
//			
//			System.out.println("검색한 멤버" + md);
//			
//		}
		
		String path="";
		
		if(!memList.isEmpty()) {
			
			path = "/WEB-INF/views/admin/memberList.jsp";
			request.setAttribute("memList", memList);
			request.setAttribute("memSearchCondition", condition);
			request.setAttribute("memSearchValue" , value);
			
		} else {
			
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "회원 검색 조회 실패");
			
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	

}
