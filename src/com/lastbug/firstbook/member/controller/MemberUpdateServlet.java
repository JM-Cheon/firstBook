package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String memName = request.getParameter("memName");
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		java.sql.Date memBirthDate = java.sql.Date.valueOf(request.getParameter("memBirthDate"));
		String memEmail = request.getParameter("memEmail");
		String memEmail2 = request.getParameter("memEmail2");
		String memZipCode = request.getParameter("memZipCode");
		String memAddress = request.getParameter("memAddress");
		String memDetailAddress = request.getParameter("memDetailAddress");
		
		MemberDTO requestMember = new MemberDTO();
		requestMember.setMemNum(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemNum());
		requestMember.setMemName(memName);
		requestMember.setMemId(memId);
		requestMember.setMemPwd(memPwd);
		requestMember.setMemBirthDate(memBirthDate);
		requestMember.setMemEmail(memEmail + "@" + memEmail2);
		requestMember.setMemAddress(memZipCode + "$" + memAddress + "$" + memDetailAddress);
		requestMember.setMemLoginCount(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemLoginCount());
		requestMember.setMemCoin(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemCoin());
		requestMember.setMemCanVoteYn(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemCanVoteYn());
		requestMember.setMemWithdrawYn(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemWithdrawYn());
		requestMember.setMemWithdrawDate(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemWithdrawDate());
		requestMember.setMemClass(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemClass());
		requestMember.setMemBlockYn(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemBlockYn());
		requestMember.setMemBlockDate(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemBlockDate());
		requestMember.setMemEnrollDate(((MemberDTO)request.getSession().getAttribute("loginMember")).getMemEnrollDate());
		
		MemberService memberService = new MemberService();
		int result = memberService.updateMember(requestMember);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "updateMember");
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", requestMember);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "updateMember");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
