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


@WebServlet("/webnovel/reply/delete")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		int webNovNum = Integer.parseInt(request.getParameter("webNovNum"));
		int memNum = Integer.parseInt(request.getParameter("memNum"));
		
//		System.out.println("소설 번호" + webNovNum);
//		System.out.println("회원번호" +memNum);
		
		WebNovelService webService = new WebNovelService();

		/* 댓글 삭제용 메소드 */
		int replyinsert = webService.deleteReply(replyNum);
		
//		System.out.println("결과물" + replyinsert);
		List<WebnovelReplyDTO> replydata = webService.replydata(webNovNum, memNum);
		
//		System.out.println("댓글 데이터" +replydata);
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy년MM월dd일")
				.serializeNulls()
				.create();
		
		String jsonString = gson.toJson(replydata);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(jsonString);

		out.flush();
		out.close();
		
		
		
	}



}
