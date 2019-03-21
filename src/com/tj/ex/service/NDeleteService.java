package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
public class NDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao noticeDao = new NoticeDao();
		int result = noticeDao.nDelete(nNum);
		if(result == NoticeDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}
