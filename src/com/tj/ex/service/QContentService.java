package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnADao;
import com.tj.ex.dto.QnADto;
public class QContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNum = Integer.parseInt(request.getParameter("qNum"));
		QnADao qnADao = new QnADao();
		QnADto dto = qnADao.qContentView(qNum);
		request.setAttribute("qcontent_view", dto);
	}

}
