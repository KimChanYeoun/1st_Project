package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ErrorboardDao;
public class EDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eNum = Integer.parseInt(request.getParameter("eNum"));
		ErrorboardDao eDao = new ErrorboardDao();
		int result = eDao.eDelete(eNum);
		if(result == ErrorboardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}
