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

@WebServlet("/member/insertQna")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/insertQna.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String qnaTitle = request.getParameter("qnaTitle");
		String qnaContent = request.getParameter("qnaContent");
		
		QnaDTO qna = new QnaDTO();
		qna.setMemNum(((MemberDTO)request.getSession().getAttribute("loginMember")));
		qna.setQnaTitle(qnaTitle);
		qna.setQnaContent(qnaContent);
		
		MemberService memberService = new MemberService();
		int result = memberService.insertQNA(qna);
		
		String path = "";
		if(result > 0) {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "insertQNA");
		} else {
			path = "/WEB_INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "insertQNA");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
