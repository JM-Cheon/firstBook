package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.common.mail.SendMail;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

@WebServlet("/member/searchPwd")
public class SearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/searchPwd.jsp";
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memName = request.getParameter("memName");
		String memId = request.getParameter("memId");
		String memEmail = request.getParameter("memEmail");
		String memEmail2 = request.getParameter("memEmail2");
		
		MemberDTO member = new MemberDTO();
		member.setMemName(memName);
		member.setMemId(memId);
		member.setMemEmail(memEmail + "@" + memEmail2);
		
		MemberService memberService = new MemberService();
		MemberDTO searchMember = memberService.searchPwd(member);
		
		System.out.println(searchMember);
		
		String path = "";
		String emailKey = "";
		if(searchMember.getMemId() != null) {
			System.out.println("searchMember : "+searchMember);
			if(searchMember.getMemEmail().contains("naver.com")) {
				emailKey = SendMail.naverMailSend(searchMember.getMemEmail());
				path = "/WEB-INF/views/member/searchPwd_res.jsp";
				request.setAttribute("emailKey", emailKey);
			} else {
				emailKey = SendMail.gmailSend(searchMember.getMemEmail());
				path = "/WEB-INF/views/member/searchPwd_res.jsp";
				request.setAttribute("emailKey", emailKey);
			}
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "searchPwd");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
