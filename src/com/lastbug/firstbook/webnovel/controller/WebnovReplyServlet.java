package com.lastbug.firstbook.webnovel.controller;

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
import com.lastbug.firstbook.webnovel.model.dto.WebnovelReplyDTO;
import com.lastbug.firstbook.webnovel.model.service.WebNovelService;

/**
 * Servlet implementation class WebnovReplyServlet
 */
@WebServlet("/webnovel/reply/send")
public class WebnovReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String replytext = request.getParameter("replytext");
		int webNovNum = Integer.parseInt(request.getParameter("webNovNum"));
		int memNum = Integer.parseInt(request.getParameter("memNum"));
		
//		WebnovelReplyDTO replydto = new WebnovelReplyDTO();
		WebNovelService webService = new WebNovelService();
		
		int replyinsert = webService.insertReply(replytext, webNovNum, memNum);
		List<WebnovelReplyDTO> replydata = webService.replydata(replytext, webNovNum, memNum);
		

	
		Gson gson = new GsonBuilder()
						.setDateFormat("yyyy년MM월dd일")
						.serializeNulls()
						.create();
		
		String jsonString= gson.toJson(replydata);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
//		System.out.println("웹소설번호" + webNovNum);
//		System.out.println("웹소설댓글내용" + replytext);
	}

}
