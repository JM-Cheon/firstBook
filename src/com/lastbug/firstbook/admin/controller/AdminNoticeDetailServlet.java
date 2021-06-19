package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.member.model.service.MemberService;


@WebServlet("/admin/noticeDetail")
public class AdminNoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int noticeNum = Integer.valueOf(request.getParameter("no"));
		
		MemberService memberService = new MemberService();
		NoticeDTO noticeDetail = memberService.selectNoticeDetail(noticeNum);
		
		String path = "";
		if(noticeDetail != null) {
			path = "/WEB-INF/views/admin/adminNoticeDetail.jsp";
			request.setAttribute("notice", noticeDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectNoticeDetail");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
