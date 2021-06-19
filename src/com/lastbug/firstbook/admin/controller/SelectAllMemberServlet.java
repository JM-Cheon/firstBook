package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminService;
import com.lastbug.firstbook.member.model.dto.MemberDTO;


@WebServlet("/admin/memlist")
public class SelectAllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminService adminService = new AdminService();
		
		List<MemberDTO> memList = adminService.selectAllMemList();
		
//		for(MemberDTO md : memList) {
//			
//			System.out.println(md);
//			
//		}
		
		String path="";
		
		if(!memList.isEmpty()) {
			
			path = "/WEB-INF/views/admin/memberList.jsp";
			request.setAttribute("memList", memList);
			
			
		} else {
			
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("message", "회원 명단 조회 실패");
			
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

}
