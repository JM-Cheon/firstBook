package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.member.model.dto.FaqDTO;
import com.lastbug.firstbook.member.model.service.MemberService;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;

@WebServlet("/member/faq")
public class FAQSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		MemberService memberService = new MemberService();
		int totalCount = memberService.selectFAQTotalCount();
		
//		System.out.println("게시 가능한 게시물의 총 갯수 : " + totalCount);
		
		int limit = 14;
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		List<FaqDTO> faqList = memberService.selectFAQList(pageInfo);
		
//		for(FaqDTO n : faqList) {
//			System.out.println("한 페이지에 보여질 게시글들 : " + n);
//		}
		
		String path = "";
		if(!faqList.isEmpty()) {
			path = "/WEB-INF/views/member/faq.jsp";
			request.setAttribute("faqList", faqList);
			request.setAttribute("pageInfo", pageInfo);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "faqSelectList");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
