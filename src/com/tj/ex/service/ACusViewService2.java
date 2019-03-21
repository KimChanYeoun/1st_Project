package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.BoardDao;
import com.tj.ex.dao.ErrorboardDao;
import com.tj.ex.dao.GalleryDao;
import com.tj.ex.dao.QnADao;

public class ACusViewService2 implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// mId, mPw 파라미터값 받아 loginCheck
		String cId = request.getParameter("cId");
		GalleryDao galleryDao = new GalleryDao();
		int gcnt = galleryDao.getGalleryMyTotCnt(cId);
		request.setAttribute("gcontent_view", gcnt);
		BoardDao boardDao = new BoardDao();
		int bcnt = boardDao.getBoardMyTotCnt(cId);
		request.setAttribute("bcontent_view", bcnt);
		ErrorboardDao errorboardDao = new ErrorboardDao();
		int ecnt = errorboardDao.getErrorBoardMyTotCnt(cId);
		request.setAttribute("econtent_view", ecnt);
		QnADao qnADao = new QnADao();
		int qcnt = qnADao.getQnAMyTotCnt(cId);
		request.setAttribute("qcontent_view", qcnt);
	}
}