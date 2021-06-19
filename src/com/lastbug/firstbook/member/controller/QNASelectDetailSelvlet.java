package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.QnaDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/qnaDetail")
public class QNASelectDetailSelvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int memNum = ((MemberDTO)request.getSession().getAttribute("loginMember")).getMemNum();
		int qnaNum = Integer.valueOf(request.getParameter("no"));
		
		MemberService memberService = new MemberService();
		QnaDTO qnaDetail = memberService.selectQnaDetail(qnaNum,memNum);
		
		String path = "";
		if(qnaDetail != null) {
			path = "/WEB-INF/views/member/qna_detail.jsp";
			request.setAttribute("qna", qnaDetail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "selectFaqDetail");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
