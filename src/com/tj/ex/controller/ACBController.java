package com.tj.ex.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.ACusViewService;
import com.tj.ex.service.ACusViewService2;
import com.tj.ex.service.ALoginService;
import com.tj.ex.service.BContentService;
import com.tj.ex.service.BDeleteService;
import com.tj.ex.service.BListService;
import com.tj.ex.service.BModifyService;
import com.tj.ex.service.BModifyViewService;
import com.tj.ex.service.BReplyService;
import com.tj.ex.service.BReplyViewService;
import com.tj.ex.service.BWriteService;
import com.tj.ex.service.CAllViewService;
import com.tj.ex.service.CDeleteService;
import com.tj.ex.service.CIdConfirmService;
import com.tj.ex.service.CJoinService;
import com.tj.ex.service.CLoginService;
import com.tj.ex.service.CLogoutService;
import com.tj.ex.service.CModifyService;
import com.tj.ex.service.HGListService;
import com.tj.ex.service.HNListService;
import com.tj.ex.service.HUListService;
import com.tj.ex.service.MyBListService;
import com.tj.ex.service.Service;
@WebServlet("*.do")
public class ACBController extends HttpServlet {
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
		/* * member 관련 요청들 * * * * * * * * * * * * */
		if(com.equals("/joinView.do")) { 
			viewPage = "customer/join.jsp";
		}else if(com.equals("/join.do")) {
			service = new CJoinService();
			service.execute(request, response);
			viewPage = "customer/login.jsp";
		}else if(com.equals("/loginView.do")) {
			viewPage = "customer/login.jsp";
		}else if(com.equals("/login.do")) {
			service = new CLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/logout.do")) {
			service = new CLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/modifyView.do")) {
			viewPage = "customer/modify.jsp";
		}else if(com.equals("/modify.do")) {
			service = new CModifyService();
			service.execute(request, response);
			viewPage = "main.do";		
		}else if(com.equals("/myinform.do")) {
			viewPage = "customer/myInform.jsp";
		}else if(com.equals("/cIdConfirm.do")) {
			service = new CIdConfirmService();
			service.execute(request, response);
			viewPage = "customer/idConfirm.jsp";
		}else if(com.equals("/cDelete.do")) {
			viewPage = "customer/delete.jsp";
		}else if(com.equals("/cDelete_comfirm.do")) {
			service = new CDeleteService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/allView.do")) {
			
/************** admin 로그인 성공시 나오는 화면 ***************/
			service = new CAllViewService();
			service.execute(request, response);
			viewPage = "customer/cAllView.jsp";
		}else if(com.equals("/main.do")) {
			service = new HNListService();
			service.execute(request, response);
			service = new HUListService();
			service.execute(request, response);
			service = new HGListService();
			service.execute(request, response);
			viewPage = "main/projectMain.jsp";
		}else if(com.equals("/cinform.do")) {
			service = new ACusViewService();
			service.execute(request, response);
			service = new ACusViewService2();
			service.execute(request, response);
			viewPage = "admin/CInform.jsp";
			
			
/**************** Board 관련 요청들 **********************/
		}else if(com.equals("/list.do")) {
			service = new BListService();
			service.execute(request, response);
			viewPage = "board/list.jsp";
		}else if(com.equals("/mylist.do")) {
			service = new MyBListService();
			service.execute(request, response);
			viewPage = "board/mylist.jsp";
		}else if(com.equals("/write_view.do")) {
			write_view = 1;
			viewPage = "board/write_view.jsp";
		}else if(com.equals("/write.do")) {
			if(write_view == 1) {
				service = new BWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			service = new BContentService();
			service.execute(request, response);
			viewPage = "board/content_view.jsp";
		}else if(com.equals("/boradModify_view.do")) {
			service = new BModifyViewService();
			service.execute(request, response);
			viewPage = "board/modify_view.jsp";
		}else if(com.equals("/boradModify.do")) {
			service = new BModifyService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/delete.do")) {
			service = new BDeleteService();
			service.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/reply_view.do")) {
			service = new BReplyViewService();
			service.execute(request, response);
			viewPage = "board/reply_view.jsp";
		}else if(com.equals("/reply.do")) {
			service = new BReplyService();
			service.execute(request, response);
			viewPage = "list.do";
			
/********************** admin ***************************/
		}else if(com.equals("/adminloginView.do")) { 
			viewPage = "admin/adminLogin.jsp";
		}else if(com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}