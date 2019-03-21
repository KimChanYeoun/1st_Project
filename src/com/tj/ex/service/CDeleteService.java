package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dao.ErrorboardDao;
import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dao.QnADao;
public class CDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String cId = request.getParameter("cId");
		CustomerDao customerDao = new CustomerDao();
		// 게시판에 cId가 쓴 글 삭제
		BoardDao boardDao = new BoardDao();
		boardDao.ballDelete(cId);
		ErrorboardDao errorboardDao = new ErrorboardDao();
		errorboardDao.eallDelete(cId);
		GalleryDao galleryDao = new GalleryDao();
		galleryDao.gallDelete(cId);
		QnADao qnADao = new QnADao();
		qnADao.qallDelete(cId);
		// 회원테이블에 cId삭제
		int result = customerDao.cDelete(cId);
		if(result == CustomerDao.SUCCESS) {
			request.setAttribute("resultMsg", "회원탈퇴 성공");
			httpSession.removeAttribute("cId");
			httpSession.removeAttribute("customer");
		}else {
			request.setAttribute("resultMsg", "회원탈퇴 실패");
		}
	}
}
