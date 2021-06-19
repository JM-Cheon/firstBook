package com.lastbug.firstbook.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lastbug.firstbook.member.model.dto.MemberDTO;
import com.lastbug.firstbook.member.model.dto.QnaReplyDTO;
import com.lastbug.firstbook.member.model.service.MemberService;

/**
 * Servlet implementation class QnaReplySelectListServlet
 */
@WebServlet("/member/qnaReply")
public class QnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postNum = Integer.valueOf(request.getParameter("no"));
		
		MemberService memberService = new MemberService();
		
		List<QnaReplyDTO> replyList = memberService.selectQnaReplyList(postNum);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	    String jsonString= gson.toJson(replyList);

		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(jsonString);
		
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int postNum = Integer.valueOf(request.getParameter("no"));
		String replyContent = request.getParameter("replyContent");
		MemberDTO member = new MemberDTO();
		member = ((MemberDTO)request.getSession().getAttribute("loginMember"));
		
		QnaReplyDTO insertReply = new QnaReplyDTO();
		insertReply.setMember(member);
		insertReply.setPostNum(postNum);
		insertReply.setReplyContent(replyContent);
		
		MemberService memberService = new MemberService();
		int result = memberService.insertQnaReply(insertReply);
		
		String jsonString = "";
		if(result > 0) {
			List<QnaReplyDTO> replyList = memberService.selectQnaReplyList(postNum);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		    jsonString = gson.toJson(replyList);
		} else {
			jsonString = "{\"replyContent\":\"인서트 실패\"}";
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(jsonString);
		
		out.flush();
		out.close();
	}

}
