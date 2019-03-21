package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dao.CustomerDao;
import com.tj.ex.dao.ErrorboardDao;
import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.CustomerDto;
public class CLoginService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// mId, mPw 파라미터값 받아 loginCheck
		String cId = request.getParameter("cId");
		String cPw = request.getParameter("cPw");
		CustomerDao cDao = new CustomerDao();
		int result = cDao.loginCheck(cId, cPw);
		// loginCheck 결과가 SUCCESS면 세션에 추가
		// loginCheck 결과가 FAIL면 fail이유를 request.setAttribute
		if(result == CustomerDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			CustomerDto dto = cDao.getCustomer(cId);
			httpSession.setAttribute("cId", cId);
			httpSession.setAttribute("customer", dto);
			GalleryDao galleryDao = new GalleryDao();
			int gcnt = galleryDao.getGalleryMyTotCnt(cId);
			httpSession.setAttribute("gcontent_view", gcnt);
			BoardDao boardDao = new BoardDao();
			int bcnt = boardDao.getBoardMyTotCnt(cId);
			httpSession.setAttribute("bcontent_view", bcnt);
			ErrorboardDao errorboardDao = new ErrorboardDao();
			int ecnt = errorboardDao.getErrorBoardMyTotCnt(cId);
			httpSession.setAttribute("econtent_view", ecnt);
			QnADao qnADao = new QnADao();
			int qcnt = qnADao.getQnAMyTotCnt(cId);
			httpSession.setAttribute("qcontent_view", qcnt);
		}else if(result == CustomerDao.LOGIN_FAIL_PW) {
			request.setAttribute("loginErrorMsg", "비밀번호를 확인하세요");
		}else if(result == CustomerDao.LOGIN_FAIL_ID) {
			request.setAttribute("loginErrorMsg", "ID를 확인하세요");
		}
	}
}