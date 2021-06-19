package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.model.service.AdminNoticeService;
import com.lastbug.firstbook.admin.webnovel.model.service.AdminWebNovelService;
import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.member.model.dto.NoticeDTO;
import com.lastbug.firstbook.member.model.service.MemberService;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;


@WebServlet("/admin/noticeDeactive")
public class AdminNoticeDeactiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int noticeNum = Integer.valueOf(request.getParameter("noticeNum"));
		
		String noticeStatus = request.getParameter("noticeStatus");
		
		AdminNoticeService anService = new AdminNoticeService();
	
		
		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		MemberService memberService = new MemberService();
		int totalCount = memberService.selectTotalCount();
		
//		System.out.println("게시 가능한 게시물의 총 갯수 : " + totalCount);
		
		int limit = 14;
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		List<NoticeDTO> noticeList = memberService.selectNoticeList(pageInfo);
		

		if(noticeStatus.equals("Y")){
			
			noticeStatus = "N";
			
		} else if(noticeStatus.equals("N")) {
			
			noticeStatus = "Y";
			
		}
		
		
		
		int result = anService.NoticeActDeactive(noticeNum, noticeStatus);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/admin/adminNotice.jsp";
			request.setAttribute("noticeList", noticeList);
			request.setAttribute("pageInfo", pageInfo);
			
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("message", "공개 or 비공개처리 실패!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

}
