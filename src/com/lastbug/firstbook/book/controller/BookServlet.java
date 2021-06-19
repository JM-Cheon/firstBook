package com.lastbug.firstbook.book.controller;

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

@WebServlet("/book/list")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path ="";
		path = "/WEB-INF/views/book/bookMain.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
