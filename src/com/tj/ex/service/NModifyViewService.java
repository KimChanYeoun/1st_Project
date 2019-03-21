package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.NoticeDao;
import com.tj.ex.dto.NoticeDto;
public class NModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nNum = Integer.parseInt(request.getParameter("nNum"));
		NoticeDao noticeDao = new NoticeDao();
		NoticeDto dto = noticeDao.nModifyView_replyView(nNum);
		request.setAttribute("nmodify_view", dto);
	}
}
