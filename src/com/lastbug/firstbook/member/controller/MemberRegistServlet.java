package com.lastbug.firstbook.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/regist")
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/registForm.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

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
		
//		System.out.println(name + memberId + memberPw + memberPwYn + birth + email);
		
		MemberDTO newMember = new MemberDTO();
		newMember.setMemName(memName);
		newMember.setMemId(memId);
		newMember.setMemPwd(memPwd);
		newMember.setMemBirthDate(memBirthDate);
		newMember.setMemEmail(memEmail + "@" + memEmail2);
		newMember.setMemAddress(memZipCode + "$" + memAddress + "$" + memDetailAddress);
		
//		System.out.println(newMember.toString());
		
		MemberService memberService = new MemberService();
		int result = memberService.insertMember(newMember);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertMember");
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertMember");			
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
