package com.lastbug.firstbook.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lastbug.firstbook.admin.webnovel.model.service.AdminWebNovelService;
import com.lastbug.firstbook.webnovel.model.dto.WebNovelInfoDTO;


@WebServlet("/admin/webnovelCloseOpen")
public class WebnovelCloseAndOpenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			int no = Integer.valueOf(request.getParameter("webNovNum"));
			
			AdminWebNovelService awnService = new AdminWebNovelService();
			
			
			
			WebNovelInfoDTO adminWebNovelList = awnService.adminSelectWebNovelDetail(no);
						
			
			String CloseOrOpen = adminWebNovelList.getWebNovOpenOrClose();
			

			
			
			if(CloseOrOpen.equals("Y")){
				
				CloseOrOpen = "N";
				
			} else if(CloseOrOpen.equals("N")) {
				
				CloseOrOpen = "Y";
				
			}
			List<WebNovelInfoDTO> adminWebNovelList2 = new AdminWebNovelService().AdminSelectAllNovel();
			
			int result = awnService.WebNovelCloseOrOpen(no, CloseOrOpen, adminWebNovelList);
			
			String path = "";
			if(result > 0) {
				path = "/WEB-INF/views/admin/webnovel/adminWebnovel.jsp";
				request.setAttribute("successCode", "");
				request.setAttribute("adminWebNovelList", adminWebNovelList2);
				
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("message", "공개 or 비공개처리 실패!");
			}
			
			request.getRequestDispatcher(path).forward(request, response);
			
	}


}
