package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.EContentService;
import com.tj.ex.service.EDeleteService;
import com.tj.ex.service.EListService;
import com.tj.ex.service.EModifyService;
import com.tj.ex.service.EModifyViewService;
import com.tj.ex.service.EReplyService;
import com.tj.ex.service.EReplyViewService;
import com.tj.ex.service.EWriteService;
import com.tj.ex.service.GContentService;
import com.tj.ex.service.GDeleteService;
import com.tj.ex.service.GListService;
import com.tj.ex.service.GModifyService;
import com.tj.ex.service.GModifyViewService;
import com.tj.ex.service.GReplyService;
import com.tj.ex.service.GReplyViewService;
import com.tj.ex.service.GWriteService;
import com.tj.ex.service.MyEListService;
import com.tj.ex.service.MyGListService;
import com.tj.ex.service.Service;

@WebServlet("*.so")
public class IGEController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length()); // 들어온 요청
		String viewPage = null;
		Service service = null;
/************************* 갤러리 게시판 *****************************/
		if (com.equals("/list.so")) {
			service = new GListService();
			service.execute(request, response);
			viewPage = "gallery/list.jsp";
		} else if (com.equals("/myglist.so")) {
			service = new MyGListService();
			service.execute(request, response);
			viewPage = "gallery/mylist.jsp";
		} else if (com.equals("/write_view.so")) {
			write_view = 1;
			viewPage = "gallery/write_view.jsp";
		} else if (com.equals("/write.so")) {
			if (write_view == 1) {
				service = new GWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.so";
		} else if (com.equals("/content_view.so")) {
			service = new GContentService();
			service.execute(request, response);
			viewPage = "gallery/content_view.jsp";
		} else if (com.equals("/galleryModify_view.so")) {
			service = new GModifyViewService();
			service.execute(request, response);
			viewPage = "gallery/modify_view.jsp";
		} else if (com.equals("/galleryModify.so")) {
			service = new GModifyService();
			service.execute(request, response);
			viewPage = "list.so";
		} else if (com.equals("/delete.so")) {
			service = new GDeleteService();
			service.execute(request, response);
			viewPage = "list.so";
		} else if (com.equals("/reply_view.so")) {
			service = new GReplyViewService();
			service.execute(request, response);
			viewPage = "gallery/reply_view.jsp";
		} else if (com.equals("/reply.so")) {
			service = new GReplyService();
			service.execute(request, response);
			viewPage = "list.so";
			
/******************************* 에러 게시판 ******************************/
		} else if (com.equals("/elist.so")) {
			service = new EListService();
			service.execute(request, response);
			viewPage = "errorboard/list.jsp";
		} else if (com.equals("/myelist.so")) {
			service = new MyEListService();
			service.execute(request, response);
			viewPage = "errorboard/mylist.jsp";
		} else if (com.equals("/ewrite_view.so")) {
			write_view = 1;
			viewPage = "errorboard/write_view.jsp";
		} else if (com.equals("/ewrite.so")) {
			if (write_view == 1) {
				service = new EWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "elist.so";
		} else if (com.equals("/econtent_view.so")) {
			service = new EContentService();
			service.execute(request, response);
			viewPage = "errorboard/content_view.jsp";
		} else if (com.equals("/errorboardModify_view.so")) {
			service = new EModifyViewService();
			service.execute(request, response);
			viewPage = "errorboard/modify_view.jsp";
		} else if (com.equals("/errorboardModify.so")) {
			service = new EModifyService();
			service.execute(request, response);
			viewPage = "elist.so";
		} else if (com.equals("/edelete.so")) {
			service = new EDeleteService();
			service.execute(request, response);
			viewPage = "elist.so";
		} else if (com.equals("/ereply_view.so")) {
			service = new EReplyViewService();
			service.execute(request, response);
			viewPage = "errorboard/reply_view.jsp";
		} else if (com.equals("/ereply.so")) {
			service = new EReplyService();
			service.execute(request, response);
			viewPage = "elist.so";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}