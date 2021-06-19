package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.paging.Pagenation;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.QnaDTO;
import com.lastbug.firstbook.member.model.service.MemberService;
import com.lastbug.firstbook.webnovel.model.dto.PageInfoDTO;

@WebServlet("/member/qna")
public class QNASelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memNum = ((MemberDTO)request.getSession().getAttribute("loginMember")).getMemNum();
		
		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.valueOf(currentPage);
			
			if(pageNo <= 0) {
				pageNo = 1;
			}
		}
		
		MemberService memberService = new MemberService();
		int totalCount = memberService.selectQNATotalCount(memNum);
		
//		System.out.println("게시 가능한 게시물의 총 갯수 : " + totalCount);
		
		int limit = 14;
		int buttonAmount = 5;
		
		PageInfoDTO pageInfo = Pagenation.getPageInfo(pageNo, totalCount, limit, buttonAmount);
		
		List<QnaDTO> qnaList = memberService.selectQnaList(pageInfo, memNum);
		
//		for(NoticeDTO n : noticeList) {
//			System.out.println("한 페이지에 보여질 게시글들 : " + n);
//		}
		
		String path = "";
		path = "/WEB-INF/views/member/qna.jsp";
		request.setAttribute("qnaList", qnaList);
		request.setAttribute("pageInfo", pageInfo);
		
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
