package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.FaqDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/faqDetail")
public class FAQSelectDetailSelvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int faqNum = Integer.valueOf(request.getParameter("no"));
		
		MemberService memberService = new MemberService();
		FaqDTO faqDetail = memberService.selectFaqDetail(faqNum);
		
		String path = "";
		if(faqDetail != null) {
			path = "/WEB-INF/views/member/faq_detail.jsp";
			request.setAttribute("faq", faqDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectFaqDetail");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
