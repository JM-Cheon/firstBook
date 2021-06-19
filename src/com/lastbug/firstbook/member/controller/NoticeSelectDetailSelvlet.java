package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/noticeDetail")
public class NoticeSelectDetailSelvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNum = Integer.valueOf(request.getParameter("no"));
		
		MemberService memberService = new MemberService();
		NoticeDTO noticeDetail = memberService.selectNoticeDetail(noticeNum);
		
		String path = "";
		if(noticeDetail != null) {
			path = "/WEB-INF/views/member/notice_detail.jsp";
			request.setAttribute("notice", noticeDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectNoticeDetail");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
