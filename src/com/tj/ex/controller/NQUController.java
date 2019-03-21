package com.tj.ex.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.MyQListService;
import com.tj.ex.service.NContentService;
import com.tj.ex.service.NDeleteService;
import com.tj.ex.service.NListService;
import com.tj.ex.service.NModifyService;
import com.tj.ex.service.NModifyViewService;
import com.tj.ex.service.NWriteService;
import com.tj.ex.service.QContentService;
import com.tj.ex.service.QDeleteService;
import com.tj.ex.service.QListService;
import com.tj.ex.service.QModifyService;
import com.tj.ex.service.QModifyViewService;
import com.tj.ex.service.QReplyService;
import com.tj.ex.service.QReplyViewService;
import com.tj.ex.service.QWriteService;
import com.tj.ex.service.QaModifyViewService;
import com.tj.ex.service.QrContentService;
import com.tj.ex.service.Service;
import com.tj.ex.service.UContentService;
import com.tj.ex.service.UDeleteService;
import com.tj.ex.service.UListService;
import com.tj.ex.service.UModifyService;
import com.tj.ex.service.UModifyViewService;
import com.tj.ex.service.UWriteService;
@WebServlet("*.ao")
public class NQUController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int write_view = 0;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri     = request.getRequestURI();
		String conPath = request.getContextPath();
		String com     = uri.substring(conPath.length()); //들어온 요청
		String viewPage = null;
		Service service = null;
/****************************notice게시판******************************/
		if(com.equals("/list.ao")) {
			service = new NListService();
			service.execute(request, response);
			viewPage = "notice/list.jsp";
		}else if(com.equals("/write.ao")) {
			if(write_view == 1) {
				service = new NWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.ao";
		}else if(com.equals("/write_view.ao")) {
			write_view = 1;
			viewPage = "notice/write_view.jsp";
		}else if(com.equals("/content_view.ao")) {
			service = new NContentService();
			service.execute(request, response);
			viewPage = "notice/content_view.jsp";
		}else if(com.equals("/noticeModify_view.ao")) {
			service = new NModifyViewService();
			service.execute(request, response);
			viewPage = "notice/modify_view.jsp";
		}else if(com.equals("/noticeModify.ao")) {
			service = new NModifyService();
			service.execute(request, response);
			viewPage = "list.ao";
		}else if(com.equals("/delete.ao")) {
			service = new NDeleteService();
			service.execute(request, response);
			viewPage = "list.ao";
			
/***************************업데이트 게시판***************************/
		}else if(com.equals("/ulist.ao")) {
			service = new UListService();
			service.execute(request, response);
			viewPage = "upgrade/list.jsp";
		}else if(com.equals("/uwrite.ao")) {
			if(write_view == 1) {
				service = new UWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "ulist.ao";
		}else if(com.equals("/uwrite_view.ao")) {
			write_view = 1;
			viewPage = "upgrade/write_view.jsp";
		}else if(com.equals("/ucontent_view.ao")) {
			service = new UContentService();
			service.execute(request, response);
			viewPage = "upgrade/content_view.jsp";
		}else if(com.equals("/upgradeModify_view.ao")) {
			service = new UModifyViewService();
			service.execute(request, response);
			viewPage = "upgrade/modify_view.jsp";
		}else if(com.equals("/upgradeModify.ao")) {
			service = new UModifyService();
			service.execute(request, response);
			viewPage = "ulist.ao";
		}else if(com.equals("/udelete.ao")) {
			service = new UDeleteService();
			service.execute(request, response);
			viewPage = "ulist.ao";
			
/**************************qna게시판*******************************/
		}else if(com.equals("/qlist.ao")) {
			service = new QListService();
			service.execute(request, response);
			viewPage = "QnA/list.jsp";
		} else if (com.equals("/myQlist.ao")) {
			service = new MyQListService();
			service.execute(request, response);
			viewPage = "QnA/mylist.jsp";
		}else if(com.equals("/qwrite_view.ao")) {
			write_view = 1;
			viewPage = "QnA/write_view.jsp";
		}else if(com.equals("/qwrite.ao")) {
			if(write_view == 1) {
				service = new QWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "qlist.ao";
		}else if(com.equals("/qcontent_view.ao")) {
			service = new QContentService();
			service.execute(request, response);
			viewPage = "QnA/content_view.jsp";
		}else if(com.equals("/QnAModify_view.ao")) {
			service = new QModifyViewService();
			service.execute(request, response);
			viewPage = "QnA/modify_view.jsp";
		}else if(com.equals("/QnAModify.ao")) {
			service = new QModifyService();
			service.execute(request, response);
			viewPage = "qlist.ao";
		}else if(com.equals("/qdelete.ao")) {
			service = new QDeleteService();
			service.execute(request, response);
			viewPage = "qlist.ao";
		}else if(com.equals("/qreply_view.ao")) {
			service = new QReplyViewService();
			service.execute(request, response);
			viewPage = "QnA/reply_view.jsp";
		}else if(com.equals("/qreply.ao")) {
			service = new QReplyService();
			service.execute(request, response);
			viewPage = "qlist.ao";
		}else if(com.equals("/qrcontent_view.ao")) {
			service = new QrContentService();
			service.execute(request, response);
			viewPage = "QnA/content_view.jsp";
		}else if(com.equals("/aQnAModify_view.ao")) {
			service = new QaModifyViewService();
			service.execute(request, response);
			viewPage = "QnA/modify_view.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}