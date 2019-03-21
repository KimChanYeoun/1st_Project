package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.QnADao;
public class QDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qNum = Integer.parseInt(request.getParameter("qNum"));
		QnADao qnADao = new QnADao();
		int result = qnADao.qDelete(qNum);
		if(result == QnADao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}
