package com.lastbug.firstbook.contest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.lastbug.firstbook.contest.model.dto.ContestDTO;
import com.lastbug.firstbook.contest.model.dto.ContestSsnDTO;
import com.lastbug.firstbook.contest.model.service.ContestService;

@WebServlet("/contestAll/list")
public class ContestAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String select = request.getParameter("competSsn");
		List<ContestDTO> contestList = new ContestService().selectAllconstListTwo(select);
		List<ContestSsnDTO> contestListTime = new ContestService().selectAllconstListTime();
		 
		String path = null;
		if(!contestList.isEmpty()) {
		path = "/WEB-INF/views/contest/contest.jsp";
		request.setAttribute("contestList", contestList);	
		request.setAttribute("contestListTime", contestListTime);	
		}else {						
			path = "/WEB-INF/views/common/failed.jsp";
			request.setAttribute("failedCode", "viewFailed");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

}
